package Bloques1.EjerciciosPracticosBloque2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class LecturaTXT {

	File archivo = new File("src/main/java/DIA1/EjerciciosPracticosBloque2/alumnos.txt");
	File reporte = new File("src/main/java/DIA1/EjerciciosPracticosBloque2/reporte_alumnos.txt");

	public void generarReporte() {

		try (Scanner in = new Scanner(archivo);
				PrintWriter pw = new PrintWriter(reporte)) {

			in.useLocale(Locale.ENGLISH);

			while (in.hasNextLine()) {

				String linea = in.nextLine();

				if (linea.isBlank()) {
					continue;
				}

				Scanner lineaSc = new Scanner(linea);
				lineaSc.useLocale(Locale.ENGLISH); 

				String nombre = lineaSc.next();
				double suma = 0;
				int cantidad = 0;

				while (lineaSc.hasNextDouble()) {
					suma += lineaSc.nextDouble();
					cantidad++;
				}
				lineaSc.close();

				double media = (cantidad > 0) ? suma / cantidad : 0;

				pw.printf(Locale.ENGLISH, "%-15s%8.2f%n", nombre, media);
			}

		} catch (FileNotFoundException e) {
			System.out.println("Error con los ficheros: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		new LecturaTXT().generarReporte();
	}
}