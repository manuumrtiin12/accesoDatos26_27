package Boletin2XML;

public class Empleado {
	
	private int id;
	private String nombreApellido;
	private int edad;
	private String empresa;
	private Puesto puesto;
	
	
	public Empleado(String nombreApellido, String empresa, Puesto puesto) {
		this.nombreApellido = nombreApellido;
		this.empresa = empresa;
		this.puesto = puesto;
	}
	
	public Empleado(int id, String nombreApellido, int edad, String empresa, Puesto puesto) {
		this.id = id;
		this.nombreApellido = nombreApellido;
		this.edad = edad;
		this.empresa = empresa;
		this.puesto = puesto;
	}

	


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombreApellido() {
		return nombreApellido;
	}


	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public String getEmpresa() {
		return empresa;
	}


	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}


	public Puesto getPuesto() {
		return puesto;
	}


	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}


	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombreApellido=" + nombreApellido + ", edad=" + edad + ", empresa=" + empresa
				+ ", puesto=" + puesto + "]";
	}
	
	
	
	

}
