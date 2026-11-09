package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Simulacro;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Videojuego {

	private String id;
	private String titulo;
	private String genero;
	private double precio;
	private List<String> plataformas;

	public Videojuego(String id, String titulo, String genero, double precio, List<String> plataformas) {

		if (id == null || id.trim().isEmpty() || titulo == null || titulo.trim().isEmpty()
				|| genero == null || genero.trim().isEmpty()) {
			throw new IllegalArgumentException("El id, el título y el género son obligatorios.");
		}
		if (precio <= 0) {
			throw new IllegalArgumentException("El precio debe ser mayor que 0.");
		}

		this.id = id.trim();
		this.titulo = titulo.trim();
		this.genero = genero.trim();
		this.precio = precio;
		this.plataformas = (plataformas == null) ? new ArrayList<String>() : plataformas;
	}

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public String getTitulo() { return titulo; }
	public void setTitulo(String titulo) { this.titulo = titulo; }

	public String getGenero() { return genero; }
	public void setGenero(String genero) { this.genero = genero; }

	public double getPrecio() { return precio; }
	public void setPrecio(double precio) { this.precio = precio; }

	public List<String> getPlataformas() { return plataformas; }
	public void setPlataformas(List<String> plataformas) { this.plataformas = plataformas; }

	@Override
	public String toString() {
		return String.format("Videojuego[id=%s, titulo=%s, genero=%s, precio=%.2f, plataformas=%s]",
				id, titulo, genero, precio, plataformas);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Videojuego other = (Videojuego) obj;
		return Objects.equals(id, other.id);
	}
}
