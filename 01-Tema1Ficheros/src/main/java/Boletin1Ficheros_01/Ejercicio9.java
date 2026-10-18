package Boletin1Ficheros_01;

import java.io.File;
import java.util.Scanner;

public class Ejercicio9 {

    public static void main(String[] args) {

        File directorio = new File("C:\\Users\\mmanu\\Desktop\\pruebaEj9");

        Scanner sc = new Scanner(System.in);

        int cantidad = contarArchivos(directorio);

        System.out.println("Se van a eliminar " + cantidad + " archivos.");
        System.out.println("¿Quieres continuar? (si/no)");
        String respuesta = sc.nextLine();

        if (respuesta.equalsIgnoreCase("si")) {
            borrarDirectorio(directorio);
            System.out.println("Borrado completado.");
        } else {
            System.out.println("Borrado cancelado.");
        }

       
    }

    public static int contarArchivos(File dir) {

        int contador = 0;
        File[] archivos = dir.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    contador++;
                } else if (archivo.isDirectory()) {
                    contador += contarArchivos(archivo);
                }
            }
        }

        return contador;
    }

    public static void borrarDirectorio(File dir) {

        File[] archivos = dir.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    borrarDirectorio(archivo);
                } else {
                    archivo.delete();
                }
            }
        }

        dir.delete();
    }
}
