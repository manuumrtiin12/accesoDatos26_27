package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejemplos;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Bloques2ArquitecturaCompleta.EjemploGuiado.RepositorioException;
import Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejercicio1.IRepositorioPaises;
import Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejercicio1.Pais;

public class ServicioEstudiantes_CSVTOJSON {

    private final IRepositorioPaises repoCsv; // Su repositorio lee CSV

    public ServicioEstudiantes_CSVTOJSON(IRepositorioPaises repoCsv) {
        this.repoCsv = repoCsv;
    }

    // MÉTODO DE CONVERSIÓN: CSV -> JSON
    public boolean exportarAJson(String rutaJsonSalida) throws Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejercicio1.RepositorioException {
        // PASO 1: Obtener la lista de objetos Java desde el Repositorio CSV
		List<Pais> lista = repoCsv.findAll();

		// PASO 2: Configurar Gson con sangría para un JSON bien formateado
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		// PASO 3: Escribir la lista completa en el fichero JSON destino
		try (FileWriter fw = new FileWriter(rutaJsonSalida, StandardCharsets.UTF_8)) {
		    gson.toJson(lista, fw); // Gson convierte la List<Estudiante> a JSON automáticamente
		} catch (IOException e) {
		    System.err.println("Error I/O al escribir el JSON: " + e.getMessage());
		    return false;
		}

		return true; // Exportación correcta
    }
}
