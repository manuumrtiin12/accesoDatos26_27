
package Boletin1Ficheros_02;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DiffFolder {

    private File directorio1;
    private File directorio2;

    private static final Logger logger = LogManager.getLogger(DiffFolder.class);

    public DiffFolder(String ruta1, String ruta2) throws DiffFolderException {
        setFolders(new File(ruta1), new File(ruta2));
    }

    public void setFolders(File folder1, File folder2) throws DiffFolderException {

        if (folder1 != null && folder2 != null
                && folder1.exists() && folder1.isDirectory()
                && folder2.exists() && folder2.isDirectory()) {

            this.directorio1 = folder1;
            this.directorio2 = folder2;

            logger.info("Ambos directorios están bien.");

        } else {
            logger.error("Alguno de los directorios no existe o no es válido.");
            throw new DiffFolderException(
                    "Alguno de los directorios no existe o no es un directorio.");
        }
    }

    public File getFolder1() {
        return directorio1;
    }

    public File getFolder2() {
        return directorio2;
    }

    public Collection<ResultadoComparacion> comparame() {

        Collection<ResultadoComparacion> resultados = new ArrayList<>();

        File[] archivos1 = obtenerArchivos(directorio1);
        File[] archivos2 = obtenerArchivos(directorio2);

        if (archivos1 == null || archivos2 == null) {
            return resultados;
        }

        logger.info("Cantidad de archivos del directorio 1: " + archivos1.length);
        logger.info("Cantidad de archivos del directorio 2: " + archivos2.length);

        if (archivos1.length >= archivos2.length) {
            compararDirectorios(archivos1, archivos2, true, resultados);
        } else {
            compararDirectorios(archivos2, archivos1, false, resultados);
        }

        return resultados;
    }

    private File[] obtenerArchivos(File directorio) {

        File[] elementos = directorio.listFiles();

        if (elementos == null) {
            logger.error("No se ha podido leer el directorio: " + directorio);
            return null;
        }

        Collection<File> archivos = new ArrayList<>();

        for (File elemento : elementos) {
            if (elemento.isFile()) {
                archivos.add(elemento);
            }
        }

        return archivos.toArray(new File[0]);
    }

    private void compararDirectorios(File[] principal, File[] secundario,
            boolean principalEsDirectorio1,
            Collection<ResultadoComparacion> resultados) {

        for (File archivoPrincipal : principal) {

            File archivoEncontrado = buscarArchivo(archivoPrincipal.getName(), secundario);

            if (archivoEncontrado == null) {

                ValorComparacion valor;

                if (principalEsDirectorio1) {
                    valor = ValorComparacion.FALTA_EN_2;
                } else {
                    valor = ValorComparacion.FALTA_EN_1;
                }

                resultados.add(new ResultadoComparacion(
                        archivoPrincipal.getName(), valor));

            } else {

                long fechaPrincipal = archivoPrincipal.lastModified();
                long fechaSecundario = archivoEncontrado.lastModified();

                ValorComparacion valor;

                if (fechaPrincipal == fechaSecundario) {
                    valor = ValorComparacion.IGUALES;
                } else if (principalEsDirectorio1) {
                    if (fechaPrincipal > fechaSecundario) {
                        valor = ValorComparacion.MENOS_NUEVO_EN_2;
                    } else {
                        valor = ValorComparacion.MENOS_NUEVO_EN_1;
                    }
                } else {
                    if (fechaPrincipal > fechaSecundario) {
                        valor = ValorComparacion.MENOS_NUEVO_EN_1;
                    } else {
                        valor = ValorComparacion.MENOS_NUEVO_EN_2;
                    }
                }

                resultados.add(new ResultadoComparacion(
                        archivoPrincipal.getName(), valor));
            }
        }

        for (File archivoSecundario : secundario) {

            if (buscarArchivo(archivoSecundario.getName(), principal) == null) {

                ValorComparacion valor;

                if (principalEsDirectorio1) {
                    valor = ValorComparacion.FALTA_EN_1;
                } else {
                    valor = ValorComparacion.FALTA_EN_2;
                }

                resultados.add(new ResultadoComparacion(
                        archivoSecundario.getName(), valor));
            }
        }
    }

    private File buscarArchivo(String nombre, File[] archivos) {

        for (File archivo : archivos) {
            if (archivo.getName().equals(nombre)) {
                return archivo;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "DiffFolder [directorio1=" + directorio1
                + ", directorio2=" + directorio2 + "]";
    }
}
