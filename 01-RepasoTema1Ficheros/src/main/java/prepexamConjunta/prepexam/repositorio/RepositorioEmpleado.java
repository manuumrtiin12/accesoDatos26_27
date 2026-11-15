package prepexamConjunta.prepexam.repositorio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import prepexamConjunta.prepexam.*;
import prepexamConjunta.prepexam.Modelo.Direccion;
import prepexamConjunta.prepexam.Modelo.Empleado;
import prepexamConjunta.prepexam.Modelo.Puesto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEmpleado implements IRepositorioEmpleado {
	private File fichero;
	private List<Empleado> empleados; // null = todavía no se ha leído

	public RepositorioEmpleado(String ruta) {
		fichero = new File(ruta);
	}

	private List<Empleado> leerFichero() throws RepositorioException {
		List<Empleado> lista = new ArrayList<>();
		if (fichero.exists()) {
			if (!fichero.isFile() || !fichero.canRead()) {
				throw new RepositorioException("Sin permiso de lectura", null);
			}
			Scanner in = null;
			try {
				in = new Scanner(new FileReader(fichero, StandardCharsets.UTF_8));
				in.nextLine(); // esto lo hago para saltar la cabecera
				while (in.hasNextLine()) {
					String linea = in.nextLine();
					if (!linea.isBlank()) {
						try {
							String[] campos = linea.split(";");
							int edad = 0;
							String nombre = null;
							boolean activo = false;
							Puesto puesto = null;
							Direccion direccion = null;
							String calle = "Desconocido";
							String ciudad = "Desconocido";
							for (int i = 0; i < campos.length; i++) {
								if (i == 0) {
									edad = Integer.parseInt(campos[i]);
								}
								if (i == 1) {
									nombre = campos[i];
								}
								if (i == 2) {
									activo = Boolean.parseBoolean(campos[i]);
								}
								if (i == 3) {
									if (campos[i].equals("DESARROLLADOR")) {
										puesto = Puesto.DESARROLLADOR;
									} else if (campos[i].equals("ANALISTA")) {
										puesto = Puesto.ANALISTA;
									} else if (campos[i].equals("ADMINISTRADOR")) {
										puesto = Puesto.ADMINISTRADOR;
									}
								}
								if (i == 4) {
									calle = campos[i];
								}
								if (i == 5) {
									ciudad = campos[i];
								}
							}
							if (!calle.equals("Desconocido") || !ciudad.equals("Desconocido")) {
								direccion = new Direccion();
								direccion.setCalle(calle);
								direccion.setCiudad(ciudad);
							}
							Empleado e = new Empleado();
							e.setEdad(edad);
							e.setNombre(nombre);
							e.setActivo(activo);
							e.setPuesto(puesto);
							e.setDireccion(direccion);
							
							lista.add(e);

						} catch (IllegalArgumentException e) {
							throw new RepositorioException("", e);
						}
					}
				}
			}

			catch (IOException e) {
			}

		}
		return lista;

	}
	
	
	private void escribirFichero(){
		try {
			FileWriter fw = new FileWriter(fichero);
			PrintWriter pw = new PrintWriter(fw);
			pw.println("id;nombre;activo;puesto;calle;ciudad");
			for (Empleado empleado : empleados) {
				pw.println(empleado.getEdad() + ";" + empleado.getNombre() + ";" + empleado.isActivo() + ";" + empleado.getPuesto() + ";" + empleado.getDireccion().getCalle() + ";" + empleado.getDireccion().getCiudad());
			}
			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void escribirJSON(String RutaJson) throws RepositorioException {  
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(empleados);
        FileWriter out = null;
        try {
            out = new FileWriter(RutaJson, StandardCharsets.UTF_8);
            out.write(json);
        } catch (IOException e) {
            throw new RepositorioException("Error al escribir " + RutaJson, e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    throw new RepositorioException("Error al cerrar " + RutaJson, e);
                }
            }
        }
		
	}

	@Override
	public List<Empleado> findall() throws RepositorioException {
		if(empleados == null || empleados.size() == 0) {
			empleados = leerFichero();
		}
		return empleados;
	}

	@Override
	public Empleado findbyEdad(int edad) { //esto seria id lo que pasa es que no voy a refactorizar tood 
		Empleado emp = null;
		for (Empleado empleado : empleados) {
			if(edad == empleado.getEdad()) {
				emp = empleado;
			}
			
		}
		return emp;
	}

	@Override
	public void delete(int edad) {
		for (Empleado empleado : empleados) {
			if(edad == empleado.getEdad()) {
				empleados.remove(empleado);
			}
			
		}

	}

	@Override
	public boolean existbyEdad(int edad) {
		boolean esta = false;
		for (Empleado empleado : empleados) {
			if(edad == empleado.getEdad()) {
				esta = true;
			}
		}
		return esta;
	}

	@Override
	public void save(Empleado emp) throws RepositorioException {
		if(!empleados.contains(emp)) {
			empleados.add(emp);
			escribirFichero();		
		}
		else{
			throw new RepositorioException("Ya estaba primico", null);
		}
		

		
	}
	
	

}
