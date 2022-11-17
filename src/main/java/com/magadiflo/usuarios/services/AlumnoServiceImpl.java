package com.magadiflo.usuarios.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magadiflo.commons.alumnos.models.entity.Alumno;
import com.magadiflo.commons.services.CommonServiceImpl;
import com.magadiflo.usuarios.client.ICursoFeignClient;
import com.magadiflo.usuarios.models.repository.IAlumnoRepository;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, IAlumnoRepository> implements IAlumnoService {

	private final ICursoFeignClient cursoFeignClient;

	public AlumnoServiceImpl(IAlumnoRepository repository, ICursoFeignClient cursoFeignClient) {
		super(repository);
		this.cursoFeignClient = cursoFeignClient;
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

	@Override
	@Transactional
	public void deleteById(Long id) {
		super.deleteById(id);
		this.eliminarCursoAlumnoPorId(id);
	}

	// Como es un cliente HTTP con Feign, el transactional no va
	@Override
	public void eliminarCursoAlumnoPorId(Long id) {
		this.cursoFeignClient.eliminarCursoAlumnoPorId(id);
	}

}
