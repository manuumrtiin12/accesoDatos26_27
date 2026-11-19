package RepasoExamen.prepexamConjunta.prepexam.Modelo;

public class Empleado {
	private int edad;
	private String nombre;
	private boolean activo;
	private Puesto puesto;
	private Direccion direccion;
	
	public int getEdad() { 
		return edad;
	}
	public void setEdad(int edad) { // se podria meter una validacion basica para hacer que la edad tenga que ser mayor a 18 
		this.edad = edad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public Puesto getPuesto() {
		return puesto;
	}
	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	@Override
	public String toString() {
		return "Empleado [edad=" + edad + ", nombre=" + nombre + ", activo=" + activo + ", puesto=" + puesto
				+ ", direccion=" + direccion + "]";
	}
	public Empleado() {
		super();
	}

	
	
}
