package RepasoExamen.prepexamConjunta.prepexam.Modelo;

public class Direccion {
	private String calle;
	private String ciudad;
	
	
	
	public Direccion() {
		super();
	}



	public String getCalle() {
		return calle;
	}



	public void setCalle(String calle) {
		this.calle = calle;
	}



	public String getCiudad() {
		return ciudad;
	}



	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}



	@Override
	public String toString() {
		return "Direccion [calle=" + calle + ", ciudad=" + ciudad + "]";
	}

	
}
