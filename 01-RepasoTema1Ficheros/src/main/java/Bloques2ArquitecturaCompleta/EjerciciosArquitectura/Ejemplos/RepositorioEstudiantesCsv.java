package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejemplos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


public class RepositorioEstudiantesCsv implements IRepositorioEstudiantes {

	private String SEPARADOR_CSV = ",";
	private File fichero;
	private List<Estudiante> estudiantesCache; 

	public RepositorioEstudiantesCsv(String rutaFichero) {
		this.fichero = new File(rutaFichero);
		this.estudiantesCache = null; 
	}

	private void cargarSiHaceFalta() throws RepositorioException {
		if (this.estudiantesCache == null) {
			this.estudiantesCache = leerFichero();
		}
	}

	private List<Estudiante> leerFichero() throws RepositorioException {
		List<Estudiante> lista = new ArrayList<>();

		if (!fichero.exists()) {
			return lista;
		}
		if (!fichero.canRead()) {
			throw new RepositorioException("Sin permisos de lectura en: " + fichero.getAbsolutePath());
		}

		Scanner sc = null;
		try {
			FileReader fr = new FileReader(fichero, StandardCharsets.UTF_8);
			sc = new Scanner(fr);
			sc.useLocale(Locale.ENGLISH);

			if (sc.hasNextLine()) {
				sc.nextLine();
			}

			int numLinea = 1;
			while (sc.hasNextLine()) {
				numLinea++;
				String linea = sc.nextLine();
				if (linea.trim().isEmpty()) continue;

				try {
					lista.add(parsearLineaCSV(linea));
				} catch (IllegalArgumentException e) {
					System.err.println("[AVISO REPOSITORIO] Línea " + numLinea + " descartada: " + e.getMessage());
				}
			}
		} catch (IOException e) {
			throw new RepositorioException("Error al leer el archivo CSV de estudiantes", e);
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
		return lista;
	}

	private Estudiante parsearLineaCSV(String linea) {
		String[] campos = linea.split(SEPARADOR_CSV, -1); 
		if (campos.length < 3) {
			throw new IllegalArgumentException("La línea no contiene las 3 columnas requeridas.");
		}

		String nombre = campos[0].trim();
		String curso = campos[1].trim();

		List<Double> notas = new ArrayList<>();
		String notasTexto = campos[2].trim();
		if (!notasTexto.isEmpty()) {
			for (String n : notasTexto.split(" ")) {
				if (!n.trim().isEmpty()) {
					notas.add(Double.parseDouble(n.trim()));
				}
			}
		}

		return new Estudiante(nombre, curso, notas);
	}

	private void escribirFichero(List<Estudiante> lista) throws RepositorioException {
		File carpetaPadre = fichero.getParentFile();
		if (carpetaPadre != null && !carpetaPadre.exists()) {
			carpetaPadre.mkdir();
		}

		PrintWriter out = null;
		try {
			FileWriter fw = new FileWriter(fichero, StandardCharsets.UTF_8, false);
			out = new PrintWriter(fw);

			out.println("Nombre,Curso,Notas");

			for (Estudiante e : lista) {
				StringBuilder notas = new StringBuilder();
				for (Double n : e.getNotas()) {
					if (notas.length() > 0) {
						notas.append(" ");
					}
					notas.append(n); 
				}
				out.printf(Locale.ENGLISH, "%s,%s,%s%n", e.getNombre(), e.getCurso(), notas);
			}

			if (out.checkError()) {
				throw new RepositorioException("Error al volcar datos en el archivo CSV.");
			}
		} catch (IOException e) {
			throw new RepositorioException("Error I/O al escribir el archivo de estudiantes", e);
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	@Override
	public List<Estudiante> findAll() throws RepositorioException {
		cargarSiHaceFalta();
		return new ArrayList<>(estudiantesCache);
	}

	@Override
	public Estudiante findById(String nombre) throws RepositorioException {
		if (nombre == null) {
			return null;
		}
		cargarSiHaceFalta();
		for (Estudiante e : estudiantesCache) {
			if (e.getNombre().equalsIgnoreCase(nombre.trim())) {
				return e;
			}
		}
		return null;
	}

	@Override
	public boolean existsById(String nombre) throws RepositorioException {
		return findById(nombre) != null;
	}

	@Override
	public void save(Estudiante estudiante) throws RepositorioException {
		cargarSiHaceFalta();
		List<Estudiante> copia = new ArrayList<>(estudiantesCache);

		int pos = -1;
		for (int i = 0; i < copia.size(); i++) {
			if (copia.get(i).getNombre().equalsIgnoreCase(estudiante.getNombre())) {
				pos = i;
				
			}
		}

		if (pos == -1) {
			copia.add(estudiante); 
		} else {
			copia.set(pos, estudiante); 
		}

		escribirFichero(copia);
		this.estudiantesCache = copia;
	}

	@Override
	public boolean deleteById(String nombre) throws RepositorioException {
		if (nombre == null) {
			return false;
		}
		cargarSiHaceFalta();
		List<Estudiante> copia = new ArrayList<>(estudiantesCache);

		boolean eliminado = copia.removeIf(e -> e.getNombre().equalsIgnoreCase(nombre.trim()));

		if (eliminado) {
			escribirFichero(copia);
			this.estudiantesCache = copia;
		}
		return eliminado;
	}
	
	
}
