package Boletin1Ficheros_01;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;

public class Ejercicio2 {

	
	public static void main(String[] args) throws RutaNoValidaException, IOException {
		
	
	
	Scanner sc = new Scanner(System.in);
	System.out.println("Dime la ruta a la que quieres acceder: ");
	String ruta = sc.nextLine();
	
	File archivo = new File(ruta);
	
	if(!archivo.exists()) {
		
		throw new RutaNoValidaException("La ruta no existe"); 
	}
	
	else {
		
		System.out.println("Nombre: " + archivo.getName());
		System.out.println("Ruta: " + archivo.getPath());
		System.out.println("Ruta absoluta: " + archivo.getAbsolutePath());
		System.out.println("Ruta canónica: " + archivo.getCanonicalPath());
		System.out.println("Directorio padre: " + archivo.getParent());
		System.out.println("Tipo: " + (archivo.isFile() ? "fichero" : "directorio"));
		System.out.println("Lectura: " + archivo.canRead());
		System.out.println("Escritura: " + archivo.canWrite());
		System.out.println("Ejecución: " + archivo.canExecute());
		System.out.println("Oculto: " + archivo.isHidden());
		System.out.println("Tamaño en bytes: " + archivo.length());
		System.out.println("Número de elementos: " + (archivo.isDirectory() ? archivo.list().length : "no es un directorio"));
		System.out.println("Fecha de última modificación: " + new Date(archivo.lastModified()));

	}
	
	}
	
static class RutaNoValidaException extends Exception {
	public RutaNoValidaException(String mensaje) {
	        super(mensaje);
	    }
	}
}
