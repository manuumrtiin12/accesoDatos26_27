package Boletin1Ficheros_01;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejercicio4 {

    private static final Logger logger = LogManager.getLogger(Ejercicio4.class);

    void mostrarInformacion(String nombreYRutaFichero) throws RutaNoValidaException {

        File f = new File(nombreYRutaFichero);

        if (!f.exists()) {
            throw new RutaNoValidaException("No existe: " + nombreYRutaFichero);
        }

        if (f.isFile()) {
            logger.info("Nombre: {}", f.getName());
            logger.info("Ruta: {}", f.getAbsolutePath());
        } else {
            File[] hijos = f.listFiles();

            if (hijos != null) {
                for (File f1 : hijos) {
                    mostrarInformacion(f1.getAbsolutePath());
                }
            }
        }
    }

    public static void main(String[] args) throws RutaNoValidaException {
        Ejercicio4 ejercicio = new Ejercicio4();
        ejercicio.mostrarInformacion("C:\\Users\\mmarf\\Desktop\\DAM2\\acceso_datos\\accesoDatos26_27\\01-Tema1Ficheros\\src\\main\\resources");
    }
}