package Boletin1Ficheros_01;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejercicio8 implements Comparator<File> {

    private static final Logger logger = LogManager.getLogger(Ejercicio8.class);

    @Override
    public int compare(File o1, File o2) {
        return Long.compare(o1.length(), o2.length());
    }

    File directorio = new File("C:\\Users\\mmarf\\Desktop\\PruebaFicheros");

    File[] archivos = directorio.listFiles();

    public static void main(String[] args) {

        Ejercicio8 ejercicio = new Ejercicio8();

        logger.info("Inicio del programa");

        if (!ejercicio.directorio.exists()) {
            logger.error("El directorio no existe: {}", ejercicio.directorio);
            return;
        }

        if (ejercicio.archivos == null) {
            logger.error("No se ha podido leer el contenido del directorio");
            return;
        }

        logger.info("Número de archivos encontrados: {}", ejercicio.archivos.length);

        logger.info("Archivos antes de ordenar:");
        for (File archivo : ejercicio.archivos) {
            logger.info("{} - Tamaño: {} bytes",
                    archivo.getName(), archivo.length());
        }

        Arrays.sort(ejercicio.archivos, ejercicio);

        logger.info("Archivos después de ordenar:");
        for (File archivo : ejercicio.archivos) {
            logger.info("{} - Tamaño: {} bytes",
                    archivo.getName(), archivo.length());
        }

        logger.info("Ordenación completada correctamente");
    }
}