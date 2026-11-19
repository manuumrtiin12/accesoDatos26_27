package Boletin1XML.Ejercicio1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;

public class GestionaPeliculas {
	
	private static final Logger logger = LogManager.getLogger(GestionaPeliculas.class);
	
	public static void main(String[] args) {
		
		UtilesDOMPelicula util = new UtilesDOMPelicula();
		
		Document raiz = util.getDocumentFromXML("peliculas.xml");
		
		logger.info(util.leerPeliculaDesdeXML(raiz));
		
	
	}

}
