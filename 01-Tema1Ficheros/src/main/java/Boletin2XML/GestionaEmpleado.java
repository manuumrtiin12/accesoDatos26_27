package Boletin2XML;

public class GestionaEmpleado {
	
	Puesto desarrollador = new Puesto(1, "Desarrollador", "Tecnología", 32000);
	Puesto analista = new Puesto(2, "Analista de datos", "Datos", 29000);
	Puesto sysadmin = new Puesto("Administrador de sistemas", 30000); 

	Empleado e1 = new Empleado(1, "Laura Gómez", 28, "TechSur", desarrollador);
	Empleado e2 = new Empleado(2, "Carlos Ruiz", 35, "TechSur", sysadmin);
	Empleado e3 = new Empleado(3, "Marta Díaz", 31, "DataGranada", analista);
	Empleado e4 = new Empleado(4, "Javier Moreno", 26, "DataGranada", desarrollador);
	Empleado e5 = new Empleado(5, "Ana Torres", 30, "TechSur", analista); 

}
