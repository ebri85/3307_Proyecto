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
import java.util.Scanner;

/**
 * <b>Class Ejecuta</b>
 * <p>
 * Lleva acabo el llamado de los diferentes procesos realizados en Escaner, y
 * ademas de compilar el archivo el archivo.kovol a sus diferentes versiones</p>
 *
 * @author Esau Brizuela
 */
public class Ejecuta {

    private String archivoExe;
    protected String archivo;

    protected List<String> codigo = Collections.emptyList();
    protected ArrayList<String> reservadas = new ArrayList();
    protected InfoArchivo infoArchivo;
    protected Escaner escaner;

    boolean cargarCodigo;
    boolean generaErrores;

    /** <b>Constructor Ejecuta()</b>
     *
     */
    public Ejecuta() {
    }

    /**
     * <b>Constructor Ejecuta(String str)</b>
     * <p>
     * Constructor que inicializa las funcionalidades de la clase Ejecuta</p> *
     *
     * @param str String str
     */
    public Ejecuta(String str) {
        try {

            archivo = str;
            infoArchivo = new InfoArchivo(str);
            GeneraDatos();

            //System.out.println("EJECUTA() - valor de archivo ->" + archivo);
        } catch (Exception e) {
            System.out.println("Clase Ejecuta>Ejecuta()=>" + e.getMessage());
            e.printStackTrace();
        }

    }

    /**
     * <b>Método GeneraDatos</b>
     * <p>
     * Este metodo realiza el llamada de otros metodos, que se encarga de hacer
     * el llamada a diferentes metodos</p>
     *
     * @see
     * @kink
     */
    protected void GeneraDatos() {
        try {
            do {
                CargaReservadas();

                infoArchivo.GeneraDatos();
                cargarCodigo = CargaCodigo();
                EnviaInfoEscaner(this.codigo, this.reservadas, this.infoArchivo);
                escaner.GeneraDatos();
                // Compila();

            } while (reservadas.isEmpty());

        } catch (Exception e) {
            System.out.println("Clase Ejecuta>GeneraDatos()=>" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void EnviaInfoEscaner(List<String> c, ArrayList<String> r, InfoArchivo a) {
        try {
            this.escaner = new Escaner(c, r, a);

        } catch (Exception e) {
            System.out.println("Clase Ejecuta>EnviaAescaner()=>" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * <b> Metodo <b>CargaReservadas()</b>
     * <p>
     * Este método accesa el archivo reservadas.txt y lo carga en un ArrayList,
     * para poder luego se utilizado en la consulta que se requiere</p>
     *
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

    /**
     *
     * < b>Metodo CargaCodigo</b>
     * <p>
     * Lee el archivo kovol y lo carga a una Lista, esto para hacer más facil
     * las diferentes validaciones que el compilador necesita hacer</p>
     *
     * @return true si no hubo inconveniento durante el proceso de lectura del
     * archivo
     */
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

    /**
     * <b>Metodo <b>Compila()</b>
     * Compila el archivo a Exe y luego lo ejecuta en CMD
     */
    protected void Compila() {
        try {
            this.archivoExe = infoArchivo.GeneraExtensionExe(infoArchivo.nombreCob.toString(), ".exe");
            String arch = this.archivoExe;
            String nomCob = infoArchivo.nombreCob.toString();
            Process p = Runtime.getRuntime().exec(new String[]{
                "cobc",
                "-x",
                nomCob
            });

            Thread t = new Thread(new Runnable() {

                @Override
                public void run() {
                    try {

                        p.waitFor();
                        Runtime.getRuntime().exec(new String[]{
                            "cmd",
                            "/c",
                            "Start",
                            "cmd",
                            "/k",
                            arch
                        });

                    } catch (Exception e) {
                        System.out.println("Clase Ejecuta>Void()=>" + e.getMessage());
                        e.printStackTrace();
                    }

                }
            });
            t.start();

        } catch (Exception e) {
            System.out.println("Clase Ejecuta>Compila()=>" + e.getMessage());
            e.printStackTrace();

        }
    }

}
