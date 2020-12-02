/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kovol;

import java.nio.file.Paths;

/**
 *
 * @author Esau Brizuela
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            if (args.length > 0) {
                String archivo = args[0];
                Ejecuta ejecuta = new Ejecuta(archivo);

            } else {
                System.out.println("No se ha indicado el nombre de, parametro");
            }

        } catch (Exception e) {
            System.out.println("Clase Main=>" + e.getMessage());
            e.printStackTrace();
        }

    }

}
