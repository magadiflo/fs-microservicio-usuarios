package com.magadiflo.usuarios.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magadiflo.commons.alumnos.models.entity.Alumno;
import com.magadiflo.commons.services.CommonServiceImpl;

import com.magadiflo.usuarios.models.repository.IAlumnoRepository;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, IAlumnoRepository> implements IAlumnoService {

	public AlumnoServiceImpl(IAlumnoRepository repository) {
		super(repository);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Alumno> encontrarPorNombreOrApellido(String termino) {
		return this.repository.encontrarPorNombreOrApellido(termino);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAllById(Iterable<Long> ids) {
		return this.repository.findAllById(ids);
	}

}
