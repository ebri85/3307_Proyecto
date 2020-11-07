/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kovol;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author ebri_
 */
public class Escaner {

    
    boolean error = false;

    public Escaner() {

    }

    /**
     * *
     * Metodo <b>CargaReservadas()</b>
     *
     * @param resv ArrayListReservadas
     * @param rtArch String de la ruta del archivo
     */
    public InfoArchivo GeneraDatos(String str) {
        try {

            return new InfoArchivo(str);

        } catch (Exception e) {

            System.out.println("Clase Escaner>Ejecuta()=>" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


}
