package Bloques1.EjerciciosPracticosBloque2;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import org.apache.logging.log4j.core.Logger;

public class Producto {

	private int id;
	private String nombre;
	private double precio;
	private int stock;
	
	List<Producto> cargarCSV(String ruta) throws IOException {
    List<Producto> productos = new ArrayList<Producto>();
    
    FileReader fr = null;
    Scanner sc = null;
    
    try {
        fr = new FileReader(ruta);
        sc = new Scanner(fr);
        sc.useLocale(Locale.ENGLISH);
        
        // 1. Saltar cabecera
        if (sc.hasNextLine()) {
            sc.nextLine();
        }
        
        // 2. Leer registros
        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            
            // Protección opcional contra líneas en blanco
            if (linea.trim().isEmpty()) continue;
            
            String[] atributos = linea.split(",");
            
            if (atributos.length == 4) {
                int id = Integer.parseInt(atributos[0].trim());
                String nombre = atributos[1].trim();
                double precio = Double.parseDouble(atributos[2].trim());
                int stock = Integer.parseInt(atributos[3].trim());
                
                Producto pAñadir = new Producto(id, nombre, precio, stock);
                productos.add(pAñadir);
            }
        }
    } finally {
        // El bloque finally se ejecuta SIEMPRE, haya o no excepción
        if (sc != null) {
            sc.close(); // Al cerrar el Scanner, se cierra automáticamente el FileReader (fr) envuelto
        }
    }
    
    return productos;
}

	public Producto(int id, String nombre, double precio, int stock) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}
	
	
	
}
