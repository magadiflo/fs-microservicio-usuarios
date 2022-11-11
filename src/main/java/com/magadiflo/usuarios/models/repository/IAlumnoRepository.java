package com.magadiflo.usuarios.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.magadiflo.commons.alumnos.models.entity.Alumno;

public interface IAlumnoRepository extends CrudRepository<Alumno, Long> {

	@Query("SELECT a FROM Alumno AS a WHERE a.nombre LIKE %?1% OR a.apellido LIKE %?1%")
	List<Alumno> encontrarPorNombreOrApellido(String termino);

}
