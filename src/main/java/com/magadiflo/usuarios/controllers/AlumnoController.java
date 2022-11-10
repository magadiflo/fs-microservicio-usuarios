package com.magadiflo.usuarios.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Alumno alumno) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.alumnoService.save(alumno));
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody Alumno alumno) {
		Optional<Alumno> opAlumno = this.alumnoService.findById(id);
		if (opAlumno.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Alumno alumnoBD = opAlumno.get();
		alumnoBD.setNombre(alumno.getNombre());
		alumnoBD.setApellido(alumno.getApellido());
		alumnoBD.setEmail(alumno.getEmail());

		return ResponseEntity.status(HttpStatus.CREATED).body(this.alumnoService.save(alumnoBD));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id) {
		this.alumnoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
