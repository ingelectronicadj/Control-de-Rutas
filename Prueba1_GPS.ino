#include <LGPS.h>
#include <LSD.h>

gpsSentenceInfoStruct info;
char buff[256];
unsigned int tiempo,ciclo;
unsigned char cont=0;
String inputString = "";         // a string to hold incoming data
boolean stringComplete = false;  // whether the string is complete

static unsigned char getComma(unsigned char num,const char *str)
{
  unsigned char i,j = 0;
  int len=strlen(str);
  for(i = 0;i < len;i ++)
  {
     if(str[i] == ',')
      j++;
     if(j == num)
      return i + 1; 
  }
  return 0; 
}

static double getDoubleNumber(const char *s)
{
  char buf[10];
  unsigned char i;
  double rev;
  
  i=getComma(1, s);
  i = i - 1;
  strncpy(buf, s, i);
  buf[i] = 0;
  rev=atof(buf);
  return rev; 
}

static double getIntNumber(const char *s)
{
  char buf[10];
  unsigned char i;
  double rev;
  
  i=getComma(1, s);
  i = i - 1;
  strncpy(buf, s, i);
  buf[i] = 0;
  rev=atoi(buf);
  return rev; 
}


void parseGPRMC(const char* GPRMCstr)
{
  /*$GPRMC,123519,A,4807.038,N,01131.000,E,022.4,084.4,230394,003.1,W*6A

Where:
     RMC          Recommended Minimum sentence C
     123519       Fix taken at 12:35:19 UTC
     A            Status A=active or V=Void.
     4807.038,N   Latitude 48 deg 07.038' N
     01131.000,E  Longitude 11 deg 31.000' E
     022.4        Speed over the ground in knots
     084.4        Track angle in degrees True
     230394       Date - 23rd of March 1994
     003.1,W      Magnetic Variation
     *6A          The checksum data, always begins with *
*/
  int tmp;
  
  // make a string for assembling the data to log:
  String dataString = "";
  
  if(GPRMCstr[0] == '$')
  {
    
    /*for (tmp=7;tmp<13;tmp++){
      dataString +=GPRMCstr[tmp];
    }
    for (tmp=19;tmp<51;tmp++){
      dataString +=GPRMCstr[tmp];
    }
    for (tmp=56;tmp<62;tmp++){
      dataString +=GPRMCstr[tmp];
    }    
    */
    // open the file. note that only one file can be open at a time,
    // so you have to close this one before opening another.
    LFile dataFile = LSD.open("datalog.txt", FILE_WRITE);

    // if the file is available, write to it:
    if (dataFile) {
      dataFile.print(GPRMCstr);
      dataFile.close();
      // print to the serial port too:
      Serial.print(GPRMCstr);
    }
  }
}


void leerSetup(){
  char fileContents[20]; // Probably can be smaller
  byte index = 0;
 
  LFile dataFile = LSD.open("setup.txt");
  // if the file is available, read from it:
  if (dataFile) {
    while(dataFile.available()){
      fileContents[index++] = dataFile.read();
      fileContents[index] = '\0'; // NULL terminate the array      
    }
    dataFile.close();
    Serial.println(fileContents);
   
    tiempo=(fileContents[1]-'0')*10000;
    tiempo=tiempo+(fileContents[2]-'0')*1000;
    tiempo=tiempo+(fileContents[3]-'0')*100;
    tiempo=tiempo+(fileContents[4]-'0')*10;
    tiempo=tiempo+(fileContents[5]-'0');
  }
}


/*
  SerialEvent occurs whenever a new data comes in the
 hardware serial RX.  This routine is run between each
 time loop() runs, so using delay inside loop can delay
 response.  Multiple bytes of data may be available.
 */
void serialEvent() {
  while (Serial1.available()) {
    // get the new byte:
    char inChar = (char)Serial1.read();
    cont++;
    // add it to the inputString:
    inputString += inChar;
    // if the incoming character is a newline, set a flag
    // so the main loop can do something about it:
    if (inChar == '\n') {
      stringComplete = true;
    }
  }
}


void leerData(){
  LFile dataFile = LSD.open("datalog.txt");
  // if the file is available, read from it:
  if (dataFile) {
    while(dataFile.available()){
      Serial1.write(dataFile.read());
    }
    dataFile.close();    
  }
}


void verDataSerial(){
  for (ciclo=0;ciclo<1000;ciclo++){
    serialEvent(); //call the function
    // print the string when a newline arrives:
    if (stringComplete) {
      if (cont==15 && inputString[0] == '$'){      
        LSD.remove("setup.txt");
        // open the file. note that only one file can be open at a time,
        // so you have to close this one before opening another.
        LFile dataFile = LSD.open("setup.txt", FILE_WRITE);
        // if the file is available, write to it:
        if (dataFile) {
          dataFile.print(inputString);
          dataFile.close();     
        }
        Serial1.print(inputString);
        leerSetup();
      }      
      else {
        if (cont==8 && inputString[0] == '$'){      
          if (inputString[1]=='Q'&&inputString[2]=='D'&&inputString[3]=='A'&&inputString[4]=='T'&&inputString[5]=='A'){
            Serial1.print(inputString);       
            leerData();
          }
        }
        else{
          if (cont==10 && inputString[0] == '$'){      
            if (inputString[1]=='Q'&&inputString[2]=='T'&&inputString[3]=='I'&&inputString[4]=='E'&&inputString[5]=='M'&&inputString[6]=='P'&&inputString[7]=='O')
            {       
              char fileContents[20]; // Probably can be smaller
              byte index = 0;
 
              LFile dataFile = LSD.open("setup.txt");
              // if the file is available, read from it:
              if (dataFile) {
                while(dataFile.available()){
                  fileContents[index++] = dataFile.read();
                  fileContents[index] = '\0'; // NULL terminate the array      
                }
                dataFile.close();
                Serial1.print(fileContents);
              }
            }
          }
        }   
      }         
      // clear the string:
      inputString = "";
      stringComplete = false;
      cont=0;      
    } 
    delay(tiempo);
  }
}


void setup() {
  // put your setup code here, to run once:
  delay(3000);
  Serial.begin(115200);
  Serial1.begin(115200);
  LGPS.powerOn();
  Serial.println("LGPS Power on, and waiting ..."); 
  Serial.print("Initializing SD card...");
  LSD.begin();
  Serial.println("card initialized.");
  leerSetup();  
  delay(3000);
}


void loop() {
  // put your main code here, to run repeatedly:
  Serial.println("LGPS loop"); 
  LGPS.getData(&info);
  Serial.print((char*)info.GPRMC);
  parseGPRMC((const char*)info.GPRMC);
  Serial.println("\r\n");
  verDataSerial(); 
}





