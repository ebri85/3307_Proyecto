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
                            /**
                             * Case 0: valida que el tamaño de la linea no
                             * exceda los 80 caracteres o columnas, si la linea
                             * esta vacia, se ignora y se continua con la
                             * siguiente
                             */
                            if (linea.length() > 80) {
                                mensajeError = "\n" + "\tError 0001=> La instruccion  contiene mas de 80 columnas";
                                this.errores.set(numLinea, linea.concat(mensajeError));
                                codigo++;
                            } else if (linea.isEmpty()) {
                                codigo = -1;
                            }

                            break;

                        case 1:
                            /**
                             * Case 1: valida las primeras seis columnas, las
                             * cuales deben de estar en blanco.
                             */
                            error = ValidaEspacioBlanco(linea, numLinea);

                            codigo++;
                            break;

                        case 2:
                            /**
                             * Este case valida columna 7, si encuentra '*' no
                             * revisa la linea y continua con la siguiente linea
                             * '-' no evalua la linea actual y busca en las
                             * siguientes el punto (el cual debe de estar de
                             * forma correcta)
                             *
                             */

                            char chr = 0;
                            boolean validaPunto = false;
                            chr = ValidaColumnaSiete(linea, numLinea);
                            if (chr == '*') {
                                codigo = -1;
                            } else if (chr == '-') {
                                String ptr = ".";
                                int cont = numLinea;
                                while (!error) {
                                    error = MatchPtr(ptr, c.get(cont));
                                    if (error) {
                                        validaPunto = MatchPtr("\\w\\.\\s", c.get(cont));
                                        if (!validaPunto) {
                                            mensajeError = "\n" + "\tError 0004=> El punto no es precedido por un caracter y seguido de un espacio";
                                            this.errores.set(numLinea, c.get(cont).concat(mensajeError));
                                            mensajeError = c.get(cont).concat(mensajeError);
                                        }
                                    }
                                    cont++;
                                }
                                if (!error) {
                                    mensajeError = "\n" + "\tError 0003=> La linea de continuacion no cumple con los criterios del punto".concat(mensajeError);
                                    this.errores.set(numLinea, linea.concat(mensajeError));

                                }
                                codigo++;
                            } else if (chr == 32) {
                                codigo++;
                            } else {
                                mensajeError = "\n" + "\tError 0005=> Esta columna no cumple con lo requerido";
                                this.errores.set(numLinea, linea.concat(mensajeError));
                                codigo++;
                            }
                            break;
                        case 3:
                            /**
                             * *
                             * En este case se valida el MargenA dentro de la
                             * Validacion se determinan que existan las unicas
                             * palabras reservadas que pueden estar dentro de
                             * este.
                             */
                            if (margenA.isEmpty()) {
                                codigo++;
                                break;
                            } else {
                                error = ValidaMargenA(linea, numLinea);
                            }

                            break;

                        case 4:
                            /**
                             * En este case se valida el margenB, considerando
                             * la cantidad de palabras reservadas encontradas Si
                             * es > 1, se debe determinar si son reservadas
                             * Kovol o Cobol. si son de Cobol se terminan las
                             * validaciones y se continua con la siguiente linea
                             * de codigo.
                             *
                             */
                            int cont = 0;
                            List<String> list = ValidaMargenB(linea, numLinea);

                            if (list.size() > 1) {
                                codigo = -1;
                                break;
                            }

                            codigo++;

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
     * < b>Metodo ValidaEspacioBlanco</b>
     * <p>
     * Este metodo valida que los primeros 6 espacios de la linea de codigo se
     * encuentren en blanco</p>
     *
     * @param ln hace referencia al string de la linea
     * @param j hace referencia a el numero de linea que esta siendo evaluada
     * @return <b>true</b> si las primeras 6 columnas estan en blanco,
     * <b>false</b>si no hay espacios en blanco en las primeras 6 columnas
     *
     */
    private boolean ValidaEspacioBlanco(String ln, int j) {

        try {
            String ptr = "^\\s{5}";
            String str = ln;

            boolean encontro = MatchPtr(ptr, str);

            String mensajeError = "\n" + "\tError 0002=> Contiene caracteres en las primeras 6 columnas de la linea";
            Object o = (encontro) ? null : this.errores.set(j, ln.concat(mensajeError));

            return encontro;

        } catch (Exception e) {
            System.out.println("Clase Escaner>ValidaEspacioBlanco()=>" + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    /**
     *
     * < b>Metodo ValidaColumnaSiete</b>
     * <p>
     * Este metodo valida que la columna 7 sea un espacio o lleve asterisco o un
     * guion</p>
     *
     * @param ln hace referencia al string de la linea
     * @param j hace referencia a el numero de linea que esta siendo evaluada
     * @return el caracter encontrado, retorna 0 si no encuentra '*' o' -'
     */
    private char ValidaColumnaSiete(String ln, int j) {

        try {
            char columna = ln.charAt(7);
            String ptr = "\\*|}\\-"; //((^\s{6})(\*))
            char encontro = 0;
            if (columna == '*') {
                encontro = '*';
            } else if (columna == '-') {
                encontro = '-';

            } else if (encontro == 32) {
                encontro = 32;
            } else {
                encontro = 0;
            }

            return encontro;

        } catch (Exception e) {
            System.out.println("Clase Escaner>ValidaColumnaSiete()=>" + e.getMessage());
            e.printStackTrace();
            return 0;
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
                    encontro = BuscaPtr(p, str);                   

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
                    encontro = BuscaPtr(ptr, str);

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

    /**
     * <h2>Metodo Match Ptr</h2>
     * <p>
     * Este metodo valida si el string str hace match con el string patron que
     * se le pase a comparar</p>
     * </p> Valida indiferentemente mayusculas o minusculas</p>
     *
     * @param patron
     * @param str
     * @return true o false segun el resultado
     */
    private boolean MatchPtr(String patron, String str) {
        try {

            Pattern ptr = Pattern.compile(patron, Pattern.CASE_INSENSITIVE);
            Matcher match = ptr.matcher(str);
            return match.matches();

        } catch (Exception e) {
            System.out.println("Clase Escaner>MatchPtr()=>" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private boolean BuscaPtr(String patron, String str) {
        try {

            Pattern ptr = Pattern.compile(patron, Pattern.CASE_INSENSITIVE);
            Matcher match = ptr.matcher(str);
            return match.find();

        } catch (Exception e) {
            System.out.println("Clase Escaner>MatchPtr()=>" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private Pattern ObtieneMatchPtr(String patron, String str) {
        try {

            Pattern ptr = Pattern.compile(patron, Pattern.CASE_INSENSITIVE);
            Matcher match = ptr.matcher(str);
            return match.pattern();

        } catch (Exception e) {
            System.out.println("Clase Escaner>MatchPtr()=>" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
