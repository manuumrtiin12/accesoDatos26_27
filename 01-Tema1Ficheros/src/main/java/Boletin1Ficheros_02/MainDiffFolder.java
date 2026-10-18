
package Boletin1Ficheros_02;

import java.util.Collection;

public class MainDiffFolder {

    public static void main(String[] args) {

        try {
            DiffFolder comparador = new DiffFolder(
                    "C:\\Prueba\\Carpeta1",
                    "C:\\Prueba\\Carpeta2");

            Collection<ResultadoComparacion> resultados = comparador.comparame();

            for (ResultadoComparacion resultado : resultados) {
                System.out.println(resultado);
            }

        } catch (DiffFolderException e) {
            System.out.println(e.getMessage());
        }
    }
}
