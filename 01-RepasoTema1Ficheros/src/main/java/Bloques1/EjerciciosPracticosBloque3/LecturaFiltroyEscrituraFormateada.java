package Bloques1.EjerciciosPracticosBloque3;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LecturaFiltroyEscrituraFormateada {

	public List<Empleado> filtroEmpleados(String ruta) throws IOException {

		List<Empleado> filtrados = new ArrayList<Empleado>();
		Gson gson = new Gson();

		try (FileReader fr = new FileReader(ruta)) {

			Empleado[] empleados = gson.fromJson(fr, Empleado[].class);

			if (empleados != null) {
				for (Empleado e : empleados) {
					if (e.getEdad() > 30) {
						filtrados.add(e);
					}
				}
			}
		}

		// Escritura en formato indentado
		Gson gsonBonito = new GsonBuilder().setPrettyPrinting().create();

		try (FileWriter fw = new FileWriter("empleados_veteranos.json")) {
			gsonBonito.toJson(filtrados, fw);
		}

		return filtrados;
	}
	

	public static void main(String[] args) throws IOException {
		new LecturaFiltroyEscrituraFormateada().filtroEmpleados("empleados.json");
	}
}

class Empleado {

	private String nombre;
	private int edad;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
}