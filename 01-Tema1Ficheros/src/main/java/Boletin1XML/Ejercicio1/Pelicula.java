package Boletin1XML.Ejercicio1;

import java.util.List;

public class Pelicula {
	
	private String Titulo;
	private int Duracion;
	private String Genero;
	private String sinopsis;
	private String Fecha;
	private String Director;
	
	
	public Pelicula(String titulo2, String duracion2, String genero2, String sinopsis2, List<Actor> actores, String fecha2, String director2) {
	}

	
	public Pelicula(String titulo, int duracion, String genero, String sinopsis, String fecha, String director) {
		this.Titulo = titulo;
		this.Duracion = duracion;
		this.Genero = genero;
		this.sinopsis = sinopsis;
		this.Fecha = fecha;
		this.Director = director;
	}
	

	public String getTitulo() {
		return Titulo;
	}


	public void setTitulo(String titulo) {
		this.Titulo = titulo;
	}


	public int getDuracion() {
		return Duracion;
	}


	public void setDuracion(int duracion) {
		this.Duracion = duracion;
	}


	public String getGenero() {
		return Genero;
	}


	public void setGenero(String genero) {
		this.Genero = genero;
	}


	public String getSinopsis() {
		return sinopsis;
	}


	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}


	public String getFecha() {
		return Fecha;
	}


	public void setFecha(String fecha) {
		this.Fecha = fecha;
	}


	public String getDirector() {
		return Director;
	}


	public void setDirector(String director) {
		this.Director = director;
	}


	@Override
	public String toString() {
		return "Pelicula [titulo=" + Titulo + ", duracion=" + Duracion + ", genero=" + Genero + ", sinopsis=" + sinopsis
				+ ", fecha=" + Fecha + ", director=" + Director + "]";
	}
}