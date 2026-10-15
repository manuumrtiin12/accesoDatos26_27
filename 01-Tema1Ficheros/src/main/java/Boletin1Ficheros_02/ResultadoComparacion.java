package Boletin1Ficheros_02;

import java.time.LocalDate;
import java.util.Objects;

public class ResultadoComparacion {

	private String nombreArchivo;
	private valorResultadoComparacion valor;
	private LocalDate fechaModificacion;
	
	
	String getNomFichero() {
		
		return getNombreArchivo();
	}
	
	valorResultadoComparacion getValorComparacion() {
		
		return getValor();
	}
	
	public ResultadoComparacion(String nombreArchivo, valorResultadoComparacion valor, LocalDate fechaModificacion) {
		super();
		this.nombreArchivo = nombreArchivo;
		this.valor = valor;
		this.fechaModificacion = fechaModificacion;
	}


	public String getNombreArchivo() {
		return nombreArchivo;
	}


	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}


	public valorResultadoComparacion getValor() {
		return valor;
	}


	public void setValor(valorResultadoComparacion valor) {
		this.valor = valor;
	}


	public LocalDate getFechaModificacion() {
		return fechaModificacion;
	}


	public void setFechaModificacion(LocalDate fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}


	@Override
	public int hashCode() {
		return Objects.hash(fechaModificacion, nombreArchivo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultadoComparacion other = (ResultadoComparacion) obj;
		return Objects.equals(fechaModificacion, other.fechaModificacion)
				&& Objects.equals(nombreArchivo, other.nombreArchivo);
	}


	@Override
	public String toString() {
		return "ResultadoComparacion [nombreArchivo=" + nombreArchivo + ", valor=" + valor + ", fechaModificacion="
				+ fechaModificacion + "]";
	}
	
	
	
	
}
