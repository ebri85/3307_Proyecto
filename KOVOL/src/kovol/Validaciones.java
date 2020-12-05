/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kovol;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta clase va a contener todas las validaciones requeridas para evaluar cada
 * linea
 *
 * @author ebri_
 */
public class Validaciones {

    private int numeroLinea;
    private String linea;
    private boolean encontroError;
    private String error;
    protected ArrayList<String> reservadas;

    public Validaciones() {
    }

    public Validaciones(int numeroLinea, String linea, ArrayList<String> reservadas) {
        this.numeroLinea = numeroLinea;
        this.linea = linea;
        this.reservadas = reservadas;
    }

    public boolean isEncontroError() {
        return encontroError;
    }

    private void setEncontroError(boolean encontroError) {
        this.encontroError = encontroError;
    }

    public int getNumeroLinea() {
        return numeroLinea;
    }

    private void setNumeroLinea(int numeroLinea) {
        this.numeroLinea = numeroLinea;
    }

    public String getLinea() {
        return linea;
    }

    private void setLinea(String linea) {

        this.linea = linea;

    }

    public String getError() {
        return error;
    }

    private void setError(String error) {
        this.error += "\t" + error + "\n";
    }

    /**
     *
     * <h2>GeneraValidaciones</h2>
     * Metodo que va a llamar a el resto de metodos y va a ir alimentando el
     * String Error
     */
    private void GeneraValidaciones() {
        try {
            boolean _error = true;

            while (_error) {
                SeisColumnas();
                Columna7();
                MargenA();
                MargenB();
                _error = false;
            }

        } catch (Exception e) {
            System.out.println("Clase Validaciones>GeneraValidaciones()=>" + e.getMessage());
            e.printStackTrace();
        }
    }

