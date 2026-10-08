package Boletin1Ficheros_01;

import java.io.File;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejercicio5 {

    private static final Logger logger = LogManager.getLogger(Ejercicio5.class);

    public static void main(String[] args) {
        File userHome = new File("C:\\Users\\mmanu\\Desktop\\DAM 2\\accesoDatos\\accesoDatos26_27\\01-Tema1Ficheros\\src\\main\\java");

        if (userHome.exists() && userHome.isDirectory()) {
            logger.info("Calculando el tamaño recursivo de: {}", userHome.getAbsolutePath());
            
            double tamanoTotalBytes = calcularTamanoDirectorio(userHome);
            
            mostrarTamanoFormateado(tamanoTotalBytes);
        } else {
            logger.error("El directorio no existe o no es válido.");
        }
    }

  
    public static double calcularTamanoDirectorio(File dir) {
        double totalBytes = 0;

        File[] archivos = dir.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {

                if (archivo.isFile()) {
                    totalBytes += archivo.length();

                } else if (archivo.isDirectory()) {
                    totalBytes += calcularTamanoDirectorio(archivo);
                }
            }
        }

        return totalBytes;
    }

   
    public static void mostrarTamanoFormateado(double bytes) {
        double kb = bytes / 1024.0;
        double mb = kb / 1024.0;

        if (mb >= 1.0) {
            double mbRedondeado = Math.round(mb * 100.0) / 100.0;
            logger.info("MB: {}", mbRedondeado);
        } else if (kb >= 1.0) {
            double kbRedondeado = Math.round(kb * 100.0) / 100.0;
            logger.info("KB: {}", kbRedondeado);
        } else {
            logger.info("Bytes: {}", bytes);
        }
    }
}
