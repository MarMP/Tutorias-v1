package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;

public class Citas {

	private List<Cita> coleccionCitas;

	public Citas() {
		coleccionCitas = new ArrayList<>();
	}

	public List<Cita> get() {
		return copiaProfundaCitas();
	}

	private List<Cita> copiaProfundaCitas() {
		List<Cita> copiaCita = new ArrayList<>();
		for (Cita cita : coleccionCitas) {
			copiaCita.add(new Cita(cita));
		}
		return copiaCita;
	}

	public List<Cita> get(Sesion sesion) {
		if (sesion == null) {
			throw new NullPointerException("ERROR: La sesión no puede ser nula.");
		}
		List<Cita> citasSesion = new ArrayList<>();
		for (Cita cita : coleccionCitas) {
			if (cita.getSesion().equals(sesion)) {
				citasSesion.add(new Cita(cita));
			}
		}
		return citasSesion;
	}

	public List<Cita> get(Alumno alumno) {
		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		List<Cita> citasAlumno = new ArrayList<>();
		for (Cita cita : coleccionCitas) {
			if (cita.getAlumno().equals(alumno)) {
				citasAlumno.add(new Cita(cita));
			}
		}
		return citasAlumno;
	}

	public int getTamano() {
		return coleccionCitas.size();
	}

	public void insertar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}
		int indice = coleccionCitas.indexOf(cita);

		if (indice == -1) {
			coleccionCitas.add(new Cita(cita));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita con esa hora.");
		}
	}

	public Cita buscar(Cita cita) {
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una cita nula.");
		}
		int indice = coleccionCitas.indexOf(cita);

		if (indice == -1) {
			return null;
		} else {
			return new Cita(coleccionCitas.get(indice));
		}

	}

	public void borrar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}
		int indice = coleccionCitas.indexOf(cita);

		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita con esa hora.");
		} else {
			coleccionCitas.remove(indice);
		}
	}

}
