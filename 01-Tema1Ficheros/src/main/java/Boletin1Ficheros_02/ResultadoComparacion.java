
package Boletin1Ficheros_02;

import java.util.Objects;

public class ResultadoComparacion {

    private String nombreArchivo;
    private ValorComparacion valor;

    public ResultadoComparacion(String nombreArchivo, ValorComparacion valor) {
        this.nombreArchivo = nombreArchivo;
        this.valor = valor;
    }

    public String getNomFichero() {
        return nombreArchivo;
    }

    public ValorComparacion getValorComparacion() {
        return valor;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public ValorComparacion getValor() {
        return valor;
    }

    public void setValor(ValorComparacion valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreArchivo, valor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ResultadoComparacion other = (ResultadoComparacion) obj;

        return Objects.equals(nombreArchivo, other.nombreArchivo)
                && valor == other.valor;
    }

    @Override
    public String toString() {
        return "ResultadoComparacion [nombreArchivo=" + nombreArchivo
                + ", valor=" + valor + "]";
    }
}
