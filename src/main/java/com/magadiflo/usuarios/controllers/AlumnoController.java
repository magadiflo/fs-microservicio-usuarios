package com.magadiflo.usuarios.controllers;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.magadiflo.usuarios.models.entity.Alumno;
import com.magadiflo.usuarios.services.IAlumnoService;

@RestController
public class AlumnoController {

	private final IAlumnoService alumnoService;

	public AlumnoController(IAlumnoService alumnoService) {
		this.alumnoService = alumnoService;
	}

	@GetMapping
	public ResponseEntity<?> listarAlumnos() {
		return ResponseEntity.ok(this.alumnoService.findAll());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<?> verAlumno(@PathVariable Long id) {
		Optional<Alumno> opAlumno = this.alumnoService.findById(id);
		if (opAlumno.isEmpty()) {
			// build(), construye la respuesta con un body vacío, sin contenido
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(opAlumno.get());
	}

}
