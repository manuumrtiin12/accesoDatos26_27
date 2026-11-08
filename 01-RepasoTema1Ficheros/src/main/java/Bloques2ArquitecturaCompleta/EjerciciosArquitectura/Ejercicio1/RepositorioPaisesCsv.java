package Bloques2ArquitecturaCompleta.EjerciciosArquitectura.Ejercicio1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class RepositorioPaisesCsv implements IRepositorioPaises {

    private static final String SEPARADOR_CSV = ",";
    private final File fichero;
    private List<Pais> paisesCache; // Memoria interna

    public RepositorioPaisesCsv(String rutaFichero) {
        this.fichero = new File(rutaFichero); // Comprobación de ruta con File
        this.paisesCache = null; // Carga perezosa inicial
    }

    // Carga perezosa: solo lee del disco la primera vez que se consulta
    private void cargarSiHaceFalta() throws RepositorioException {
        if (this.paisesCache == null) {
            this.paisesCache = leerFichero();
        }
    }

    // Lectura del fichero CSV e importación a la lista
    private List<Pais> leerFichero() throws RepositorioException {
        List<Pais> lista = new ArrayList<>();

        if (!fichero.exists()) {
            return lista; // Si el fichero no existe físicamente, devolvemos lista vacía
        }
        if (!fichero.canRead()) {
            throw new RepositorioException("Sin permisos de lectura en: " + fichero.getAbsolutePath());
        }

        Scanner sc = null;
        try {
            FileReader fr = new FileReader(fichero, StandardCharsets.UTF_8);
            sc = new Scanner(fr);
            sc.useLocale(Locale.ENGLISH); // Para mantener consistencia con números

            // 1. Saltar la cabecera (Nombre,Continente,Población,Idioma,Moneda)
            if (sc.hasNextLine()) {
                sc.nextLine();
            }

            // 2. Procesar fila a fila
            int numLinea = 1;
            while (sc.hasNextLine()) {
                numLinea++;
                String linea = sc.nextLine();
                if (linea.trim().isEmpty()) continue; // Ignorar líneas en blanco

                try {
                    Pais p = parsearLineaCSV(linea);
                    lista.add(p);
                } catch (IllegalArgumentException e) {
                    // Falla la validación (falta nombre/continente, número inválido, columnas...)
                    System.err.println("[AVISO REPOSITORIO] Línea " + numLinea + " descartada: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RepositorioException("Error al leer el archivo CSV de países", e);
        } finally {
            if (sc != null) {
                sc.close(); // Cierra Scanner y el FileReader envuelto
            }
        }
        return lista;
    }

    // Parseo de línea CSV a Objeto Pais
    // Columnas: 0 Nombre, 1 Continente, 2 Población, 3 Idioma, 4 Moneda
    private Pais parsearLineaCSV(String linea) {
        String[] campos = linea.split(SEPARADOR_CSV); // Dividir por comas
        if (campos.length < 5) {
            throw new IllegalArgumentException("La línea no contiene las 5 columnas requeridas.");
        }

        String nombre = campos[0].trim();
        String continente = campos[1].trim();
        long poblacion = Long.parseLong(campos[2].trim());

        // Procesar lista de idiomas separados por espacios
        String idiomasTexto = campos[3].trim();
        List<String> listaIdiomas = new ArrayList<>();
        if (!idiomasTexto.isEmpty()) {
            String[] arrayIdiomas = idiomasTexto.split(" "); // Split por espacio en blanco
            for (String idm : arrayIdiomas) {
                if (!idm.trim().isEmpty()) {
                    listaIdiomas.add(idm.trim());
                }
            }
        }

        String moneda = campos[4].trim();

        // El constructor de Pais valida nombre y continente no vacíos
        return new Pais(nombre, continente, poblacion, listaIdiomas, moneda);
    }

    // Escritura de la lista completa a disco en formato CSV
    private void escribirFichero(List<Pais> lista) throws RepositorioException {
        File carpetaPadre = fichero.getParentFile();
        if (carpetaPadre != null && !carpetaPadre.exists()) {
            carpetaPadre.mkdir(); // Crear carpeta contenedora si no existe
        }

        PrintWriter out = null;
        try {
            FileWriter fw = new FileWriter(fichero, StandardCharsets.UTF_8, false); // Sobrescribir
            out = new PrintWriter(fw);

            // 1. Escribir siempre la cabecera CSV
            out.println("Nombre,Continente,Población,Idioma,Moneda");

            // 2. Escribir cada registro
            for (Pais p : lista) {
                // Unir la lista de idiomas por espacios para mantener el formato original
                String idiomasUnidos = String.join(" ", p.getIdiomas());

                out.printf(Locale.ENGLISH, "%s,%s,%d,%s,%s%n",
                        p.getNombre(),
                        p.getContinente(),
                        p.getPoblacion(),
                        idiomasUnidos,
                        p.getMoneda());
            }

            if (out.checkError()) {
                throw new RepositorioException("Error al volcar datos en el archivo CSV.");
            }
        } catch (IOException e) {
            throw new RepositorioException("Error I/O al escribir el archivo de países", e);
        } finally {
            if (out != null) {
                out.close(); // Flush automático y cierre del flujo
            }
        }
    }

    @Override
    public List<Pais> findAll() throws RepositorioException {
        cargarSiHaceFalta();
        return new ArrayList<>(paisesCache);
    }

    @Override
    public Pais findById(String nombre) throws RepositorioException {
        cargarSiHaceFalta();
        for (Pais p : paisesCache) {
            if (p.getNombre().equalsIgnoreCase(nombre.trim())) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean existsById(String nombre) throws RepositorioException {
        return findById(nombre) != null;
    }

    @Override
    public void save(Pais pais) throws RepositorioException {
        cargarSiHaceFalta();
        List<Pais> copia = new ArrayList<>(paisesCache);

        int pos = -1;
        for (int i = 0; i < copia.size(); i++) {
            if (copia.get(i).getNombre().equalsIgnoreCase(pais.getNombre())) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            copia.add(pais); // Alta
        } else {
            copia.set(pos, pais); // Modificación
        }

        // Regla: 1º Escribir en fichero, 2º Actualizar memoria
        escribirFichero(copia);
        this.paisesCache = copia;
    }

    @Override
    public boolean deleteById(String nombre) throws RepositorioException {
        cargarSiHaceFalta();
        List<Pais> copia = new ArrayList<>(paisesCache);

        boolean eliminado = copia.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre.trim()));

        if (eliminado) {
            // Regla: 1º Escribir en fichero, 2º Actualizar memoria
            escribirFichero(copia);
            this.paisesCache = copia;
        }
        return eliminado;
    }
}