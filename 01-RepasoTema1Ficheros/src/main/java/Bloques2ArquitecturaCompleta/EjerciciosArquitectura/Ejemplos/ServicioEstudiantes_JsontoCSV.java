package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejemplos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

public class ServicioEstudiantes_JsontoCSV {

	private final IRepositorioEstudiantes repoJson; // Su repositorio lee JSON

	public ServicioEstudiantes_JsontoCSV(IRepositorioEstudiantes repoJson) {
		this.repoJson = repoJson;
	}

	// Log / reportes internos
	private void datoNoValido(String msg) {
		System.err.println("[ERROR SERVICIO - DATO NO VÁLIDO]: " + msg);
	}

	private void errorPersistencia(RepositorioException e) {
		System.err.println("[ERROR SERVICIO - PERSISTENCIA]: " + e.getMessage());
	}

	// MÉTODO DE CONVERSIÓN: JSON -> CSV
	// El servicio no propaga excepciones: informa y devuelve false
	public boolean exportarACSV(String rutaCsvSalida) {

		if (rutaCsvSalida == null || rutaCsvSalida.trim().isEmpty()) {
			datoNoValido("La ruta del CSV de salida no puede estar vacía.");
			return false;
		}

		try {
			// PASO 1: Obtener la lista de objetos Java desde el Repositorio JSON
			List<Estudiante> lista = repoJson.findAll();

			// PASO 2: Crear la carpeta destino si no existe
			File salida = new File(rutaCsvSalida);
			File carpetaPadre = salida.getParentFile();
			if (carpetaPadre != null && !carpetaPadre.exists()) {
				carpetaPadre.mkdir();
			}

			// PASO 3: Escribir la lista en un fichero CSV con PrintWriter
			try (PrintWriter out = new PrintWriter(new FileWriter(salida, StandardCharsets.UTF_8))) {

				// A. Cabecera CSV (la misma que lee RepositorioEstudiantesCsv)
				out.println("Nombre,Curso,Notas");

				// B. Una fila por estudiante; las notas se unen separadas por espacios
				for (Estudiante e : lista) {
					StringBuilder notas = new StringBuilder();
					for (Double n : e.getNotas()) {
						if (notas.length() > 0) {
							notas.append(" ");
						}
						notas.append(String.format(Locale.ENGLISH, "%.2f", n));
					}

					out.printf(Locale.ENGLISH, "%s,%s,%s%n", e.getNombre(), e.getCurso(), notas);
				}

				if (out.checkError()) {
					datoNoValido("Error al volcar los datos en el CSV.");
					return false;
				}
			} catch (IOException e) {
				datoNoValido("Error I/O al escribir el CSV: " + e.getMessage());
				return false;
			}

			return true; // Exportación correcta

		} catch (RepositorioException e) {
			errorPersistencia(e);
			return false;
		}
	}
}