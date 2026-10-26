
package Boletin1Ficheros_02;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainDiffFolder {
	
    private static final Logger logger = LogManager.getLogger(MainDiffFolder.class);


    public static void main(String[] args) {

        try {
            DiffFolder comparador = new DiffFolder(
                    "C:\\Prueba\\Carpeta1",
                    "C:\\Prueba\\Carpeta2");

            Collection<ResultadoComparacion> resultados = comparador.comparame();

            for (ResultadoComparacion resultado : resultados) {
                logger.info(resultado);
            }

        } catch (DiffFolderException e) {
        	logger.info(e.getMessage());
        }
    }
}
