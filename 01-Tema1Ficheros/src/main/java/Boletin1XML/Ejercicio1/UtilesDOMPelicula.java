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
	
	private static final String RUTA_RESOURCES = "src/main/resources/";

	public Document getDocumentFromXML(String nombreFichero) {

		Document doc = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder;

        try {

			builder = factory.newDocumentBuilder();

			try {

				doc = builder.parse(new File(RUTA_RESOURCES + nombreFichero));

			} catch (SAXException e) {

				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();

			}

		} catch (ParserConfigurationException e) {

			e.printStackTrace();

		}

        return doc;

	}

	

	public Pelicula fromElement2Pelicula(Element raiz) {

		Element tituloElemento = (Element) raiz.getElementsByTagName("Titulo").item(0);

		Element duracionElemento = (Element) raiz.getElementsByTagName("Duracion").item(0);

		Element generoElemento = (Element) raiz.getElementsByTagName("Genero").item(0);

		Element sinopsisElemento = (Element) raiz.getElementsByTagName("Sinopsis").item(0);

		String sinopsis = "";

		Element actoresElemento = (Element) raiz.getElementsByTagName("Actores").item(0);

		Element añoElemento = (Element) raiz.getElementsByTagName("Fecha").item(0);

		Element directorElemento = (Element) raiz.getElementsByTagName("Director").item(0);

		String titulo = tituloElemento.getTextContent();

		String duracion = duracionElemento.getTextContent();

		String genero = generoElemento.getTextContent();

		if (sinopsisElemento != null) {

			sinopsis = sinopsisElemento.getTextContent();

		} 

		List<Actor> actores = fromElement2Actor(actoresElemento);

		String fecha = añoElemento.getTextContent();

		String director = directorElemento.getTextContent();

		Pelicula pelicula = new Pelicula(titulo, duracion, genero, sinopsis, actores, fecha, director);

		return pelicula;

	}

	

	public List<Pelicula> leerPeliculasDesdeXML(Document raiz) {

		// 1. Obtener todos los elementos <libro>

	    NodeList listaNodos = raiz.getElementsByTagName("Pelicula");

	    // 2. Crear la colección de libros

	    List<Pelicula> peliculas = new ArrayList<>();

	    // 3. Recorrer los elementos <libro>

	    for (int i = 0; i < listaNodos.getLength(); i++) {

	        Element elementoLibro = (Element) listaNodos.item(i);

	        // 4. Convertir el elemento en un objeto Libro

	        Pelicula pelicula = fromElement2Pelicula(elementoLibro);

	        // 5. Añadir el libro a la colección

	        peliculas.add(pelicula);

	    }

	    return peliculas;

	}

	

	public List<Actor> fromElement2Actor(Element raiz) {

		NodeList listaNodos = raiz.getElementsByTagName("Actor");

		List<Actor> actores = new ArrayList<Actor>();

		for (int i = 0; i < listaNodos.getLength(); i++) {

			Element actorElemento = (Element) listaNodos.item(i);

			String nombre = actorElemento.getTextContent();

			Actor actor = new Actor(nombre);

			actores.add(actor);

		}

		return actores;

	}
}
