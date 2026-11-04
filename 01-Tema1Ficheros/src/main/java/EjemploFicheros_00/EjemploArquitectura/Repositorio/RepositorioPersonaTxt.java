package EjemploFicheros_00.EjemploArquitectura.Repositorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import EjemploFicheros_00.EjemploArquitectura.Modelo.Persona;

public class RepositorioPersonaTxt implements IRepositorioPersona {

	private File fichero;
	private List<Persona> personas;

	private static final Logger logger = LogManager.getLogger(RepositorioPersonaTxt.class);

	public RepositorioPersonaTxt(File fichero) {
		super();

		this.fichero = fichero;
		this.personas = null;

	}


	@Override
	public List<Persona> findAll() throws RepositorioException {

		if(personas == null) {

			personas = leerFichero();

		}

		else {

		logger.info("Ya tienes a todas las personas");

		}

		return personas;
	}

	
	List<Persona> leerFichero() throws RepositorioException {

		List<Persona> resultado = new ArrayList<Persona>();
		Scanner in = null;

		try {
			FileReader reader = new FileReader(fichero);
			in = new Scanner(reader);
			in.useLocale(Locale.ENGLISH);


			while(in.hasNext()) {
				String nombrePersona = in.next();
				List<Double> notas = new ArrayList<Double>();

				while(in.hasNextDouble()) {

					notas.add(in.nextDouble());
				}

				Persona personaAñadir = new Persona(nombrePersona, notas);
				resultado.add(personaAñadir);
			}

		} catch (FileNotFoundException e) {
			throw new RepositorioException("No se encuentra el fichero: " + fichero.getPath(), e);
		} finally {
			if (in != null) {
				in.close();
			}
		}

		return resultado;


	}



	@Override
	public Persona findById(String nombre) throws RepositorioException {

		List<Persona> personas = this.findAll();

		for(Persona persona : personas) {

			if(persona.getNombre().equals(nombre)) {

				return persona;
			}
		}

		return null;

	}


	@Override
	public boolean existsById(String nombre) throws RepositorioException {
		return findById(nombre) != null;
	}



	@Override
	public int count() throws RepositorioException {
		return findAll().size();
	}



	@Override
	public List<Persona> getPersonas() {
	    return personas;
	}

}