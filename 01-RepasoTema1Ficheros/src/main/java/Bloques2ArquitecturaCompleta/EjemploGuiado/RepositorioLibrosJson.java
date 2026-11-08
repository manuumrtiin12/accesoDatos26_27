package Bloques2ArquitecturaCompleta.EjemploGuiado;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RepositorioLibrosJson implements IRepositorioLibros {

	private final File fichero;
	private List<Libro> librosCache; // Memoria interna
	private final Gson gson;

	public RepositorioLibrosJson(String rutaFichero) {
		// Bloque 1: Representación de la ruta física con File
		this.fichero = new File(rutaFichero);
		// Bloque 3: Configuración avanzada de Gson con GsonBuilder
		this.gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		this.librosCache = null; // Carga perezosa inicial
	}

	// Carga perezosa: solo lee del disco la primera vez
	private void cargarSiHaceFalta() throws RepositorioException {
		if (this.librosCache == null) {
			this.librosCache = leerFichero();
		}
	}

	private List<Libro> leerFichero() throws RepositorioException {
		List<Libro> lista = new ArrayList<>();

		// Bloque 1: Comprobar existencia física antes de abrir flujos
		if (!fichero.exists()) {
			return lista; // Si el fichero no existe, devolvemos lista vacía
		}
		if (!fichero.canRead()) {
			throw new RepositorioException("Sin permisos de lectura en la ruta: " + fichero.getPath());
		}

		FileReader fr = null;
		try {
			fr = new FileReader(fichero, StandardCharsets.UTF_8);
			Libro[] array = gson.fromJson(fr, Libro[].class);
			if (array != null) {
				// Validar cada libro leído
				for (Libro l : array) {
					try {
						// El constructor valida atributos no nulos
						Libro validado = new Libro(l.getIsbn(), l.getTitulo(), l.getEditorial(), l.getNumeroEdiciones());
						lista.add(validado);
					} catch (IllegalArgumentException e) {
						// Ignorar libro inválido o registrar aviso
					}
				}
			}
		} catch (IOException e) {
			// Conversión de excepción física a RepositorioException
			throw new RepositorioException("Error I/O al leer el archivo JSON", e);
		} finally {
			if (fr != null) {
				try { fr.close(); } catch (IOException e) { /* ignorar */ }
			}
		}
		return lista;
	}

	private void escribirFichero(List<Libro> lista) throws RepositorioException {
		// Bloque 1: Comprobar o crear directorio padre si no existe
		File carpetaPadre = fichero.getParentFile();
		if (carpetaPadre != null && !carpetaPadre.exists()) {
			boolean creada = carpetaPadre.mkdir();
			if (!creada) {
				throw new RepositorioException("No se pudo crear la carpeta contenedora.");
			}
		}

		FileWriter fw = null;
		try {
			fw = new FileWriter(fichero, StandardCharsets.UTF_8);
			gson.toJson(lista, fw);
		} catch (IOException e) {
			throw new RepositorioException("Error I/O al escribir en el archivo JSON", e);
		} finally {
			if (fw != null) {
				try { fw.close(); } catch (IOException e) { /* ignorar */ }
			}
		}
	}

	@Override
	public List<Libro> findAll() throws RepositorioException {
		cargarSiHaceFalta();
		return new ArrayList<>(librosCache);
	}

	@Override
	public Libro findById(String isbn) throws RepositorioException {
		cargarSiHaceFalta();
		for (Libro l : librosCache) {
			if (l.getIsbn().equalsIgnoreCase(isbn)) {
				return l;
			}
		}
		return null;
	}

	@Override
	public boolean existsById(String isbn) throws RepositorioException {
		return findById(isbn) != null;
	}

	@Override
	public void save(Libro libro) throws RepositorioException {
		cargarSiHaceFalta();
		// 1º Copia de seguridad en memoria
		List<Libro> copia = new ArrayList<>(librosCache);

		int pos = -1;
		for (int i = 0; i < copia.size(); i++) {
			if (copia.get(i).getIsbn().equalsIgnoreCase(libro.getIsbn())) {
				pos = i;
				break;
			}
		}

		if (pos == -1) {
			copia.add(libro); // Alta
		} else {
			copia.set(pos, libro); // Modificación
		}

		// Regla: 1º Escribir en fichero, 2º Actualizar memoria
		escribirFichero(copia);
		this.librosCache = copia;
	}

	@Override
	public boolean deleteById(String isbn) throws RepositorioException {
		cargarSiHaceFalta();
		List<Libro> copia = new ArrayList<>(librosCache);

		boolean eliminado = copia.removeIf(l -> l.getIsbn().equalsIgnoreCase(isbn));

		if (eliminado) {
			// Regla: 1º Escribir en fichero, 2º Actualizar memoria
			escribirFichero(copia);
			this.librosCache = copia;
		}
		return eliminado;
	}
}
