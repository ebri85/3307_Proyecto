/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kovol;

import java.nio.file.Paths;

/**
 *
 * @author ebri_85
 */
public class main {

        
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String archivo = args[0];
        
        Ejecuta ejecuta = new Ejecuta(archivo);
        
        ejecuta.GeneraDatos();
        
        ejecuta.Compila();
        

    }
    
}
