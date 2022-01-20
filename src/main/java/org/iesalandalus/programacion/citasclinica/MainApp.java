package org.iesalandalus.programacion.citasclinica;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
import org.iesalandalus.programacion.citasclinica.vista.Opciones;

public class MainApp {
	//Atributos
	public static final int NUM_MAX_CITAS = 10;
	private static Citas listaCitas = new Citas(NUM_MAX_CITAS);
	
	//M�todo main
	public static void main(String[] args) throws OperationNotSupportedException {
		System.out.println("-----------------------------------");
		System.out.println("Gesti�n de las citas de la Cl�nica.");
		System.out.println("-----------------------------------");
		System.out.println("");
		Consola.mostrarMenu();
		ejecutarOpcion(Consola.elegirOpcion());
	}
	
	//M�todo ejecutarOpcion
	private static void ejecutarOpcion(Opciones opcion) throws OperationNotSupportedException {
		switch (opcion) {
		case INSERTAR_CITA:
			insertarCita();
			break;
		case BUSCAR_CITA:
			buscarCita();
			break;
		case BORRAR_CITA:
			borrarCita();
			break;
		case MOSTRAR_CITAS_DIA:
			mostrarCitasDia();
			break;
		case MOSTRAR_CITAS:
			mostrarCitas();
			break;
		case SALIR:
			System.out.println("");
			System.out.print("�Has cerrado la sesi�n, hasta la pr�xima!");
			break;
		}
	}
	
	//M�todo insertarCita
	private static void insertarCita() throws OperationNotSupportedException {
		try {
			Cita cita = Consola.leerCita();
			listaCitas.insertar(cita);
			System.out.println("");
			System.out.println("Se ha asisngado la cita correctamente.");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} catch (IllegalArgumentException | OperationNotSupportedException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}

	}

	//M�todo buscarCita
	private static void buscarCita() throws OperationNotSupportedException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime fecha = Consola.leerFechaHora();
		Paciente paciente = new Paciente("prueba", "77152538G", "659874621");
		Cita cita = new Cita(paciente, fecha);
		Cita citaBuscada;
		citaBuscada = listaCitas.buscar(cita);
		if (citaBuscada == null) {
			System.out.println("");
			System.out.println("No existen citas para la fecha: " + fecha.format(formato));
			System.out.println("");
		} else {
			Cita[] citas = listaCitas.getCitas(fecha.toLocalDate());
			for (Cita citaEncontrada : citas) {
				if (cita.equals(citaEncontrada)) {
					System.out.println("");
					System.out.println("La cita es " + citaEncontrada);
					System.out.println("");
				}
			}

		}

		Consola.mostrarMenu();
		ejecutarOpcion(Consola.elegirOpcion());
	}

	//M�todo borrarCita
	private static void borrarCita() throws OperationNotSupportedException {
		LocalDateTime fechaHora = Consola.leerFechaHora();
		Paciente paciente = new Paciente("prueba", "77152538G", "659874621");
		Cita cita = new Cita(paciente, fechaHora);
		try {
			listaCitas.borrar(cita);
			System.out.println("");
			System.out.println("Cita borrada.");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} catch (OperationNotSupportedException e) {
			System.out.println("");
			System.out.println(e.getMessage());
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}

	}

	//M�todo mostrarCitasDia
	private static void mostrarCitasDia() throws OperationNotSupportedException {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fecha = Consola.leerFecha();
		System.out.println("");
		System.out.println("Lista de citas para fecha: " + fecha.format(formato));
		System.out.println("");
		Cita[] citasFecha = listaCitas.getCitas(fecha);
		boolean citasDisponibles = false;
		for (int i = 0; i <= citasFecha.length - 1; i++) {
			if (citasFecha[i] != null) {
				citasDisponibles = true;
			}
		}

		if (citasFecha.length == 0 || !citasDisponibles) {
			System.out.println("No hay citas para esta fecha.");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} else {
			for (Cita cita : citasFecha) {
				if (cita != null){
					System.out.println(cita);
				}
			}
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}
	}

	//M�todo mostrarCitas
	private static void mostrarCitas() throws OperationNotSupportedException {
		System.out.println("");
		System.out.println("Lista de todas las citas");
		System.out.println("------------------------");
		if (listaCitas.getTamano() == 0) {
			System.out.println("");
			System.out.println("No hay citas para mostrar.");
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		} else {
			Cita[] citas = listaCitas.getCitas();
			for (Cita cita : citas) {
				if (cita != null) {
					System.out.println(cita);
				}
			}
			System.out.println("");
			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}
	}
	
}