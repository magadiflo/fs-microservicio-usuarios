package com.magadiflo.usuarios.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.magadiflo.usuarios.models.entity.Alumno;

public interface IAlumnoRepository extends CrudRepository<Alumno, Long> {
	
}
