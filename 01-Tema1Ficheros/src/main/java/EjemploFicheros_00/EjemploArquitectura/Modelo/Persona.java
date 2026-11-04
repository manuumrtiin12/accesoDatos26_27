package EjemploFicheros_00.EjemploArquitectura.Modelo;

import java.util.ArrayList;
import java.util.List;


public class Persona {

	String nombre ;
	List<Double> notas;

	public Persona(String nombre, List<Double> notas2) {
	super();
	this.nombre = nombre;
	this.notas = notas2; 
	}

	public String getNombre() {
	return nombre;
	}

	public void setNombre(String nombre) {
	this.nombre = nombre;
	}

	public List<Double> getNotas() {
	return notas;
	}

	public void setNotas(List<Double> notas) {
	this.notas = notas;
	}

	@Override
	public String toString() {
	return "Persona [nombre=" + nombre + ", notas=" + notas + "]";
	}

	}