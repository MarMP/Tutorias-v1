package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public class Sesiones {

	private List<Sesion> coleccionSesiones;

	public Sesiones() {
		coleccionSesiones = new ArrayList<>();
	}

	public List<Sesion> get() {
		return copiaProfundaSesiones();
	}

	private List<Sesion> copiaProfundaSesiones() {
		List <Sesion> copiaSesion = new ArrayList<>();
		for (Sesion sesion : coleccionSesiones) {
			coleccionSesiones.add(new Sesion(sesion));
		}
		return copiaSesion;
	}

	public List<Sesion> get(Tutoria tutoria) {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: La tutoría no puede ser nula.");
		}
		List<Sesion> sesionesTutoria = new ArrayList<>();
		for (Sesion sesion : coleccionSesiones) {
			if (sesion.getTutoria().equals(tutoria)) {
				sesionesTutoria.add(new Sesion(sesion));
			}
		}
		return sesionesTutoria;
	}

	public int getTamano() {
		return coleccionSesiones.size();
	}

	public void insertar(Sesion sesion) throws OperationNotSupportedException {
		if (sesion == null) {
			throw new NullPointerException("ERROR: No se puede insertar una sesión nula.");
		}
		int indice = coleccionSesiones.indexOf(sesion);
		if(indice == -1) {
			coleccionSesiones.add(new Sesion(sesion));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una sesión con esa fecha.");
		}
	}

	public Sesion buscar(Sesion sesion) {	
		if (sesion == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una sesión nula.");
		}
		int indice = coleccionSesiones.indexOf(sesion);
		if (indice == -1) {
			return null;
		} else {
			return new Sesion (coleccionSesiones.get(indice));
		}

	}

	public void borrar(Sesion sesion) throws OperationNotSupportedException {
		if (sesion == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una sesión nula.");
		}
		int indice = coleccionSesiones.indexOf(sesion);
		
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna sesión con esa fecha.");
		} else {
			coleccionSesiones.remove(indice);
		}
	}
}
