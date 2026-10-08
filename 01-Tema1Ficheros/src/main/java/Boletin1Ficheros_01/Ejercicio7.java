package Boletin1Ficheros_01;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Ejercicio7 {
	
	private static final Logger logger = LogManager.getLogger(Ejercicio7.class);
	
	public List<File> buscar(File dir, String nombre) {

	    List<File> resultado = new ArrayList<>();

	    if (dir.isFile()) {
	        if (dir.getName().equals(nombre)) {
	            resultado.add(dir);
	        }
	    }
	    
	    else {

	    File[] archivos = dir.listFiles();

	    if (archivos != null) {
	        for (File archivo : archivos) {

	            if (archivo.isFile() && archivo.getName().equals(nombre)) {
	                resultado.add(archivo);

	            } else if (archivo.isDirectory()) {
	                resultado.addAll(buscar(archivo, nombre));
	            }
	        }
	    }
	    }

	    return resultado;
	}
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Dame un nombre de Archivo: ");
		String nombreFichero = sc.next();
		
		File directorioBuscar = new File("C:\\Users\\mmarf\\Desktop\\DAM2\\acceso_datos\\accesoDatos26_27\\01-Tema1Ficheros\\src\\main\\java");
		
		Ejercicio7 llamaBuscar = new Ejercicio7();
		
		logger.info(llamaBuscar.buscar(directorioBuscar, nombreFichero));
		
		
	}

}
