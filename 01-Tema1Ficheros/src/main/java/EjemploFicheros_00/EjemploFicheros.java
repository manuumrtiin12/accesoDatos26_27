package EjemploFicheros_00;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EjemploFicheros {

    private static final Logger logger = LogManager.getLogger(EjemploFicheros.class);

    public static void main(String[] args) {

        String rutaDirectorio = "src/main/resources";
        File directorio = new File(rutaDirectorio);

        // Referencio a un fichero dentro del directorio resources
        File fichero = new File(directorio, "fichero.txt");

        try {
            boolean creado = fichero.createNewFile(); // Aquí creo el fichero

            if (creado) {
                logger.info("Fichero creado correctamente");
            } else {
                logger.info("El fichero ya existía");
            }

        } catch (IOException e) {
            logger.error("Error al crear fichero: " + e.getMessage());
        }
    }
}
