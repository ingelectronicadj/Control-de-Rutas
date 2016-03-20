/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package información;

import java.util.StringTokenizer;
import logica.ValidadorInformacion;

/**
 *
 * @author duvan
 */
public class Trama {

    private String cadena;
    private String latitud;
    private String longitud;
    private String fecha;
    private String hora;
    private String velocidad;

    public boolean calcularTrama() {
        if (ValidadorInformacion.getSum(cadena.trim()).equals(cadena.trim().substring(cadena.length() - 2))) {
            StringTokenizer stk = new StringTokenizer(cadena, ",");
            int c = 0;
            while (stk.hasMoreTokens()) {
                switch (c++) {
                    case 1:
                        setHora(stk.nextToken());
                        break;
                    case 3:
                        latitud = (stk.nextToken());
                        break;
                    case 4:
                        latitud = (latitud + " " + stk.nextToken());
                        break;
                    case 5:
                        longitud = (stk.nextToken());
                        break;
                    case 6:
                        longitud = (longitud + " " + stk.nextToken());
                        break;
                    case 7:
                        float vel = Float.parseFloat(stk.nextToken()) * 1.852F;
                        velocidad = (vel + " Km/h");
                        break;
                    case 9:
                        fecha = (stk.nextToken());
                        break;
                    default:
                        stk.nextToken();
                        break;
                }
            }
        }
        return ValidadorInformacion.getSum(cadena.trim()).equals(cadena.trim().substring(cadena.length() - 2));
    }

    /**
     * @return the cadena
     */
    public String getCadena() {
        return cadena;
    }

    /**
     * @param cadena the cadena to set
     */
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    /**
     * @return the latitud
     */
    public String getLatitud() {
        return latitud;
    }

    /**
     * @param latitud the latitud to set
     */
    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    /**
     * @return the longitud
     */
    public String getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return the velocidad
     */
    public String getVelocidad() {
        return velocidad;
    }

    /**
     * @param velocidad the velocidad to set
     */
    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }
}
