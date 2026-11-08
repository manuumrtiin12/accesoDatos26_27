package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Pais {
    private String nombre;
    private String continente;
    private long poblacion;
    private List<String> idiomas; // Se almacena como colección en memoria
    private String moneda;

    public Pais(String nombre, String continente, long poblacion, List<String> idiomas, String moneda) {
        // Regla de validación: Nombre y Continente son obligatorios
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del país no puede estar vacío.");
        }
        if (continente == null || continente.trim().isEmpty()) {
            throw new IllegalArgumentException("El continente no puede estar vacío.");
        }

        this.nombre = nombre.trim();
        this.continente = continente.trim();
        this.poblacion = poblacion;
        this.idiomas = (idiomas != null) ? new ArrayList<>(idiomas) : new ArrayList<>();
        this.moneda = (moneda != null) ? moneda.trim() : "";
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public String getContinente() { return continente; }
    public long getPoblacion() { return poblacion; }
    public List<String> getIdiomas() { return idiomas; }
    public String getMoneda() { return moneda; }

    // Método de utilidad para añadir un idioma sin duplicados
    public boolean anadirIdioma(String nuevoIdioma) {
        if (nuevoIdioma != null && !nuevoIdioma.trim().isEmpty()) {
            String limpio = nuevoIdioma.trim();
            if (!this.idiomas.contains(limpio)) {
                this.idiomas.add(limpio);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Pais[Nombre=%-10s | Continente=%-8s | Población=%-10d | Idiomas=%s | Moneda=%s]",
                nombre, continente, poblacion, String.join(", ", idiomas), moneda);
    }
}