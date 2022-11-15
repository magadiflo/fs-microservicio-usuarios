package com.magadiflo.usuarios.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<?> editar(@PathVariable Long id, @Valid @RequestBody Alumno alumno, BindingResult result) {

		if (result.hasErrors()) {
			return this.validar(result);
		}

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

	@GetMapping(path = "/filtrar/{termino}")
	public ResponseEntity<?> filtrar(@PathVariable String termino) {
		return ResponseEntity.ok(this.service.encontrarPorNombreOrApellido(termino));
	}

}
