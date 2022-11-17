package com.magadiflo.usuarios.services;

import java.util.List;

import com.magadiflo.commons.alumnos.models.entity.Alumno;
import com.magadiflo.commons.services.ICommonService;

public interface IAlumnoService extends ICommonService<Alumno> {
	List<Alumno> encontrarPorNombreOrApellido(String termino);

	Iterable<Alumno> findAllById(Iterable<Long> ids);
}
