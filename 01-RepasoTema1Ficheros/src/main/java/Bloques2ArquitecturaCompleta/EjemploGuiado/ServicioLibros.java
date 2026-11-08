package Bloques2ArquitecturaCompleta.EjemploGuiado;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ServicioLibros {

	private final IRepositorioLibros repositorio;

	public ServicioLibros(IRepositorioLibros repositorio) {
		this.repositorio = repositorio;
	}

	// Métodos auxiliares de log / reporte exigidos por el temario
	private void datoNoValido(String mensaje) {
		System.err.println("[ERROR SERVICIO - DATO NO VÁLIDO]: " + mensaje);
	}

	private void errorPersistencia(RepositorioException e) {
		System.err.println("[ERROR SERVICIO - PERSISTENCIA]: " + e.getMessage());
	}

	public List<Libro> listarTodos() {
		try {
			return repositorio.findAll();
		} catch (RepositorioException e) {
			errorPersistencia(e);
			return new ArrayList<>(); // Devuelve lista vacía en caso de error
		}
	}

	public boolean registrarLibro(String isbn, String titulo, String editorial, int ediciones) {
		try {
			// Comprobar si ya existe
			if (repositorio.existsById(isbn)) {
				datoNoValido("El libro con ISBN " + isbn + " ya existe.");
				return false;
			}
			// Crear objeto (lanza IllegalArgumentException si falla la validación)
			Libro nuevo = new Libro(isbn, titulo, editorial, ediciones);
			repositorio.save(nuevo);
			return true;
		} catch (IllegalArgumentException e) {
			datoNoValido(e.getMessage());
			return false;
		} catch (RepositorioException e) {
			errorPersistencia(e);
			return false;
		}
	}

	public boolean eliminarLibro(String isbn) {
		try {
			boolean borrado = repositorio.deleteById(isbn);
			if (!borrado) {
				datoNoValido("No se encontró ningún libro con ISBN " + isbn + " para borrar.");
			}
			return borrado;
		} catch (RepositorioException e) {
			errorPersistencia(e);
			return false;
		}
	}

	// Lógica de exportación combinando el repositorio JSON con salida CSV (Bloque 2)
	public boolean exportarLibrosACSV(String rutaSalidaCsv) {
		try {
			List<Libro> libros = repositorio.findAll();

			// Bloque 2: Escritura con PrintWriter en UTF-8 y formateo con Locale.ENGLISH
			try (PrintWriter out = new PrintWriter(new FileWriter(rutaSalidaCsv, StandardCharsets.UTF_8))) {
				out.println("ISBN,TITULO,EDITORIAL,EDICIONES"); // Cabecera CSV
				for (Libro l : libros) {
					out.printf(Locale.ENGLISH, "%s,%s,%s,%d%n",
							l.getIsbn(), l.getTitulo(), l.getEditorial(), l.getNumeroEdiciones());
				}
			} catch (IOException e) {
				datoNoValido("Fallo al escribir el archivo CSV de exportación: " + e.getMessage());
				return false;
			}
			return true;
		} catch (RepositorioException e) {
			errorPersistencia(e);
			return false;
		}
	}
}
