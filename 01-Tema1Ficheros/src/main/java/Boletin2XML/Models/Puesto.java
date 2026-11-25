package UNIDAD_1.Boletin2_XML.Ej1.Models;

public class Puesto {
	private String id;
	private String nombre;
	private String departamento;
	private Double salario;
	public Puesto(String nombre, Double salario) {
		super();
		this.nombre = nombre;
		this.salario = salario;
	}
	public Puesto(String id, String nombre, String departamento, Double salario) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.departamento = departamento;
		this.salario = salario;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	@Override
	public String toString() {
		return "Puesto [id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + ", salario=" + salario
				+ "]";
	}
	
}
