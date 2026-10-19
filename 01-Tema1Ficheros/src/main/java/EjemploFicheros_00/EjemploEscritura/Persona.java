package EjemploFicheros_00.EjemploEscritura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;

public class Persona {

    private String nombre;
    private List<Double> notas;

    private static final Logger logger =
            Logger.getLogger(Persona.class.getName());

    
    public void escribePersonas(List<Persona> personas, String rutaFichero) {
        PrintWriter out = null;

        try {
            FileWriter ficheroSalida =
                    new FileWriter(rutaFichero, StandardCharsets.UTF_8, true);

            // Abre el fichero de texto
            out = new PrintWriter(ficheroSalida);

            // Escribe la cabecera
            out.printf("%-20s", "Nombre");
            out.printf("%-10s%n", "Notas");

            // Escribe el listado persona a persona
            for (Persona p : personas) {
                out.printf(Locale.ENGLISH, "%-20s", p.getNombre());

                for (double nota : p.getNotas()) {
                    out.printf(Locale.ENGLISH, "%6.2f", nota);
                }

                out.println();
            }

        } catch (IOException e) {
            logger.info("IOException");
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
    
    
	public Persona(String nombre, List<Double> notas) {
		super();
		this.nombre = nombre;
		this.notas = notas;
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
	
	public static void main(String[] args) {

		    String ruta = "C:\\Users\\mmarf\\Desktop\\DAM2\\acceso_datos\\accesoDatos26_27\\01-Tema1Ficheros\\src\\main\\java\\EjemploFicheros_00\\EjemploEscritura\\fichero1.txt";

		    ArrayList<Double> notas1 = new ArrayList<>();
		    notas1.add(10.0);
		    notas1.add(5.0);
		    notas1.add(7.0);

		    Persona p1 = new Persona("Pepe Marronero", notas1);

		    ArrayList<Double> notas2 = new ArrayList<>();
		    notas2.add(0.0);
		    notas2.add(10.0);
		    notas2.add(5.0);

		    Persona p2 = new Persona("Patri Mayonesa", notas2);

		    List<Persona> personas = new ArrayList<>();
		    personas.add(p1);
		    personas.add(p2);

		    p1.escribePersonas(personas, ruta);

		    System.out.println("Personas escritas correctamente.");
		}
}
