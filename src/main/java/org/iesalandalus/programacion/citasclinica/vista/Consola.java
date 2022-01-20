package org.iesalandalus.programacion.citasclinica.vista;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	//Constructor consola (utilidad, por lo tanto no se instancian objetos)
	private Consola() {
		
	}
	
	//M�todo mostrarMenu
	public static void mostrarMenu() {
		System.out.println("Bienvenido a la cl�nica, estas son las opciones que puedes elegir:");
		System.out.println("1. Insertar una cita.");
		System.out.println("2. Buscar una cita.");
		System.out.println("3. Borrar una cita.");
		System.out.println("4. Ver las citas con una fecha determinada.");
		System.out.println("5. Ver todas las citas.");
		System.out.println("0. Salir.");
	}
	
	//M�todo elegirOpcion()
	public static Opciones elegirOpcion() {
		Opciones[] opcion = Opciones.values();
		System.out.println("");
		System.out.println("Por favor, elija una opci�n");
		System.out.println("");
		int opcionElegida = Entrada.entero();
		while(opcionElegida < 0 || opcionElegida > 5) {
			System.out.println("Por favor, elija una opci�n comprendida entre 0 y 5: ");
			opcionElegida = Entrada.entero();
		}
		return opcion[opcionElegida];
	}
	
	
	//M�todo leerCita
	public static Cita leerCita() throws OperationNotSupportedException {		
	Cita cita = new Cita(leerPaciente(), leerFechaHora());
	return cita;
	}
	
	//M�todo leerPaciente
	public static Paciente leerPaciente() throws OperationNotSupportedException {
	Paciente paciente;
		System.out.println("Introduzca el nombre:");
		String nombre = Entrada.cadena();
		System.out.println("Introduzca el dni:");
		String dni = Entrada.cadena();
		System.out.println("Introduzca el n�mero de tel�fono:");
		String telefono = Entrada.cadena();
		paciente = new Paciente(nombre, dni, telefono);
		return paciente;
	}
	
	//M�todo leerFechaHora
	public static LocalDateTime leerFechaHora() {
	String formatoCadena = "dd/MM/yyyy HH:mm";
	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(formatoCadena);
	LocalDateTime fechaHora = null;
	boolean fechaValida = false;
		do {
			try {
				System.out.println("Introduzca una fecha y hora con el siguiente formato: dd/MM/aaaa HH:mm:");
				fechaHora = LocalDateTime.parse(Entrada.cadena(), formatoFecha);
				fechaValida = true;
			} catch (DateTimeParseException e) {
				fechaValida = false;
			}
		} while (!fechaValida);
		return fechaHora;
	}
	
	//M�todo leerFecha
	public static LocalDate leerFecha() {
	String formatoCadena = "dd/MM/yyyy";
	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(formatoCadena);
	LocalDate fecha = null;
	boolean fechaValida = false;
		do {
			try {
				System.out.println("Introduzca una fecha con el siguiente formato: dd/MM/aaaa:");
				fecha = LocalDate.parse(Entrada.cadena(), formatoFecha);
				fechaValida = true;
			} catch (DateTimeParseException e) {
				fechaValida = false;
			}
		} while (!fechaValida);
		return fecha;
	}
}
