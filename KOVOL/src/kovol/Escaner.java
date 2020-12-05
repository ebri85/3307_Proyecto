/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kovol;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>Clase Escaner</b>
 * <p>
 * Esta clase se encarga de analizar los diferentes procesos por medio de los
 * método que se van agregando</p>
 *
 * @author Esau Brizuela
 */
public class Escaner {

    protected List<String> codigo = Collections.emptyList();
    protected ArrayList<String> errores = new ArrayList();
    protected ArrayList<String> reservadas;
    protected InfoArchivo dataArchivo;
    int resultado;

    boolean error = false;

    /**
     *
     * @param c List c
     * @param r ArrayListr
     * @param a InfoArchivo a
     */
    public Escaner(List<String> c, ArrayList<String> r, InfoArchivo a) {
        this.codigo = c;
        this.reservadas = r;
        this.dataArchivo = a;
    }

    /**
     * <b>Método GeneraDatos()</b>
     * <p>
     * Método que genera diferentes acciones Generar ArchivoCob Generar
     * ArchivoErrores</p>
     *
     */
    protected void GeneraDatos() {
        try {
            boolean error = false;
            String mensaje;

            while (!(error)) {

                // System.out.println(this.dataArchivo.toString());
                GeneraArchivoCob(this.dataArchivo, this.codigo);
                error = true;
                mensaje = ValidaLineas(this.codigo);

            }
            GeneraArchivoErrores(this.codigo, this.dataArchivo);

        } catch (Exception e) {
            System.out.println("Clase Escaner>GeneraDatos()=>" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Metodo<b>GeneraArchivoErrores()</b>
     *
     * @param c Lista de codigo
     * @param a de tipo
     */
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
                    j = i;
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
     * Metodo para incluir los numeros del archivo de Errores
     *
     * @param linea String linea
     * @param reemplaza valor con el que se va a reemplazar en la linea
     * @return retorna la linea con la modificacion en el inicio de la linea
     * 00000
     */
    private String RemplazaEspacios(String linea, String reemplaza) {
        Pattern ptr = Pattern.compile("^\\s{5}|^\\n");
        Matcher mtch = ptr.matcher(linea);
        return mtch.replaceAll(reemplaza);
    }

    /**
     * <b>ValidaLineas</b>
     * Metodo que empieza a validar lineas y empezar a generar los errores.
     *
     * @param c List c
     * @return 0 - para tamano correcto, 1 tamano incorrecto, -1 error
     */
    private String ValidaLineas(List<String> c) {
        try {

            String mensajeError = "";
            for (String linea : c) {
                int codigo = 0;
                int numLinea = c.indexOf(linea);
                String subStr1 = linea.substring(0, 7); //columna 0-7
                String margenA = linea.substring(7, 11);
                String margenB = linea.substring(11, 72);
                String otroCom = linea.substring(72, 81);

                // boolean continua = true;
                while (codigo >= 0) {
                    boolean error = false;

                    switch (codigo) {
                        case 0:
                            

                            break;

                        case 1:
     

                            
                            break;

                        case 2:
                           
                            break;
                        case 3:
                       
                            break;

                        case 4:
                           

                            break;

                        case 5:

                            break;
                        default:
                            codigo = -1;
                            break;
                    }

                }
            }
            System.out.println(mensajeError);

            return mensajeError;

//                Pattern ptr = Pattern.compile("(\w).$+",Pattern.CASE_INSENSITIVE);
//                 Matcher mtch = ptr.matcher(linea);
            // mtch.matches();
            // System.out.println("Linea->"+linea+"\nTamano linea -> "+tamano+"\nSe encontro . =>"+mtch.find());
        } catch (Exception e) {
            System.out.println("Clase Escaner>ValidaTamanoLinea()=>" + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

   

  

    /**
     *
     * < b>Metodo ValidaMargenA</b>
     * <p>
     * Este metodo valida que la columna 7 sea un espacio o lleve asterisco o un
     * guion</p>
     *
     * @param ln hace referencia al string de la linea
     * @param j hace referencia a el numero de linea que esta siendo evaluada
     * @return 6 que es el codigo del error si lo encuentra, si no lo encuentra
     * retorna 0;
     */
    private boolean ValidaMargenA(String ln, int j) {

        try {

            // String margenA = ln.substring(7, 10);
            //String ptr = "IDENTIFICATION|ENVIRONMENT|DATA|PROCEDURE| DIVISION|PROGRAM-ID|AUTHOR|WORKING-STORAGE| SECTION.|01|(([a-z]*)\\-([a-z]*))";
            String str = ln;
            boolean encontro= false;
            List<String> listaEncontradas = new ArrayList<String>();
            int i = 0;
            int k = 0;

            Pattern pat = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
            Matcher m = pat.matcher(ln);
            i = m.start();

            if (i > 7 && i < 11) {
                for (String s : this.reservadas) {
                    String p = s;
                //    encontro = BuscaPtr(p, str);                   

                    Object o = (encontro) ? listaEncontradas.add(s) : null;                   
                }
                if(encontro){
                     Pattern.compile("\\W\\.\\s{?}", i).matcher(ln);
                    
                }

                return false;
            } else {
                String mensajeError = "\n" + "\tError 0006=> Error No se respeta el Margen A";
                this.errores.set(j, this.errores.get(j).concat(mensajeError));
                return true;
            }

        } catch (Exception e) {
            System.out.println("Clase Escaner>ValidaMargenA()=>" + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    /**
     *
     * < b>Metodo ValidaMargenB</b>
     * <p>
     * Inicialmente el metodo valida que no contenga nada en el margen A,</p>
     ** <p>
     * luego, busca en margen B la existencia de palabras Reservadas</p>
     *
     * @param ln hace referencia al string de la linea
     * @param j hace referencia a el numero de linea que esta siendo evaluada
     * @return retorna en un Objeto de tipo List las palabras reservadas que
     * encontro.
     *
     */
    private List<String> ValidaMargenB(String ln, int j) {

        try {
            String margenA = ln.substring(7, 10);
            String margenB = ln.substring(11, 71);
            List<String> listaEncontradas = new ArrayList<String>();

            String str = margenB;
            boolean encontro = false;

            if (margenA.isEmpty()) {
                for (String s : this.reservadas) {
                    String ptr = s;
                   // encontro = BuscaPtr(ptr, str);

                    Object o = (encontro) ? listaEncontradas.add(s) : null;
                }
                return new ArrayList<String>(listaEncontradas);
            }
            return new ArrayList<String>();

        } catch (Exception e) {
            System.out.println("Clase Escaner>ValidaMargenB()=>" + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    private void GeneraArchivoCob(InfoArchivo a, List<String> c) {
        try {
            int error = 0;

            switch (error) {
                case 0:
                    //File file = new File(a.nombreCob.toString());
                    //  int n = EncontroError(c);
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



  

}
