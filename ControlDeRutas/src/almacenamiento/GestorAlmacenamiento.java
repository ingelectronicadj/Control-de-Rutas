/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package almacenamiento;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author duvan
 */
public class GestorAlmacenamiento extends javax.swing.JFrame{
    
    private Archivo archivo;
    
        public String guardar(){            
            return guardarArchivo(archivo.getDatos(),".txt");
        }

        public String guardarKML(){            
            return guardarArchivo(archivo.getDatos(),".kml");
        }
        public String guardarArchivo(String Data, String MIME) {
        try {
            JFileChooser file = new JFileChooser();
            file.showSaveDialog(this);
            File guarda = file.getSelectedFile();
            if (guarda != null) {
                try (FileWriter save = new FileWriter(guarda + MIME)) {
                    save.write(Data);
                }
                JOptionPane.showMessageDialog(null,
                        "El archivo se ha guardado Exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
                return guarda.getAbsolutePath();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    /**
     * @return the archivo
     */
    public Archivo getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(Archivo archivo) {
        this.archivo = archivo;
    }
    
    
}
