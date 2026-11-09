package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Simulacro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ServicioVideojuegos {

	private final IRepositorioVideojuegos repositorio;

	public ServicioVideojuegos(IRepositorioVideojuegos repositorio) {
		this.repositorio = repositorio;
	}

	// Log / reportes internos
	private void datoNoValido(String msg) {
		System.err.println("[ERROR SERVICIO - DATO NO VÁLIDO]: " + msg);
	}

	private void errorPersistencia(RepositorioException e) {
		System.err.println("[ERROR SERVICIO - PERSISTENCIA]: " + e.getMessage());
	}

	public List<Videojuego> obtenerTodos() {
		try {
			return repositorio.findAll();
		} catch (RepositorioException e) {
			errorPersistencia(e);
			return new ArrayList<>(); // Lista vacía en caso de error
		}
	}

	public boolean registrarVideojuego(String id, String titulo, String genero, double precio,
			List<String> plataformas) {
		try {
			// 1º Se crea el objeto: el constructor valida los datos
			Videojuego nuevo = new Videojuego(id, titulo, genero, precio, plataformas);

			// 2º Se comprueba que el ID no esté duplicado
			if (repositorio.existsById(nuevo.getId())) {
				datoNoValido("Ya existe un videojuego con el ID " + nuevo.getId());
				return false;
			}

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

	public boolean eliminarVideojuego(String id) {
		try {
			boolean borrado = repositorio.deleteById(id);
			if (!borrado) {
				datoNoValido("No se encontró ningún videojuego con ID " + id + " para eliminar.");
			}
			return borrado;
		} catch (RepositorioException e) {
			errorPersistencia(e);
			return false;
		}
	}

	public boolean exportarPorGeneroACSV(String generoBuscado, String rutaCsvSalida) {
		if (generoBuscado == null || generoBuscado.trim().isEmpty()) {
			datoNoValido("El género a buscar no puede estar vacío.");
			return false;
		}

		try {
			// 1. Lista completa desde el repositorio
			List<Videojuego> todos = repositorio.findAll();

			// 2. Filtrado por género sin distinguir mayúsculas/minúsculas
			List<Videojuego> filtrados = new ArrayList<>();
			for (Videojuego v : todos) {
				if (v.getGenero().equalsIgnoreCase(generoBuscado.trim())) {
					filtrados.add(v);
				}
			}

			// 3. Sin coincidencias: aviso y false
			if (filtrados.isEmpty()) {
				datoNoValido("No hay videojuegos del género " + generoBuscado + ". No se genera el CSV.");
				return false;
			}

			// 4. Escritura del CSV
			File salida = new File(rutaCsvSalida);
			File carpetaPadre = salida.getParentFile();
			if (carpetaPadre != null && !carpetaPadre.exists()) {
				carpetaPadre.mkdir();
			}

			PrintWriter out = null;
			try {
				FileWriter fw = new FileWriter(salida, StandardCharsets.UTF_8);
				out = new PrintWriter(fw);

				out.println("ID,TITULO,GENERO,PRECIO,PLATAFORMAS");
				for (Videojuego v : filtrados) {
					out.printf(Locale.ENGLISH, "%s,%s,%s,%.2f,%s%n",
							v.getId(),
							v.getTitulo(),
							v.getGenero(),
							v.getPrecio(),
							String.join(" ", v.getPlataformas()));
				}

				if (out.checkError()) {
					datoNoValido("Error al volcar los datos en el CSV.");
					return false;
				}
			} catch (IOException e) {
				datoNoValido("Fallo al escribir el CSV de exportación: " + e.getMessage());
				return false;
			} finally {
				if (out != null) {
					out.close();
				}
			}
			return true;

		} catch (RepositorioException e) {
			errorPersistencia(e);
			return false;
		}
	}
}
