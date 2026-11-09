package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejemplos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ServicioEstudiantes_CSVTOJSON {

	private final IRepositorioEstudiantes repoCsv; // Su repositorio lee CSV

	public ServicioEstudiantes_CSVTOJSON(IRepositorioEstudiantes repoCsv) {
		this.repoCsv = repoCsv;
	}

	// Log / reportes internos
	private void datoNoValido(String msg) {
		System.err.println("[ERROR SERVICIO - DATO NO VÁLIDO]: " + msg);
	}

	private void errorPersistencia(RepositorioException e) {
		System.err.println("[ERROR SERVICIO - PERSISTENCIA]: " + e.getMessage());
	}

	// MÉTODO DE CONVERSIÓN: CSV -> JSON
	// El servicio no propaga excepciones: informa y devuelve false
	public boolean exportarAJson(String rutaJsonSalida) {

		if (rutaJsonSalida == null || rutaJsonSalida.trim().isEmpty()) {
			datoNoValido("La ruta del JSON de salida no puede estar vacía.");
			return false;
		}

		try {
			// PASO 1: Obtener la lista de objetos Java desde el Repositorio CSV
			List<Estudiante> lista = repoCsv.findAll();

			// PASO 2: Configurar Gson con sangría para un JSON bien formateado
			Gson gson = new GsonBuilder().setPrettyPrinting().create();

			// PASO 3: Crear la carpeta destino si no existe
			File salida = new File(rutaJsonSalida);
			File carpetaPadre = salida.getParentFile();
			if (carpetaPadre != null && !carpetaPadre.exists()) {
				carpetaPadre.mkdir();
			}

			// PASO 4: Escribir la lista completa en el fichero JSON destino
			try (FileWriter fw = new FileWriter(salida, StandardCharsets.UTF_8)) {
				gson.toJson(lista, fw); // Gson convierte la List<Estudiante> a JSON automáticamente
			} catch (IOException e) {
				datoNoValido("Error I/O al escribir el JSON: " + e.getMessage());
				return false;
			}

			return true; // Exportación correcta

		} catch (RepositorioException e) {
			errorPersistencia(e);
			return false;
		}
	}
}