/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import información.Trama;
import java.util.StringTokenizer;

/**
 *
 * @author duvan
 */
public class FormateadorDatos {

    public String trama2KML(String Log) {
        String kmlTxt = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\" xmlns:kml=\"http://www.opengis.net/kml/2.2\" xmlns:atom=\"http://www.w3.org/2005/Atom\">\n" +
                        "<Document>\n" +
                        "	<name>Ruta.kml</name>\n" +
                        "	<open>1</open>\n" +
                        "	<Style id=\"s_ylw-pushpin_hl\">\n" +
                        "		<IconStyle>\n" +
                        "			<scale>1.3</scale>\n" +
                        "			<Icon>\n" +
                        "				<href>http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png</href>\n" +
                        "			</Icon>\n" +
                        "			<hotSpot x=\"20\" y=\"2\" xunits=\"pixels\" yunits=\"pixels\"/>\n" +
                        "		</IconStyle>\n" +
                        "		<LineStyle>\n" +
                        "			<color>ff0000ff</color>\n" +
                        "			<width>3</width>\n" +
                        "		</LineStyle>\n" +
                        "	</Style>\n" +
                        "	<StyleMap id=\"m_ylw-pushpin\">\n" +
                        "		<Pair>\n" +
                        "			<key>normal</key>\n" +
                        "			<styleUrl>#s_ylw-pushpin</styleUrl>\n" +
                        "		</Pair>\n" +
                        "		<Pair>\n" +
                        "			<key>highlight</key>\n" +
                        "			<styleUrl>#s_ylw-pushpin_hl</styleUrl>\n" +
                        "		</Pair>\n" +
                        "	</StyleMap>\n" +
                        "	<Style id=\"s_ylw-pushpin\">\n" +
                        "		<IconStyle>\n" +
                        "			<scale>1.1</scale>\n" +
                        "			<Icon>\n" +
                        "				<href>http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png</href>\n" +
                        "			</Icon>\n" +
                        "			<hotSpot x=\"20\" y=\"2\" xunits=\"pixels\" yunits=\"pixels\"/>\n" +
                        "		</IconStyle>\n" +
                        "		<LineStyle>\n" +
                        "			<color>ff0000ff</color>\n" +
                        "			<width>3</width>\n" +
                        "		</LineStyle>\n" +
                        "	</Style>\n" +
                        "	<Placemark>\n" +
                        "		<name>Ruta</name>\n" +
                        "		<styleUrl>#m_ylw-pushpin</styleUrl>\n" +
                        "		<LineString>\n" +
                        "			<tessellate>1</tessellate>\n" +
                        "			<coordinates>";        
        
        String kmlfooter = "			</coordinates>\n" +
                            "		</LineString>\n" +
                            "	</Placemark>\n" +
                            "</Document>\n" +
                            "</kml>";
        StringTokenizer stk1 = new StringTokenizer(Log, "\n\r");
        while (stk1.hasMoreTokens()) {
            String cadena = stk1.nextToken();
            StringTokenizer stk = new StringTokenizer(cadena, ",");
            int c = 0;
            float lat = 0f, lon = 0f, deg = 0f, min = 0f;
            boolean valid = false;
            Trama trama=new Trama();
            trama.setCadena(cadena);
            if(trama.calcularTrama())                
            while (stk.hasMoreTokens()) {
                switch (c++) {                    
                    case 2://valid
                        if (stk.nextToken().equals("A")) {
                            valid = true;
                        }
                        break;
                    case 3://lat                        
                        lat = Float.parseFloat(stk.nextToken());
                        deg = (int) (lat / 100);
                        min = (lat - deg * 100) / 60;
                        lat = deg + min;
                        break;
                    case 4://NS
                        if (stk.nextToken().equals("S")) {
                            lat = -lat;
                        }
                        break;
                    case 5:
                        lon = Float.parseFloat(stk.nextToken());
                        deg = (int) (lon / 100);
                        min = (lon - deg * 100) / 60;
                        lon = deg + min;
                        break;
                    case 6:
                        if (stk.nextToken().equals("W")) {
                            lon = -lon;
                        }
                        break;
                    default:
                        stk.nextToken();
                        break;
                }
            }
            if (valid) {
                kmlTxt += lon + ",";
                kmlTxt += lat + ",0 ";
            }
        }
        kmlTxt += kmlfooter;
        return kmlTxt;
    }
}
