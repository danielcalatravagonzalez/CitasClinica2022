package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cita {
	//Atributos
	private static final String FORMATO_FECHA_HORA = ("dd/MM/yyyy HH:mm");
	private LocalDateTime fechaHora;
	public Paciente paciente;
	
	//Constructores
	//Constructor con parámetros
	public Cita (Paciente paciente, LocalDateTime fechaHora) {
		setPaciente(paciente);
		setFechaHora(fechaHora);
	}
	
	//Constructor copia
	public Cita (Cita eCita ) {
		if (eCita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		} else {
			setPaciente(eCita.getPaciente());
			setFechaHora(eCita.getFechaHora());
		}
	}

	//Métodos Getters y Setters
	//Get y Set de Paciente
	public Paciente getPaciente() {
		return paciente;
	}
	
	private void setPaciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
		} else {
			this.paciente = new Paciente (paciente);
		}
	}
	
	//Get y Set de fechaHora
	public void setFechaHora (LocalDateTime fechaHora) {
		if (fechaHora == null) {
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
		} else {
			this.fechaHora = fechaHora;
		}
	}
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	
	//Métodos hashCode y equals
	@Override
	public int hashCode() {
		return Objects.hash(fechaHora);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Cita))
			return false;
		Cita other = (Cita) obj;
		return Objects.equals(fechaHora, other.fechaHora);
	}

	//Método toString
	@Override
	public String toString() {
		return paciente + ", fechaHora=" + fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA)) + "";
	}
	
}
