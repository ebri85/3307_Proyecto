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

    private String archivo;
    private Path rutaJar;
    private Path rutaCompletaJar;
    private String DirectorioProyecto;
    private String rutaArchivo;


    
    public InfoArchivo() {
    }

    public InfoArchivo(String archivo,Path rutaJar, Path rutaCompletaJar, String DirectorioProyecto, String rutaArchivo, String archivo_Cob, String archivo_Kovol, String archivo_Errores) {
        this.rutaJar = rutaJar;
        this.archivo = archivo;
        this.rutaCompletaJar = rutaCompletaJar;
        this.DirectorioProyecto = DirectorioProyecto;
        this.rutaArchivo = rutaArchivo;
        
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Path getRutaJar() {
        return rutaJar;
    }

    private void setRutaJar() {
        Path rutaJar= Paths.get("");
        this.rutaJar = rutaJar;
    }

    public Path getRutaCompletaJar() {
        return rutaCompletaJar;
    }

    private void setRutaCompletaJar() {
        Path rutaCompletaJar = this.rutaJar.toAbsolutePath();
        this.rutaCompletaJar = rutaCompletaJar;
    }

    public String getDirectorioProyecto() {
        return DirectorioProyecto;
    }

    private void setDirectorioProyecto() {
        String DirectorioProyecto = this.rutaCompletaJar.toString();
        this.DirectorioProyecto = DirectorioProyecto;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    private void setRutaArchivo() {
        String rutaArchivo = Paths.get("").toAbsolutePath().resolve(this.archivo).toString();
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public String toString() {
        return "InfoArchivo{" + "archivo=" + archivo + ", rutaJar=" + rutaJar + ", rutaCompletaJar=" + rutaCompletaJar + ", DirectorioProyecto=" + DirectorioProyecto + ", rutaArchivo=" + rutaArchivo + '}';
    }

  public String NombreArchivo()
  {
      Path resultado = Paths.get("").toAbsolutePath().resolve(this.archivo).getFileName();
      return resultado.toString();
  }
  
   public Path RutaNombreCob()
  {
      Path rt = Paths.get("").toAbsolutePath().resolve(this.archivo);
      Path resultado = rt.getFileName();
      
      String rtCob = resultado.toString()+".cob";
      Path ruta = Paths.get(rtCob);

      
      return ruta;
  }
    public Path RutaNombreErrores()
  {
      Path rt = Paths.get("").toAbsolutePath().resolve(this.archivo);
      Path resultado = rt.getFileName();
      
      String rtErrores = resultado.toString().concat("-errores.txt");
      Path ruta = Paths.get(rtErrores);
      
      return ruta;
  }
    
    
}
