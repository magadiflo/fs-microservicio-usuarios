package com.magadiflo.usuarios.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.magadiflo.commons.alumnos.models.entity.Alumno;
import com.magadiflo.commons.controllers.CommonController;

import com.magadiflo.usuarios.services.IAlumnoService;

@RestController
public class AlumnoController extends CommonController<Alumno, IAlumnoService> {

	public AlumnoController(IAlumnoService service) {
		super(service);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Alumno alumno) {
		Optional<Alumno> opAlumno = this.service.findById(id);
		if (opAlumno.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Alumno alumnoBD = opAlumno.get();
		alumnoBD.setNombre(alumno.getNombre());
		alumnoBD.setApellido(alumno.getApellido());
		alumnoBD.setEmail(alumno.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(alumnoBD));
	}

}
