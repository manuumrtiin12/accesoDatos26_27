package Boletin1Ficheros_03.Ejercicio2.Modelos;

public class Pais {

    private String nombre;
    private String continente;
    private int población;
    private String idioma;
    private String moneda;

    public Pais(String nombre, String continente, int población, String idioma, String moneda) {
    	
        this.nombre = nombre;
        this.continente = continente;
        this.población = población;
        this.idioma = idioma;
        this.moneda = moneda;
    }

    public Pais(String nombre, String continente) {
        this.nombre = nombre;
        this.continente = continente;
    }
}