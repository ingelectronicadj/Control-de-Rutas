/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicaciones;


/**
 *
 * @author duvan
 */
public class PuertoSerie{
    private String nombre;
    private int BaudRate;
    private int bitsParada;
    private int paridad;
    private int HandShake;

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the BaudRate
     */
    public int getBaudRate() {
        return BaudRate;
    }

    /**
     * @param BaudRate the BaudRate to set
     */
    public void setBaudRate(int BaudRate) {
        this.BaudRate = BaudRate;
    }

    /**
     * @return the bitsParada
     */
    public int getBitsParada() {
        return bitsParada;
    }

    /**
     * @param bitsParada the bitsParada to set
     */
    public void setBitsParada(int bitsParada) {
        this.bitsParada = bitsParada;
    }

    /**
     * @return the paridad
     */
    public int getParidad() {
        return paridad;
    }

    /**
     * @param paridad the paridad to set
     */
    public void setParidad(int paridad) {
        this.paridad = paridad;
    }

    /**
     * @return the HandShake
     */
    public int getHandShake() {
        return HandShake;
    }

    /**
     * @param HandShake the HandShake to set
     */
    public void setHandShake(int HandShake) {
        this.HandShake = HandShake;
    }

}
