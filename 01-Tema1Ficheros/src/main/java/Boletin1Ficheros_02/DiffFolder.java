package Boletin1Ficheros_02;

import java.io.File;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiffFolder {

    private File directorio1;
    private File directorio2;

    private static final Logger logger = LogManager.getLogger(DiffFolder.class);

    public DiffFolder(String ruta1, String ruta2) throws DiffFolderException {

        this.directorio1 = new File(ruta1);
        this.directorio2 = new File(ruta2);

        if (directorio1 != null && directorio2 != null
                && directorio1.isDirectory() && directorio2.isDirectory()) {

            setFolders(directorio1, directorio2);

        } else {
            throw new DiffFolderException("No son carpetas válidas.");
        }
    }

    public void setFolders(File folder1, File folder2) throws DiffFolderException {

        if (folder1 != null && folder2 != null
                && folder1.exists() && folder1.isDirectory()
                && folder2.exists() && folder2.isDirectory()) {

            this.directorio1 = folder1;
            this.directorio2 = folder2;

            logger.info("Ambos directorios están bien.");

        } else {
            throw new DiffFolderException(
                    "Alguno de los directorios no existe o no es un directorio.");
        }
    }
    
    public Collection<ResultadoComparacion> comparame() {

        int cantidad1 = directorio1.listFiles().length;
        int cantidad2 = directorio2.listFiles().length;

        logger.info("Cantidad de elementos del directorio 1: " + cantidad1);
        logger.info("Cantidad de elementos del directorio 2: " + cantidad2);

        return null;
    }

    public File getFolder1() {
        return getDirectorio1();
    }

    public File getFolder2() {
        return getDirectorio2();
    }

    public File getDirectorio1() {
        return directorio1;
    }

    public File getDirectorio2() {
        return directorio2;
    }

    public void setDirectorio1(File directorio1) {
        this.directorio1 = directorio1;
    }

    public void setDirectorio2(File directorio2) {
        this.directorio2 = directorio2;
    }

    @Override
    public String toString() {
        return "DiffFolder [directorio1=" + directorio1
                + ", directorio2=" + directorio2 + "]";
    }
}