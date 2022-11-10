package com.magadiflo.usuarios.services;

import org.springframework.stereotype.Service;

import com.magadiflo.commons.services.CommonServiceImpl;
import com.magadiflo.usuarios.models.entity.Alumno;
import com.magadiflo.usuarios.models.repository.IAlumnoRepository;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, IAlumnoRepository> implements IAlumnoService {

	public AlumnoServiceImpl(IAlumnoRepository repository) {
		super(repository);
	}

}
