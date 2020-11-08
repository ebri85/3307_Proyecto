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
public class Ejecuta {

    protected String archivo;
    protected List<String> codigo = Collections.emptyList();
    protected ArrayList<String> reservadas = CargaReservadas();
    protected InfoArchivo infoArchivo;

    boolean cargarCodigo;
    boolean generaErrores;
    
    Escaner escaner;

    public Ejecuta(String str) {
        try {

            archivo = str;
            infoArchivo = new InfoArchivo(archivo);
            

            //System.out.println("EJECUTA() - valor de archivo ->" + archivo);

        } catch (Exception e) {
            System.out.println("Clase Ejecuta>Ejecuta()=>" + e.getMessage());
            e.printStackTrace();
        }

    }

    protected void GeneraDatos() {
        try {
            
            infoArchivo.GeneraDatos();
            cargarCodigo = CargaCodigo();
            escaner = new Escaner(codigo,reservadas,infoArchivo);
            escaner.GeneraDatos();
           
        } catch (Exception e) {
            System.out.println("Clase Ejecuta>GeneraDatos()=>" + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * Metodo <b>CargaReservadas()</b>
     *
     * @return Objeto de tipo @String con la lista de Reservadas
     */
    private ArrayList<String> CargaReservadas() {
        try {

            ArrayList<String> temp = new ArrayList();
            InputStream iSt = this.getClass().getResourceAsStream("/KOVOL/UTILS/reservadas.txt");
            InputStreamReader iStR = new InputStreamReader(iSt);
            BufferedReader bR = new BufferedReader(iStR);
            String hilera;
            do {
                temp.add(hilera = bR.readLine());

            } while (hilera != null);
            // temp.forEach(e->System.out.println(e));
            return new ArrayList(temp);

        } catch (Exception e) {
            System.out.println("Clase Ejecuta>CargaReservadas()=>" + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    private boolean CargaCodigo() {
        try {
            boolean error = false;
            //System.out.println("RUTA_ARCHIVO =>" + RUTA_ARCHIVO);
            File file = new File(infoArchivo.archivo);

            if (!(file.exists())) {                
                error = true;
                System.out.println("file.exists() =>" + file.exists());
            }
            codigo = Files.readAllLines(file.toPath());

            //codigo.forEach((e -> System.out.println(e)));

            return true;

        } catch (Exception e) {
            System.out.println("Clase Ejecuta>CargaCodigo()=>" + e.getMessage());
            e.printStackTrace();
            return false;
        }

    } 
    

}
