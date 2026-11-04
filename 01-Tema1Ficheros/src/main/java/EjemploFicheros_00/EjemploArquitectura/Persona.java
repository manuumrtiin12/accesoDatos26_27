package EjemploFicheros_00.EjemploArquitectura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Persona {
	private static final Logger logger = LogManager.getLogger(Persona.class);
	
	static  String rutaDirectorio = "src\\main\\resources";
	
	String nombre ;
	List<Double> notas;
	
	public Persona(String nombre, List<Double> notas2) {
	super();
	this.nombre = nombre;
	this.notas = new ArrayList<Double>();
	}
	
	public String getNombre() {
	return nombre;
	}
	
	public void setNombre(String nombre) {
	this.nombre = nombre;
	}
	
	public List<Double> getNotas() {
	return notas;
	}
	
	public void setNotas(List<Double> notas) {
	this.notas = notas;
	}
	
	void muestraContenidoFich(String nombre , List<Double> notas) throws FileNotFoundException {
	Scanner in = null;
	File  directorio = new File(rutaDirectorio);
	// Referencio a un fichero dentro del directorio
	File fichero = new File(directorio, "fichero.txt");
	try {
	boolean creado = fichero.createNewFile(); // Aquí Sí creo fichero
	} catch (IOException e) {
	// TODO Auto-generated catch block
	logger.error("Error al crear fichero:" + e.getMessage());
	}
	
	try {
	// abre el fichero
	FileReader fichero1 = new FileReader(nombre);
	
	//Se crea el flujo
	in = new Scanner(fichero1);
	// lee el fichero
	while (in.hasNext()) { //Lectura palabra a palabra
	// Aquí se hará la lectura in.next()
	}
	} finally {
	if (in != null) {
	in.close();
	}
	}
	}
	
	public void escribePersonas(List<Persona> personas, String rutaFichero) {
	   PrintWriter out = null;
	
	   try {
	       FileWriter ficheroSalida =
	           new FileWriter(rutaFichero, StandardCharsets.UTF_8);
	
	       out = new PrintWriter(ficheroSalida);
	
	       // Escribe el listado persona a persona
	       for (Persona p : personas) {
	           out.printf(Locale.ENGLISH, "%-10s", p.getNombre());
	
	           for (double nota : p.getNotas()) {
	               out.printf(Locale.ENGLISH, "%6.2f", nota);
	           }
	
	           out.println();
	       }
	
	   } catch (IOException e) {
	       logger.debug("IOException", e);
	
	   } finally {
	       if (out != null) {
	           out.close();
	       }
	   }
	}
	
	public static void main(String[] args) {
	   List<Double> notas = new ArrayList<>();
	   notas.add(7.5);
	   notas.add(8.25);
	
	   List<Persona> personas = new ArrayList<>();
	
	   Persona p = new Persona("Andra", notas);
	   personas.add(p);
	
	   p.escribePersonas(personas, "personas.txt");
	}
	
	}
