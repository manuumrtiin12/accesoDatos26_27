/*package Boletin1Ficheros_01;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejercicio2 {

    private static final Logger logger = LogManager.getLogger(Ejercicio2.class);

    public static void main(String[] args) throws RutaNoValidaException, IOException {

        Scanner sc = new Scanner(System.in);

        System.out.println("Dime la ruta a la que quieres acceder: ");
        String ruta = sc.nextLine();

        File archivo = new File(ruta);

        if (!archivo.exists()) {

            throw new RutaNoValidaException("La ruta no existe");

        } else {

            logger.info("Nombre: " + archivo.getName());
            logger.info("Ruta: " + archivo.getPath());
            logger.info("Ruta absoluta: " + archivo.getAbsolutePath());
            logger.info("Ruta canónica: " + archivo.getCanonicalPath());
            logger.info("Directorio padre: " + archivo.getParent());
            logger.info("Tipo: " + (archivo.isFile() ? "fichero" : "directorio"));
            logger.info("Lectura: " + archivo.canRead());
            logger.info("Escritura: " + archivo.canWrite());
            logger.info("Ejecución: " + archivo.canExecute());
            logger.info("Oculto: " + archivo.isHidden());
            logger.info("Tamaño en bytes: " + archivo.length());

            logger.info("Número de elementos: "
                    + (archivo.isDirectory() ? archivo.list().length : "no es un directorio"));

            logger.info("Fecha de última modificación: "
                    + new Date(archivo.lastModified()));
        }
    }
}

class RutaNoValidaException extends Exception {

    public RutaNoValidaException(String mensaje) {
        super(mensaje);
    }
}
*/