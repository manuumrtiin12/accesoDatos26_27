package EjemploFicheros_00.EjemploXML;

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

import Boletin1Ficheros_01.Ejercicio10;

public class UtilesDOMxml {
	
    private static final Logger logger = LogManager.getLogger(UtilesDOMxml.class);

    
    private static final String RUTA_RESOURCES = "src\\main\\resources\\EjemploXMLlibro\\";


	public Document getDocumentFromXML(String nombreFichero) {
		
		Document doc = null;
		DocumentBuilderFactory factory =   DocumentBuilderFactory.newInstance();
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
	
	public Libro fromElement2Libro(Element raiz) {

		Element tituloElemento = (Element) raiz.getElementsByTagName("titulo").item(0);

		Element autorElemento = (Element) raiz.getElementsByTagName("autor").item(0);

		String id =  raiz.getAttribute("id");

		String categoria = raiz.getAttribute("categoria");

		String titulo = tituloElemento.getTextContent();

		String autor = autorElemento.getTextContent();

		Libro libro = new Libro(id, categoria, titulo, autor);

		return libro;

	}
	
	
	public List<Libro> getLibroFomXML(Element librosElement) {
		
		List<Libro> libros = new ArrayList<Libro>();
		NodeList nodosLibros = librosElement.getElementsByTagName("Libro");
		
		for (int i = 0 ; i < nodosLibros.getLength() ; i++) {
			
			
			
			Libro l = new Libro();
			
		}
		
		return null;
		
		
	}
	
}
