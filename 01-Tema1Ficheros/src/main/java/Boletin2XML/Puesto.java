package Boletin2XML;

public class Puesto {
	
	private int id;
	private String nombrePuesto;
	private String departamento;
	private int salario;
	
	
	public Puesto(int id, String nombrePuesto, String departamento, int salario) {
		this.id = id;
		this.nombrePuesto = nombrePuesto;
		this.departamento = departamento;
		this.salario = salario;
	}


	public Puesto(String nombrePuesto, int salario) {
		this.nombrePuesto = nombrePuesto;
		this.salario = salario;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombrePuesto() {
		return nombrePuesto;
	}


	public void setNombrePuesto(String nombrePuesto) {
		this.nombrePuesto = nombrePuesto;
	}


	public String getDepartamento() {
		return departamento;
	}


	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}


	public int getSalario() {
		return salario;
	}


	public void setSalario(int salario) {
		this.salario = salario;
	}


	@Override
	public String toString() {
		return "Puesto [id=" + id + ", nombrePuesto=" + nombrePuesto + ", departamento=" + departamento + ", salario="
				+ salario + "]";
	}
	
	
	

}
