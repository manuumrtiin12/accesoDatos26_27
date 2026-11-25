package UNIDAD_1.Boletin2_XML.Ej1.Models;

public class Empleado {
	private String id;
	private String nombreAp;
	private int edad;
	private String empresa;
	private Puesto puesto;
	public Empleado(String nombreAp, String empresa, Puesto puesto) {
		super();
		this.nombreAp = nombreAp;
		this.empresa = empresa;
		this.puesto = puesto;
	}
	public Empleado(String id, String nombreAp, int edad, String empresa, Puesto puesto) {
		super();
		this.id = id;
		this.nombreAp = nombreAp;
		this.edad = edad;
		this.empresa = empresa;
		this.puesto = puesto;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombreAp() {
		return nombreAp;
	}
	public void setNombreAp(String nombreAp) {
		this.nombreAp = nombreAp;
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
		return "Empleado [id=" + id + ", nombreAp=" + nombreAp + ", edad=" + edad + ", empresa=" + empresa + ", puesto="
				+ puesto + "]";
	}
}
