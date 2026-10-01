package Boletin1Ficheros_01;

import java.io.File;
import java.util.Scanner;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;
import EjemploFicheros_00.EjemploFicheros;

public class Ejercicio1 {
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Ejercicio1.class);


	
	public static void main(String[] args) {
		
		
	Scanner sc = new Scanner(System.in);
	System.out.println("Dame la ruta: ");
	String ruta = sc.nextLine();
	
	try {
		
		File directorio = new File(ruta);
		
		if (!directorio.exists()) {
			
			throw new RutaNoValidaException("La ruta no existe");
		}
		
		if (!directorio.isDirectory()) {
			
			throw new RutaNoValidaException("La ruta existe pero no es un directorio");

		}
		
		 File[] elementos = directorio.listFiles();

		 int totalFicheros = 0;
		 int totalDirectorios = 0;

		 for (File elemento : elementos) {

		     if (elemento.isFile()) {
		         logger.debug("[F] " + elemento.getName());
		         totalFicheros++;

		     } else if (elemento.isDirectory()) {
		         logger.debug("[D] " + elemento.getName());
		         totalDirectorios++;
		        }
		    }

		    logger.debug("Total de ficheros: " + totalFicheros);
		    logger.debug("Total de directorios: " + totalDirectorios);

		
		
	} catch (RutaNoValidaException  e) {
		// TODO: handle exception
		logger.debug("Error: " + e.getMessage());
	}
	
	

	}
}


class RutaNoValidaException extends Exception {

    public RutaNoValidaException(String mensaje) {
        super(mensaje);
    }
}
