package org.iesalandalus.programacion.tutorias.mvc.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Alumnos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Citas;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Profesores;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Sesiones;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.Tutorias;

public class Modelo {

	private Alumnos alumnos;
	private Citas citas;
	private Profesores profesores;
	private Tutorias tutorias;
	private Sesiones sesiones;

	public Modelo() {
		alumnos = new Alumnos();
		profesores = new Profesores();
		tutorias = new Tutorias();
		sesiones = new Sesiones();
		citas = new Citas();
	}

	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.insertar(alumno);
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		profesores.insertar(profesor);
	}

	public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: No se puede insertar una tutoría nula.");
		}
		Profesor profesor = profesores.buscar(tutoria.getProfesor());
		if (profesor == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese DNI.");
		}
		tutorias.insertar(new Tutoria (profesor, tutoria.getNombre())); 
	}

	public void insertar(Sesion sesion) throws OperationNotSupportedException {
		if (sesion == null) {
			throw new NullPointerException("ERROR: No se puede insertar una sesión nula.");
		}
		Tutoria tutoria = tutorias.buscar(sesion.getTutoria());
		if (tutoria == null) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna tutoria.");
		}
		sesiones.insertar(new Sesion (tutoria, sesion.getFecha(), sesion.getHoraInicio(),sesion.getHoraFin(),sesion.getMinutosDuracion()));
	}

	public void insertar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}
		Alumno alumno = alumnos.buscar(cita.getAlumno());
		if (alumno == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese correo.");
		} 
		Sesion sesion = sesiones.buscar(cita.getSesion());
		if (sesion == null) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna sesión con esa tutoria y fecha.");
		} 
		citas.insertar(new Cita(alumno, sesion, cita.getHora()));  
	}

	public Alumno buscar(Alumno alumno) {
		return alumnos.buscar(alumno);
	}

	public Profesor buscar(Profesor profesor) {
		return profesores.buscar(profesor);
	}

	public Tutoria buscar(Tutoria tutoria) {
		return tutorias.buscar(tutoria);
	}

	public Sesion buscar(Sesion sesion) {
		return sesiones.buscar(sesion);
	}

	public Cita buscar(Cita cita) {
		return citas.buscar(cita);
	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {
		alumnos.borrar(alumno);
	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		profesores.borrar(profesor);
	}

	public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
		tutorias.borrar(tutoria);
	}

	public void borrar(Sesion sesion) throws OperationNotSupportedException {
		sesiones.borrar(sesion);
	}

	public void borrar(Cita cita) throws OperationNotSupportedException {
		citas.borrar(cita);
	}

	public List <Alumno> getAlumnos() {
		return alumnos.get();
	}

	public List<Profesor> getProfesores() {
		return profesores.get();
	}

	public List<Tutoria> getTutorias() {
		return tutorias.get();
	}

	public List<Tutoria> getTutorias(Profesor profesor) {
		return tutorias.get(profesor);
	}

	public List<Sesion> getSesiones() {
		return sesiones.get();
	}

	public List<Sesion> getSesiones(Tutoria tutoria) {
		return sesiones.get(tutoria); 
	}

	public List <Cita> getCitas() {
		return citas.get();
	}

	public List<Cita> getCitas(Sesion sesion) {
		return citas.get(sesion);
	}

	public List<Cita> getCitas(Alumno alumno) {
		return citas.get(alumno);
	}

}
