package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Simulacro;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RepositorioVideojuegosJson implements IRepositorioVideojuegos {

	private final File fichero;
	private final Gson gson;
	private List<Videojuego> cache; // Memoria interna

	public RepositorioVideojuegosJson(String rutaFichero) {
		this.fichero = new File(rutaFichero); // Ej.: "data/videojuegos.json"
		this.gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		this.cache = null; // Carga perezosa inicial
	}

	// Carga perezosa: solo lee del disco la primera vez
	private void cargarSiHaceFalta() throws RepositorioException {
		if (this.cache == null) {
			this.cache = leerFichero();
		}
	}

	private List<Videojuego> leerFichero() throws RepositorioException {
		List<Videojuego> lista = new ArrayList<>();

		if (!fichero.exists()) {
			return lista; // Si no existe todavía, empezamos con lista vacía
		}
		if (!fichero.canRead()) {
			throw new RepositorioException("Sin permisos de lectura en: " + fichero.getAbsolutePath());
		}

		FileReader fr = null;
		try {
			fr = new FileReader(fichero, StandardCharsets.UTF_8);
			Videojuego[] array = gson.fromJson(fr, Videojuego[].class);

			if (array != null) {
				for (Videojuego v : array) {
					try {
						// Pasamos por el constructor para volver a validar cada registro
						lista.add(new Videojuego(v.getId(), v.getTitulo(), v.getGenero(),
								v.getPrecio(), v.getPlataformas()));
					} catch (IllegalArgumentException e) {
						System.err.println("[AVISO REPOSITORIO] Registro inválido descartado: " + e.getMessage());
					}
				}
			}
		} catch (IOException e) {
			throw new RepositorioException("Error I/O al leer el archivo JSON", e);
		} catch (RuntimeException e) {
			// JsonSyntaxException, JsonIOException... (JSON mal formado)
			throw new RepositorioException("El archivo JSON no tiene un formato válido", e);
		} finally {
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					// ignorar
				}
			}
		}
		return lista;
	}

	private void escribirFichero(List<Videojuego> lista) throws RepositorioException {
		// Si la carpeta data/ no existe, se crea físicamente
		File carpetaPadre = fichero.getParentFile();
		if (carpetaPadre != null && !carpetaPadre.exists()) {
			if (!carpetaPadre.mkdir()) {
				throw new RepositorioException("No se pudo crear la carpeta: " + carpetaPadre.getPath());
			}
		}

		FileWriter fw = null;
		try {
			fw = new FileWriter(fichero, StandardCharsets.UTF_8); // sobrescribe
			gson.toJson(lista, fw);
		} catch (IOException e) {
			throw new RepositorioException("Error I/O al escribir el archivo JSON", e);
		} finally {
			if (fw != null) {
				try {
					fw.close(); // close() hace flush() y libera el fichero
				} catch (IOException e) {
					// ignorar
				}
			}
		}
	}

	@Override
	public List<Videojuego> findAll() throws RepositorioException {
		cargarSiHaceFalta();
		return new ArrayList<>(cache);
	}

	@Override
	public Videojuego findById(String id) throws RepositorioException {
		if (id == null) {
			return null;
		}
		cargarSiHaceFalta();
		for (Videojuego v : cache) {
			if (v.getId().equalsIgnoreCase(id.trim())) {
				return v;
			}
		}
		return null; // No hay ningún videojuego con ese id
	}

	@Override
	public boolean existsById(String id) throws RepositorioException {
		return findById(id) != null;
	}

	@Override
	public void save(Videojuego videojuego) throws RepositorioException {
		cargarSiHaceFalta();
		List<Videojuego> copia = new ArrayList<>(cache); // 1º trabajamos sobre una copia

		int pos = -1;
		for (int i = 0; i < copia.size(); i++) {
			if (copia.get(i).getId().equalsIgnoreCase(videojuego.getId())) {
				pos = i;
				break;
			}
		}

		if (pos == -1) {
			copia.add(videojuego); // Alta
		} else {
			copia.set(pos, videojuego); // Modificación
		}

		// Regla de oro: 1º fichero, 2º memoria (solo si la escritura no falló)
		escribirFichero(copia);
		this.cache = copia;
	}

	@Override
	public boolean deleteById(String id) throws RepositorioException {
		if (id == null) {
			return false;
		}
		cargarSiHaceFalta();
		List<Videojuego> copia = new ArrayList<>(cache);

		boolean eliminado = copia.removeIf(v -> v.getId().equalsIgnoreCase(id.trim()));

		if (eliminado) {
			escribirFichero(copia);
			this.cache = copia;
		}
		return eliminado;
	}
}
