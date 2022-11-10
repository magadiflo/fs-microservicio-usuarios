package com.magadiflo.usuarios.services;

import java.util.Optional;

import com.magadiflo.usuarios.models.entity.Alumno;

public interface IAlumnoService {
	Iterable<Alumno> findAll();

	Optional<Alumno> findById(Long id);

	Alumno save(Alumno alumno);

	void deleteById(Long id);
}
