/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author duvan
 */
public class ValidadorInformacion {

    public static boolean validarPlaca(String placa) {
        if (placa.length() != 6) {
            return false;
        }
        try {
            Integer.parseInt(placa.substring(3));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
        public static String getSum(String in) {
        int checksum = 0;
        if (in.startsWith("$")) {
            in = in.substring(1, in.length());
        }
        int end = in.indexOf('*');
        if (end == -1) {
            end = in.length();
        }
        for (int i = 0; i < end; i++) {
            checksum = checksum ^ in.charAt(i);   //^ XOR
        }
        String hex = Integer.toHexString(checksum);
        if (hex.length() == 1) {
            hex = "0" + hex;
        }
        return hex.toUpperCase();
    }

}
