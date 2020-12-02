/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kovol;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <b>* Clase InfoArchivo</b>
 * Extrae toda la iformacion necesaria, del Kovol.jar para apartir de esto
 * generar el resto de archivos.
 *
 * @author Esau Brizuela
 */
public class InfoArchivo {

    protected String archivo;
    protected String rutaArchivo;
    protected Path rutaJar = Paths.get("");
    protected Path rutaCompletaJar = rutaJar.toAbsolutePath();
    protected String DirectorioProyecto = rutaCompletaJar.toString();//ruta de donde se ejecuta el JAR
    protected String nombArchivo;
    protected String rutaArchivoString;
    protected Path nombreCob;
    protected Path rutaNombreErrores;

    /**
     *InfoArchivo(String str)
     * @param str String str
     */
    public InfoArchivo(String str) {

        this.archivo = str;

    }

    protected void GeneraDatos() {
        try {
            nombArchivo = NombreArchivo();
            rutaArchivoString = ObtenerRutaArchivoToString();
            nombreCob = NombreCob();
            rutaNombreErrores = RutaNombreErrores();

        } catch (Exception e) {
            System.out.println("Clase InfoArchivo>GeneraDatos()=>" + e.getMessage());
//            e.printStackTrace();
//
        }
    }


    /**
     *String toString()
     * @return -String
     */
    @Override
    public String toString() {
        return "Detalles del Archivo{\n"
                + "archivo=" + archivo
                + "\n rutaJar=" + rutaJar
                + "\n rutaCompletaJar=" + rutaCompletaJar
                + "\n DirectorioProyecto=" + DirectorioProyecto;

    }

    private String NombreArchivo() {
        try {
            Path resultado = Paths.get("").toAbsolutePath().resolve(this.archivo).getFileName();
            
            Pattern ptr = Pattern.compile("[a-z0-9&&[^\\W]]*",Pattern.CASE_INSENSITIVE);
            Matcher match = ptr.matcher(resultado.toString()) ;
            if(match.matches()){
                
            return resultado.toString();
            }else{
                throw new Exception("El nombre del archivo no cumple", null);
            }

        } catch (Exception e) {
            System.out.println("Clase InfoArchivo>NombreArchivo()=>" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private String ObtenerRutaArchivoToString() {
        try {

            Path rutaArchivo = Paths.get("").toAbsolutePath().resolve(this.archivo);
            return rutaArchivo.toString();

        } catch (Exception e) {
            System.out.println("Clase InfoArchivo>ObtenerRutaArchivoToString()=>" + e.getMessage());
            e.printStackTrace();
            return null;

        }
    }

    private Path NombreCob() {

        try {
            String extension = ".cob";
            Path rt = Paths.get("").toAbsolutePath().resolve(this.archivo);

            String resultado = rt.getFileName().toString();

            String rtErrores = RemueveExtensionKovol(resultado, extension);
            Path ruta = Paths.get(rtErrores);
            // System.out.println("ruta-> " + ruta);
            return ruta;
        } catch (Exception e) {
            System.out.println("Clase InfoArchivo>NombreCob()=>" + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    private Path RutaNombreErrores() {

        try {
            String extension = "-errores.txt";
            Path rt = Paths.get("").toAbsolutePath().resolve(this.archivo);

            String resultado = rt.getFileName().toString();

            String rtErrores = RemueveExtensionKovol(resultado, extension);
            Path ruta = Paths.get(rtErrores);
            // System.out.println("ruta-> " + ruta);
            return ruta;

        } catch (Exception e) {
            System.out.println("Clase InfoArchivo>RutaNombreErrores()=>" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private String RemueveExtensionKovol(String nombreArchivo, String nuevaExtension) {
        try {
            boolean error = true;
            String resultado = null;
            while (error) {
                Pattern ptr = Pattern.compile(".kovol", Pattern.CASE_INSENSITIVE);
                Matcher match = ptr.matcher(nombreArchivo);
                resultado = match.replaceAll(nuevaExtension);
                error = false;

            };

            return resultado;

        } catch (Exception e) {
            System.out.println("Clase InfoArchivo>ModificaExtension()=>" + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    protected String GeneraExtensionExe(String nombreArchivo, String nuevaExtension) {
        try {
            boolean error = true;
            String resultado = null;
            while (error) {
                Pattern ptr = Pattern.compile(".cob", Pattern.CASE_INSENSITIVE);
                Matcher match = ptr.matcher(nombreArchivo);
                resultado = match.replaceAll(nuevaExtension);
                error = false;

            };

            return resultado;

        } catch (Exception e) {
            System.out.println("Clase InfoArchivo>ModificaExtension()=>" + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }
}
