package EjemploFicheros_00.EjemploArquitectura.Controlador;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import EjemploFicheros_00.EjemploArquitectura.Repositorio.RepositorioException;
import EjemploFicheros_00.EjemploArquitectura.Repositorio.RepositorioPersonaTxt;
import EjemploFicheros_00.EjemploArquitectura.Repositorio.IRepositorioPersona;


public class main {
	
	private static final Logger logger = LogManager.getLogger(main.class);


	public static void main(String[] args) throws RepositorioException {
		
		File archivoPersona = new File("src/main/resources/EjemploArquitectura/persona.txt");
		
		IRepositorioPersona repo = new RepositorioPersonaTxt(archivoPersona);
		logger.info(repo.getPersonas());      
		logger.info(repo.count());            
		logger.info(repo.getPersonas());      
		logger.info(repo.existsById("Pepe")); 
		logger.info(repo.existsById("Nadie"));
	}
}
