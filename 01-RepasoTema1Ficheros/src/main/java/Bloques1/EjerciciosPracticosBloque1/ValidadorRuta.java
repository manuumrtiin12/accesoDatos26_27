package Bloques1.EjerciciosPracticosBloque1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidadorRuta {

	private static final Logger logger = LogManager.getLogger(ValidadorRuta.class);
	
	public static void main(String[] args) {
		
		
		
	}

	public void mostrarInfo(String ruta) throws RutaNoValidaException {

		File fileRuta = new File(ruta);

		if (!fileRuta.exists()) {
			throw new RutaNoValidaException("La ruta no existe: " + ruta);
		}

		logger.info("Nombre: " + fileRuta.getName());
		logger.info("Ruta absoluta: " + fileRuta.getAbsolutePath());

		long tamaño;

		if (fileRuta.isDirectory()) {
			logger.info("Tipo: directorio");
			tamaño = calcularTamañoDirectorio(fileRuta);
		} else if (fileRuta.isFile()) {
			logger.info("Tipo: fichero");
			tamaño = fileRuta.length();
		} else {
			logger.info("Tipo: desconocido");
			tamaño = fileRuta.length();
		}

		logger.info("Tamaño: " + tamaño + " bytes");
		logger.info("Permiso de lectura: " + fileRuta.canRead());
		logger.info("Permiso de escritura: " + fileRuta.canWrite());

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		logger.info("Última modificación: " + formato.format(new Date(fileRuta.lastModified())));
	}

	private long calcularTamañoDirectorio(File directorio) {

		long total = 0;
		File[] listado = directorio.listFiles();

		if (listado == null) {
			return 0; // sin permisos o error de E/S
		}

		for (File archivo : listado) {
			if (archivo.isDirectory()) {
				total += calcularTamañoDirectorio(archivo);
			} else {
				total += archivo.length();
			}
		}

		return total;
	}
}