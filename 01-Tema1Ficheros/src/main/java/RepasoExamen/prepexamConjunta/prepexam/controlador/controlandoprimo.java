package RepasoExamen.prepexamConjunta.prepexam.controlador;

import prepexamConjunta.prepexam.Service.ServicioEmpleado;



public class controlandoprimo {
	public static void main(String[] args) {
		ServicioEmpleado servicio = new ServicioEmpleado("src/main/resources/empleados.csv");
		System.out.println(servicio.findall());
		servicio.escribirJSON("src/main/resources/empleados.json");
	
	}

}
