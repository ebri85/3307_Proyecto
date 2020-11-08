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
    protected ArrayList<String> reservadas = new ArrayList();
    protected InfoArchivo infoArchivo;
    protected Escaner escaner;
    

    boolean cargarCodigo;
    boolean generaErrores;

    public Ejecuta() {
    }

 
    public Ejecuta(String str) {
        try {

            archivo = str;
            infoArchivo = new InfoArchivo(str);

            //System.out.println("EJECUTA() - valor de archivo ->" + archivo);
        } catch (Exception e) {
            System.out.println("Clase Ejecuta>Ejecuta()=>" + e.getMessage());
            e.printStackTrace();
        }

    }

    protected void GeneraDatos() {
        try {
            do {
                CargaReservadas();
                infoArchivo.GeneraDatos();
                cargarCodigo = CargaCodigo();
                EnviaInfoEscaner(this.codigo,this.reservadas,this.infoArchivo);
                escaner.GeneraDatos();
                

            } while (reservadas.isEmpty());

        } catch (Exception e) {
            System.out.println("Clase Ejecuta>GeneraDatos()=>" + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void EnviaInfoEscaner(List<String> c,ArrayList<String> r, InfoArchivo a){
        try {
            this.escaner = new Escaner(c,r,a);
            
        } catch (Exception e) {
                System.out.println("Clase Ejecuta>EnviaAescaner()=>" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Metodo <b>CargaReservadas()</b>
     *
     * @return Objeto de tipo @String con la lista de Reservadas
     */
    private void CargaReservadas() {
        try {
            //  System.out.println("ENTRO AL METODO CARGA RESERVADAS");
            InputStream iSt = this.getClass().getResourceAsStream("/kovol/UTILS/reservadas.txt");
            InputStreamReader iStR = new InputStreamReader(iSt);
            BufferedReader bR = new BufferedReader(iStR);
            String hilera = null;

            do {
                hilera = bR.readLine();
                this.reservadas.add(hilera);
                //    System.out.println(hilera);

            } while (hilera != null);
            // this.reservadas.forEach(e->System.out.println(e));

        } catch (Exception e) {

            System.out.println("Clase Ejecuta>CargaReservadas()=>" + e.getMessage());
            e.printStackTrace();

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
    

    protected void Compila() {
        try {
            PreCompila();
            String archivoExe = infoArchivo.RemueveExtensionCob(infoArchivo.nombreCob.toString(),".exe");
            Runtime.getRuntime().exec(new String[]{
                "cmd",
                "/K",
                "Start",
                 archivoExe               
                
            });
        } catch (Exception e) {
            System.out.println("Clase Ejecuta>Compila()=>" + e.getMessage());
            e.printStackTrace();
        }
    }
    
     private void PreCompila() {
        try {
            
            Runtime.getRuntime().exec(new String[]{
                "cmd",
                "/K",
                "Start",
                "cobc",
                "-x",
                infoArchivo.nombreCob.toString()
                            
            });
        } catch (Exception e) {
            System.out.println("Clase Ejecuta>Compila()=>" + e.getMessage());
            e.printStackTrace();
        }
    }

}
