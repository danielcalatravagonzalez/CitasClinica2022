package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {
	//Atributos
	private static final String ER_DNI = ("([0-9]{8})([A-Za-z])");
	private static final String ER_TELEFONO = ("[69][0-9]{8}");
	private String nombre;
	private String dni;
	private String telefono;

	//Constructores
	//Constructor con par�metros
	public Paciente(String nombre, String dni, String telefono) {
		setNombre(nombre);
		setDni(dni);
		setTelefono(telefono);
	}

	//Constructor copia
	public Paciente(Paciente ePaciente)
	{
		if (ePaciente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");
		} else {
			setNombre(ePaciente.nombre);
			setDni(ePaciente.dni);
			setTelefono(ePaciente.telefono);
		}
	}

	//M�todos Get y Set de nombre
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().isEmpty()) { 
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vac�o.");
		} else {
		
			this.nombre = formateaNombre(nombre);
		}  

	}

	//M�todo formateaNombre
	private String formateaNombre(String nombre) {
		nombre = nombre.replaceAll("\\s{2,}", " ").trim();
		String [] palabras = nombre.split(" ");
		String nuevoNombre = "";
		for (int i=0; i<=palabras.length-1; i++) {
			palabras[i] = palabras[i].substring(0,1).toUpperCase() + palabras[i].substring(1).toLowerCase();
			nuevoNombre = nuevoNombre + palabras[i] + " ";
		}
		nombre = nuevoNombre.trim();
		return nombre;
	}

	//M�todos Get y Set de dni
	public String getDni() {
		return dni;
	}

	private void setDni(String dni) {
		if (dni == null || dni.trim().isEmpty()) { 
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vac�o.");
		} else {
			Pattern patron = Pattern.compile(ER_DNI);
			Matcher comparador = patron.matcher(dni);

			if (comparador.matches()) {
				if (comprobarLetraDni(dni) == true) {
					this.dni = dni;
				} else {
					throw new IllegalArgumentException("ERROR: La letra del DNI no es correcta.");
				}
			} else {
				throw new IllegalArgumentException("ERROR: El DNI no tiene un formato v�lido.");
			}
		}
	}

	//M�todo comprobarLetraDni
	private boolean comprobarLetraDni(String dni) {
		boolean verificador = false;

		Pattern patron = Pattern.compile(ER_DNI);
		Matcher comparador = patron.matcher(dni);

		if (comparador.matches()) {
			int numeroDni = Integer.parseInt(comparador.group(1));
			String letraDni = comparador.group(2);
			String[] letraValida = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q",
					"V", "H", "L", "C", "K", "E" };
			if (comparador.group(2).equals(letraValida[numeroDni%23])) {
				verificador = true;
			} 
		}
		return verificador;
	}	

	//M�todos Get y Set de telefono
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		if (telefono == null || telefono.trim().isEmpty()) { 
			throw new NullPointerException("ERROR: El tel�fono de un paciente no puede ser nulo o vac�o.");
		} else if (telefono.matches(ER_TELEFONO))
		{
			this.telefono = telefono; 
		}  
		else 
		{
			throw new IllegalArgumentException("ERROR: El tel�fono no tiene un formato v�lido.");
		}
	}

	//M�todos hashCode y equals
	@Override
	public int hashCode() {
		return Objects.hash(dni);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Paciente)) {
			return false;
		}
		Paciente other = (Paciente) obj;
		return Objects.equals(dni, other.dni);
	}

	//M�todo toString
	@Override
	public String toString() {
		return String.format("nombre=%s (%s), DNI=%s, tel�fono=%s", getNombre(), getIniciales(), 
				getDni(), getTelefono());  
	}

	//M�todo getIniciales
	public String getIniciales()
	{
		String []palabra=nombre.split(" ");
		String iniciales="";
		for (int i=0;i<palabra.length;i++) {
			iniciales+=palabra[i].charAt(0);
		}
		return iniciales.toUpperCase();
	}
	}