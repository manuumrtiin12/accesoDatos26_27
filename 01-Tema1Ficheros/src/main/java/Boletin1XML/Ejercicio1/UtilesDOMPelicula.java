package Boletin1XML.Ejercicio1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import EjemploFicheros_00.EjemploXML.UtilesDOMxml;

public class UtilesDOMPelicula {
	
	private static final Logger logger = LogManager.getLogger(UtilesDOMPelicula.class);
    private static final String RUTA_RESOURCES = "src\\main\\resources\\";


	public Document getDocumentFromXML(String nombreFichero) {
		
		Document doc = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        
        try {
			builder = factory.newDocumentBuilder();
			doc = builder.parse(new File(RUTA_RESOURCES + nombreFichero));
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}

		return doc;
		
	}
	
	public List<Pelicula> leerPeliculaDesdeXML(Document raiz) {
		
		NodeList listaNodos = raiz.getElementsByTagName("pelicula");
	    List<Pelicula> libros = new ArrayList<Pelicula>();
	    
	    for (int i = 0; i < listaNodos.getLength(); i++) {
	    	
	    	Element elementoPelicula = (Element) listaNodos.item(i);
	    	
	    	Pelicula pel = fromElement2Pelicula(elementoPelicula);
	    	
	    	libros.add(pel);
	    }

		return libros; 
	
	}
	
	public Pelicula fromElement2Pelicula(Element elemento) {
		
		Pelicula pel = new Pelicula();
		
		String Titulo = elemento.getElementsByTagName("Titulo").item(0).getTextContent();
		int Duracion = Integer.parseInt(elemento.getElementsByTagName("Duracion").item(0).getTextContent());
		String Genero = elemento.getElementsByTagName("Genero").item(0).getTextContent();
		String sinopsis = elemento.getElementsByTagName("sinopsis").item(0).getTextContent();
		String Fecha = elemento.getElementsByTagName("Fecha").item(0).getTextContent();
		String Director = elemento.getElementsByTagName("Director").item(0).getTextContent();
		
		pel.setDirector(Director);
		pel.setDuracion(Duracion);
		pel.setGenero(Genero);
		pel.setFecha(sinopsis);
		pel.setFecha(Fecha);
		pel.setDirector(Director);
		
		return pel;
		
	}
	
	
	
}
