package Boletin1XML.Ejercicio1;

public class Actor {
	
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Actor [nombre=" + nombre + "]";
	}

	public Actor() {
	}

	public Actor(String nombre) {
		this.nombre = nombre;
	}
	
	

}
