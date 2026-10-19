package EjemploFicheros_00.EjemploLecturas;

import java.io.File;
import java.io.FileNotFoundException;
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

    public Persona(String nombre, List<Double> notas) {
        this.nombre = nombre;
        this.notas = notas;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Double> getNotas() {
        return notas;
    }

    public static List<Persona> leerPersonas(File ruta)
            throws FileNotFoundException {

        List<Persona> personas = new ArrayList<>();

        try (Scanner sc = new Scanner(ruta)) {
            sc.useLocale(Locale.ENGLISH);

            while (sc.hasNext()) {
                String nombre = sc.next();

                List<Double> notas = new ArrayList<>();

                while (sc.hasNextDouble()) {
                    notas.add(sc.nextDouble());
                }

                personas.add(new Persona(nombre, notas));
            }
        }

        return personas;
    }

    @Override
    public String toString() {
        return "Persona{nombre='" + nombre
                + "', notas=" + notas + "}";
    }

    public static void main(String[] args) {

        File ruta = new File(
                "C:\\Users\\mmarf\\Desktop\\DAM2\\acceso_datos\\accesoDatos26_27\\01-Tema1Ficheros\\src\\main\\java\\EjemploFicheros_00\\EjemploLecturas\\fichero1.txt");

        try {
            List<Persona> personas = leerPersonas(ruta);

            for (Persona persona : personas) {
                System.out.println(persona.toString());
            }

        } catch (FileNotFoundException e) {
        	System.out.println(e.getMessage());
        }
    }
}