package UNIDAD_1.Boletin2_XML.Ej1.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import UNIDAD_1.Boletin2_XML.Ej1.Models.Empleado;
import UNIDAD_1.Boletin2_XML.Ej1.Models.Puesto;
import UNIDAD_1.Boletin2_XML.Ej1.Repository.UtilesXMLDom;
import UNIDAD_1.Boletin2_XML.Ej1.Service.EmpleadoService;
import UNIDAD_1.Boletin2_XML.Ej1.Service.IEmpleadoService;

public class Gestiona {
	public static void main(String[] args) {
	
//	    Set<Empleado> empleados = new TreeSet<>();
//
//	    Puesto p1 = new Puesto("1", "Programador", "Informática", 2200.0);
//	    Puesto p2 = new Puesto("2", "Diseñador", "Diseño", 1900.0);
//	    Puesto p3 = new Puesto("3", "Analista", "Informática", 2500.0);
//	    Puesto p4 = new Puesto("4", "Contable", "Administración", 1800.0);
//	    Puesto p5 = new Puesto("5", "Director", "Dirección", 3500.0);
//
//	    empleados.add(new Empleado("1", "Adrián López", 25, "Google", p1));
//	    empleados.add(new Empleado("2", "María García", 30, "Microsoft", p2));
//	    empleados.add(new Empleado("3", "Carlos Fernández", 28, "Amazon", p3));
//	    empleados.add(new Empleado("4", "Laura Sánchez", 35, "Apple", p4));
//	    empleados.add(new Empleado("5", "Pablo Martínez", 42, "IBM", p5));
	    
	    IEmpleadoService servicio = new EmpleadoService("empleados_100.xml");
	    Set<Empleado> lista = servicio.findAll();
//	    for (Empleado empleado : lista) {
//			System.out.println(empleado);
//		}
	    Set<Empleado> listaOrdenada = servicio.ordenarPorSalario(lista);
	    System.out.println(listaOrdenada);
	    servicio.save("empleadosBien.xml", listaOrdenada);
	}
}
