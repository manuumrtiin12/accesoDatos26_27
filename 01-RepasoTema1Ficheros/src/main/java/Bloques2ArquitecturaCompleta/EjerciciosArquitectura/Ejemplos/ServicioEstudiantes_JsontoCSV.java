package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejemplos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;

import Bloques2ArquitecturaCompleta.EjemploGuiado.RepositorioException;
import Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejercicio1.IRepositorioPaises;
import Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejercicio1.Pais;

public class ServicioEstudiantes_JsontoCSV {

    private final IRepositorioPaises repoJson; // Su repositorio lee JSON

    public ServicioEstudiantes_JsontoCSV(IRepositorioPaises repoJson) {
        this.repoJson = repoJson;
    }

    // MÉTODO DE CONVERSIÓN: JSON -> CSV
    public boolean exportarACSV(String rutaCsvSalida) throws Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejercicio1.RepositorioException {
        // PASO 1: Obtener la lista de objetos Java desde el Repositorio JSON
		List<Pais> lista = repoJson.findAll();

		// PASO 2: Escribir la lista en un fichero CSV con PrintWriter
		try (PrintWriter out = new PrintWriter(new FileWriter(rutaCsvSalida, StandardCharsets.UTF_8))) {
		    
		    // A. Escribir la cabecera CSV
		    out.println("ID,NOMBRE,NOTA");

		    // B. Recorrer los objetos e imprimir cada fila separada por comas
		    for (Pais e : lista) {
		        out.printf(Locale.ENGLISH, "%d,%s,%.2f%n", 
		                e.getIdiomas(), 
		                e.getNombre(), 
		                e.getNombre());
		    }
		} catch (IOException e) {
		    System.err.println("Error I/O al escribir el CSV: " + e.getMessage());
		    return false;
		}

		return true; // Exportación correcta
    }
}