    private int TamanoLinea() {
        try {
            boolean error = false;
            String mensajeError = "Error 0001 -> El tamano de la linea de codigo excede lo permitido";
            if (linea.length() > 80) {
                setError(mensajeError);

                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Clase TamanoLinea>GeneraValidaciones()=>" + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * <h2>Metodo SeisColumnas</h2>
     * Este metodo revisa las primeras seis columnas de la linea.
     */
    private int SeisColumnas() {
        try {
            boolean error = false;
            String mensajeError = "Error 0002 -> Primeras seis columnas no deben de contener caracteres";
            String ptr = "^\\s{5}";
            error = MatchPtr(ptr, this.linea);

            if (error) {
                setError(mensajeError);
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Clase Validaciones>SeisColumnas()=>" + e.getMessage());
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * <h2>Metodo SeisColumnas</h2>
     * Este metodo revisa las primeras seis columnas de la linea. que esta
     * columna tenga '*'|'-'|SPACE
     *
     */
    private int Columna7() {
        try {
            boolean error = false;
            String mensajeError = "Error 0003 -> Columna 7 contiene un caracter no valido";
            char columna7 = linea.charAt(6);

            if (columna7 == '*') {
                error = false;
            } else if (columna7 == '-') {
                error = BuscaPtr("\\.", linea);
                if (error) {
                    MatchPtr("\\W\\.\\s|\\W\\.", linea);
                } else if (columna7 == 32) {
                    error = false;
                } else {
                    error = true;
                    setError(mensajeError);
                    return 1;
                }
            }
            return 0;

        } catch (Exception e) {
            System.out.println("Clase Validaciones>Columna7()=>" + e.getMessage());
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * <h2>Metodo MargenA</h2>
     *
     * se valida que la palabra reservada inicie entre el margen A
     *
     * @return retorna 0 si no encuentra error, o existen más de 1 palabra
     * reservada. o 1 si encuentra error, y agrega el mensaje Error.
     *
     */
    private int MargenA() {

        try {
            String margenA = linea.substring(7, 11);
            String mensajeError = "Error 0004 -> Error de estructura en Margen A";
            boolean encuentra = false;
            int posicion = 0;
            int contReservadas = 0;

            if (margenA.isEmpty()) {
                return 0;
            } else {
                for (String str : this.reservadas) {

                    encuentra = BuscaPtr(str, linea);
                    contReservadas += (encuentra) ? 1 : 0;
                    posicion = RetornaIndexMatch(str, linea);

                }
                if (contReservadas > 1) {
                    return 0;
                }

                if (encuentra == true && posicion > 12) {

                    setError(mensajeError);

                }

            }

            return 0;

        } catch (Exception e) {

            System.out.println("Clase Validaciones>MargenA()=>" + e.getMessage());
            e.printStackTrace();
            return -1;
        }

    }

    /**
     * <h2>Metodo MargenA</h2>
     *
     * se valida que la palabra reservada inicie entre el margen A
     *
     * @return retorna 0 si no encuentra error, o existen más de 1 palabra
     * reservada. o 1 si encuentra error, y agrega el mensaje Error.
     *
     */
    private int MargenB() {
//--EVALUAR-- hay que modificar MargenB,deberia de cambiar a que se pasa parametro con la palabra si es margenB continua
        try {
            String margenB = linea.substring(12, 73);
            String mensajeError = "Error 0005 -> Error de estructura en Margen B";
            boolean encuentra = false;
            int posicion = 0;
            int contReservadas = 0;

            if (margenB.isEmpty()) {
                return 0;
            } else {
                for (String str : this.reservadas) {

                    encuentra = BuscaPtr(str, linea);
                    contReservadas += (encuentra) ? 1 : 0;
                    posicion = RetornaIndexMatch(str, linea);

                }
                if (contReservadas > 1) {
                    return 0;
                }

                if (encuentra == true && posicion < 73) {

                    setError(mensajeError);

                }

            }

            return 0;

        } catch (Exception e) {

            System.out.println("Clase Validaciones>MargenB()=>" + e.getMessage());
            e.printStackTrace();
            return -1;
        }

    }

    private int ValidaPunto() {
        try {
            String mensajeError = "Error 007 -> Mal sintaxis con el punto";
            boolean encontro = false;

            encontro = BuscaPtr("\\w\\.|\\w\\.\\s", linea);
            if (!encontro) {
                setError(mensajeError);
                return 1;
            }

            return 0;

        } catch (Exception e) {
            System.out.println("Clase Validaciones>ValidaPunto()=>" + e.getMessage());
            e.printStackTrace();
            return -1;
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
            System.out.println("Clase Escaner>Validaciones()=>" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * <b>BuscaPtr</b>
     * Metodo que busca solamente si encuentra el patron en la linea
     *
     * @param patron
     * @param str
     * @return
     */
    private boolean BuscaPtr(String patron, String str) {
        try {

            Pattern ptr = Pattern.compile(patron, Pattern.CASE_INSENSITIVE);
            Matcher match = ptr.matcher(str);
            return match.find();

        } catch (Exception e) {
            System.out.println("Clase Validaciones>MatchPtr()=>" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * <b>RetornaIndexMatch</b>
     * Metodo que busca solamente si encuentra el patron en la linea y retorna
     * un entero con la posicion inicial del match
     *
     * @param patron
     * @param str
     * @return
     */
    private int RetornaIndexMatch(String patron, String str) {
        try {

            Pattern ptr = Pattern.compile(patron, Pattern.CASE_INSENSITIVE);
            Matcher match = ptr.matcher(str);
            match.find();
            return match.start();

        } catch (Exception e) {
            System.out.println("Clase Validaciones>RetornaIndexMatch()=>" + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * *
     *
     * @param patron
     * @param str
     * @return
     */
    private Pattern ObtieneMatchPtr(String patron, String str) {
        try {

            Pattern ptr = Pattern.compile(patron, Pattern.CASE_INSENSITIVE);
            Matcher match = ptr.matcher(str);
            return match.pattern();

        } catch (Exception e) {
            System.out.println("Clase Escaner>Validaciones()=>" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
