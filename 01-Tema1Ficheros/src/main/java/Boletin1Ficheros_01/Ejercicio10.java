
package Boletin1Ficheros_01;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejercicio10 {

    private static final Logger logger = LogManager.getLogger(Ejercicio10.class);
    

    public static void filtrarExtensiones(File directorio) {

        File[] archivosDirectorio = directorio.listFiles();

        if (archivosDirectorio != null) {

            for (File archivo : archivosDirectorio) {

                if (archivo.isDirectory()) {

                    filtrarExtensiones(archivo);

                } else {

                    String nombre = archivo.getName();
                    String extension = "";

                    if (nombre.endsWith(".pdf")) {
                        extension = "pdf";
                    } else if (nombre.endsWith(".jpg")) {
                        extension = "jpg";
                    } else if (nombre.endsWith(".txt")) {
                        extension = "txt";
                    }

                    if (!extension.isEmpty()) {

                        File carpeta = new File(directorio, extension);

                        if (!carpeta.exists()) {
                            carpeta.mkdir();
                        }

                        File destino = new File(carpeta, nombre);

                        if (archivo.renameTo(destino)) {
                            logger.info("Archivo movido: " + nombre);
                        } else {
                            logger.error("No se pudo mover: " + nombre);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        File directorio = new File("C:\\Users\\mmanu\\Desktop\\PruebaEj10");

        filtrarExtensiones(directorio);
    }
}
