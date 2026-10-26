package EjemploFicheros_00.EjemploArquitectura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RepositorioPersonaTxt implements IRepositorioPersona {

	private File fichero;
	private List<Persona> personas;
	
	private static final Logger logger = LogManager.getLogger(RepositorioPersonaTxt.class);

	
	
	public RepositorioPersonaTxt(File fichero, List<Persona> personas) {
		super();
		
		this.fichero = new File("C:\\Users\\mmarf\\Desktop\\EjemplosAcceso");
		
		try {
			this.personas = findAll();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RepositorioException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	@Override
	public List<Persona> findAll() throws RepositorioException, FileNotFoundException {
		
		
		if(personas.isEmpty() || personas == null) {
			
			leerFichero("C:\\Users\\mmarf\\Desktop\\EjemplosAcceso");
			
		}
		
		else {
			
		logger.info("Ya tienes a todas las personas");
		
		}
		
		return personas;
	}
	
	List<Persona> leerFichero(String ruta) throws FileNotFoundException {
		
		FileReader reader = new FileReader(fichero);
		Scanner in = new Scanner(reader);
		in.useLocale(Locale.ENGLISH);
		
		
		while(in.hasNext()) {
			String nombrePersona = in.next();
			List<Double> notas = new ArrayList<Double>();
			
			while(in.hasNextDouble()) {
				
				notas.add(in.nextDouble());
			}
		
			Persona personaAñadir = new Persona(nombrePersona, notas);
			personas.add(personaAñadir);
		}
		
		return personas;
		
		
	}



	@Override
	public Persona findById(String nombre) throws RepositorioException, FileNotFoundException {
		
		List<Persona> personas = this.findAll();
		
		for(Persona persona : personas) {
			
			if(persona.nombre.equals(nombre)) {
				
				return persona;
			}
		}
		
		return null;

	}

}
