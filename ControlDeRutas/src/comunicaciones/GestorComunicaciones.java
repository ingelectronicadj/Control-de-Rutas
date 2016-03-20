/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comunicaciones;

import Elementos.SistemaEmbebido;

/**
 *
 * @author duvan
 */
public class GestorComunicaciones {
    private PuertoSerie puerto;
    private SistemaEmbebido embebido;

    /**
     * @return the puerto
     */
    public PuertoSerie getPuerto() {
        return puerto;
    }

    /**
     * @param puerto the puerto to set
     */
    public void setPuerto(PuertoSerie puerto) {
        this.puerto = puerto;
    }

    /**
     * @return the embebido
     */
    public SistemaEmbebido getEmbebido() {
        return embebido;
    }

    /**
     * @param embebido the embebido to set
     */
    public void setEmbebido(SistemaEmbebido embebido) {
        this.embebido = embebido;
    }
    
    
}
