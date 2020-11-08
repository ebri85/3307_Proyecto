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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ebri_
 */
public class Escaner {

    protected List<String> codigo = Collections.emptyList();
    protected ArrayList<String> errores = new ArrayList();
    protected ArrayList<String> reservadas;
    protected InfoArchivo dataArchivo;

    boolean error = false;

    public Escaner(List<String> c, ArrayList<String> r, InfoArchivo a) {
        this.codigo = c;
        this.reservadas = r;
        this.dataArchivo = a;
    }

    protected void GeneraDatos() {
        try {
            boolean error = false;

            while (!(error)) {

                // System.out.println(this.dataArchivo.toString());
                GeneraArchivoCob(this.dataArchivo, this.codigo);
                error = true;

            }
            GeneraArchivoErrores(this.codigo, this.dataArchivo);

        } catch (Exception e) {
            System.out.println("Clase Escaner>GeneraDatos()=>" + e.getMessage());
            e.printStackTrace();
        }
    }

    private void GeneraArchivoErrores(List<String> c, InfoArchivo a) {
        try {
//            System.out.println("ENTRO AL METODO GeneraArchivoErrores\n"
//                    + "\n" + c.isEmpty() + "\n" + a.toString());

            int i = 0;
            int j = 0;
            String str1, str2;
            // ArrayList<String> tmp = new ArrayList();
            if (c.isEmpty()) {
                System.out.println("c.isEmpty()->" + c.isEmpty());
                System.out.println("Parametros Vacios");
                return;
            }
            //System.out.println("Parametros contiene data");

            for (String ln : c) {

                j = c.lastIndexOf(i);
                
                
               // str1 = String.format("%05d", j);
                //str2=null;

                //str2 = str1 + ln;
                if ((ln.isEmpty())) {
                    
                    j = i;
                
                } else {
                    i++;
                    j=i;
                }
                str1 = String.format("%05d", j);
                str2 = RemplazaEspacios(ln, str1);
                    this.errores.add(str2);
                // System.out.println(ln);
//                System.out.println("Entro al For de GeneraArchivoErrores\n"
//                        + "\n" + j + "\n" + str2 + "\n");

            }
            //tmp.forEach(e -> System.out.println(e));
            //  System.out.println("RutaNombreErrores ->"+a.rutaNombreErrores);

            Files.write(a.rutaNombreErrores, this.errores);

        } catch (Exception e) {
            System.out.println("Clase Escaner>GeneraArchivoErrores()=>" + e.getMessage());
            e.printStackTrace();

        }

    }

    /**
     * Metodo <b>RemplazaEspacios</b>
     *
     * @param linea
     * @param reemplaza valor con el que se va a reemplazar en la linea
     * @return retorna la linea con la modificacion en el inicio de la linea
     * 00000
     */
    private String RemplazaEspacios(String linea, String reemplaza) {
        Pattern ptr = Pattern.compile("^\\s{5}|^\\n");
        Matcher mtch = ptr.matcher(linea);
        return mtch.replaceAll(reemplaza);
    }

    private void GeneraArchivoCob(InfoArchivo a, List c) {
        try {
            int error = EncontroError(c);

            switch (error) {
                case 0:
                    //File file = new File(a.nombreCob.toString());
                    Files.write(a.nombreCob, c);

                    break;

                case 1:

                    break;

                default:
                    System.out.println("Error en case");
                    break;

            }

        } catch (Exception e) {
            System.out.println("Clase Escaner>GeneraArchivoCob()=>" + e.getMessage());
            e.printStackTrace();
        }
    }

    private int EncontroError(List c) {
        try {
            int resultado = 0;

            return resultado;

        } catch (Exception e) {
            System.out.println("Clase Escaner>RevisaErrores()=>" + e.getMessage());
            e.printStackTrace();

            return -1;
        }
    }

}
