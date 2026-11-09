package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejemplos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Estudiante {

	private String nombre; // Identificador
	private String curso;
	private List<Double> notas;

	public Estudiante(String nombre, String curso, List<Double> notas) {

		if (nombre == null || nombre.trim().isEmpty() || curso == null || curso.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre y el curso son obligatorios.");
		}

		List<Double> notasValidas = (notas == null) ? new ArrayList<Double>() : notas;
		for (Double n : notasValidas) {
			if (n == null || n < 0 || n > 10) {
				throw new IllegalArgumentException("Las notas deben estar entre 0 y 10.");
			}
		}

		this.nombre = nombre.trim();
		this.curso = curso.trim();
		this.notas = notasValidas;
	}

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getCurso() { return curso; }
	public void setCurso(String curso) { this.curso = curso; }

	public List<Double> getNotas() { return notas; }
	public void setNotas(List<Double> notas) { this.notas = notas; }

	@Override
	public String toString() {
		return "Estudiante[nombre=" + nombre + ", curso=" + curso + ", notas=" + notas + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Estudiante other = (Estudiante) obj;
		return Objects.equals(nombre, other.nombre);
	}
}
