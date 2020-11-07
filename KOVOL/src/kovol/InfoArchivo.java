/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kovol;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author ebri_
 */
public class InfoArchivo {

    String archivo;
    String rutaArchivo;
    Path rutaJar = Paths.get("");
    Path rutaCompletaJar = rutaJar.toAbsolutePath();
    String DirectorioProyecto = rutaCompletaJar.toString();
    String nombArchivo;
    String rutaArchivoString;
    Path nombreCob;
    Path rutaNombreErrores;

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

//    private void DefineRutaDir() {
//        try {
//
//           his.rutaArchivo= ObtenerRutaArchivoToString();
//
//        } catch (Exception e) {
//            System.out.println("Clase InfoArchivo>DefineRutaDir()=>" + e.getMessage());
//            e.printStackTrace();
//
//        }
//
//    }
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
            return resultado.toString();

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
            Path rt = Paths.get("").toAbsolutePath().resolve(this.archivo);
            Path resultado = rt.getFileName();

            String rtCob = resultado.toString() + ".cob";
            Path ruta = Paths.get(rtCob);
            return ruta;

        } catch (Exception e) {
            System.out.println("Clase InfoArchivo>NombreCob()=>" + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    private Path RutaNombreErrores() {

        try {
            Path rt = Paths.get("").toAbsolutePath().resolve(this.archivo);
            Path resultado = rt.getFileName();

            String rtErrores = resultado.toString().concat("-errores.txt");
            Path ruta = Paths.get(rtErrores);

            return ruta;

        } catch (Exception e) {
            System.out.println("Clase InfoArchivo>RutaNombreErrores()=>" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
