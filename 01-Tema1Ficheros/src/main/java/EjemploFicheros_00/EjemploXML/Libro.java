package EjemploFicheros_00.EjemploXML;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Libro {

	
	private String id;
	private String categoria;
	private String titulo;
	private String autor;
	

	
	
	public Libro() {
		
	}


	


	public Libro(String id, String categoria, String titulo, String autor) {
		this.id = id;
		this.categoria = categoria;
		this.titulo = titulo;
		this.autor = autor;
	}





	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	@Override
	public String toString() {
		return "Libro [id=" + id + ", categoria=" + categoria + ", titulo=" + titulo + ", autor=" + autor + "]";
	}
	
	
	
}
	
	
