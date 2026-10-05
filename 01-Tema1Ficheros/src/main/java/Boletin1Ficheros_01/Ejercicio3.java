package Boletin1Ficheros_01;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejercicio3 {

	private static final Logger logger = LogManager.getLogger(Ejercicio3.class);

	public static void main(String[] args) {

		File userHome = new File(System.getProperty("user.home"));
		File miDirectorio = new File(userHome, "miDirectorio");

		if (miDirectorio.mkdir()) {
			logger.info("Directorio creado correctamente.");
		} else {
			logger.error("Error al crear el directorio o ya existia.");
		}

		File lectura = new File(miDirectorio, "lectura.txt");
		File normal = new File(miDirectorio, "normal.txt");

		try {
			if (lectura.createNewFile()) {
				logger.info("Fichero lectura.txt creado correctamente.");
			} else {
				logger.error("El fichero lectura.txt no se pudo crear o ya existia.");
			}
		} catch (IOException e) {
			logger.error("Error al crear lectura.txt: " + e.getMessage());
		}

		try {
			if (normal.createNewFile()) {
				logger.info("Fichero normal.txt creado correctamente.");
			} else {
				logger.error("El fichero normal.txt no se pudo crear o ya existia.");
			}
		} catch (IOException e) {
			logger.error("Error al crear normal.txt: " + e.getMessage());
		}

		if (lectura.setReadOnly()) {
			logger.info("Fichero lectura.txt marcado como solo lectura.");
		} else {
			logger.error("No se pudo marcar lectura.txt como solo lectura.");
		}

		logger.info("Permisos de lectura.txt: lectura=" + lectura.canRead() + " escritura=" + lectura.canWrite());
		logger.info("Permisos de normal.txt: lectura=" + normal.canRead() + " escritura=" + normal.canWrite());

		File renombrado = new File(miDirectorio, "renombrado.txt");
		if (normal.renameTo(renombrado)) {
			logger.info("Fichero renombrado a renombrado.txt correctamente.");
		} else {
			logger.error("Error al renombrar el fichero normal.txt.");
		}

		if (lectura.delete()) {
			logger.info("Fichero lectura.txt borrado correctamente.");
		} else {
			logger.error("No se pudo borrar lectura.txt directamente.");
			if (lectura.setWritable(true)) {
				logger.info("Marca de solo lectura quitada de lectura.txt.");
				if (lectura.delete()) {
					logger.info("Fichero lectura.txt borrado tras modificar permisos.");
				} else {
					logger.error("Error al borrar lectura.txt tras modificar permisos.");
				}
			} else {
				logger.error("No se le pudieron conceder permisos de escritura a lectura.txt.");
			}
		}

		String[] archivos = miDirectorio.list();
		if (archivos != null) {
			for (String nombre : archivos) {
				logger.info("Fichero en directorio: " + nombre);
			}
		} else {
			logger.error("Error al listar el contenido del directorio.");
		}

	}
}