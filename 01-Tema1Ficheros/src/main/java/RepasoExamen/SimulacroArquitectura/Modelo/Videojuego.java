package SimulacroArquitectura.Modelo;

import java.util.Objects;

public class Videojuego {

	private int id;
	private String titulo;
	private double precio;
	private int anioLanzamiento;
	private Plataforma plataforma;
	private Genero genero;
	private boolean multijugador;
	private boolean disponible;
	private boolean enOferta;
	
	
	public Videojuego(int id, String titulo, double precio, int anioLanzamiento, Plataforma plataforma, Genero genero,
			boolean multijugador, boolean disponible, boolean enOferta) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.precio = precio;
		this.anioLanzamiento = anioLanzamiento;
		this.plataforma = plataforma;
		this.genero = genero;
		this.multijugador = multijugador;
		this.disponible = disponible;
		this.enOferta = enOferta;
	}


	public Videojuego() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public int getAnioLanzamiento() {
		return anioLanzamiento;
	}


	public void setAnioLanzamiento(int anioLanzamiento) {
		this.anioLanzamiento = anioLanzamiento;
	}


	public Plataforma getPlataforma() {
		return plataforma;
	}


	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}


	public Genero getGenero() {
		return genero;
	}


	public void setGenero(Genero genero) {
		this.genero = genero;
	}


	public boolean isMultijugador() {
		return multijugador;
	}


	public void setMultijugador(boolean multijugador) {
		this.multijugador = multijugador;
	}


	public boolean isDisponible() {
		return disponible;
	}


	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}


	public boolean isEnOferta() {
		return enOferta;
	}


	public void setEnOferta(boolean enOferta) {
		this.enOferta = enOferta;
	}


	@Override
	public String toString() {
		return "Videojuego [id=" + id + ", titulo=" + titulo + ", precio=" + precio + ", anioLanzamiento="
				+ anioLanzamiento + ", plataforma=" + plataforma + ", genero=" + genero + ", multijugador="
				+ multijugador + ", disponible=" + disponible + ", enOferta=" + enOferta + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(id));
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Videojuego other = (Videojuego) obj;
		return id == other.id;
	}
	
	
	
	
	
	
}
