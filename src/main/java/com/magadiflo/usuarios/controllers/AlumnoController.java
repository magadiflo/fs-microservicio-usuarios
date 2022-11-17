package com.magadiflo.usuarios.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	/**
	 * En el CommonsController, el método crear tiene anotado un
	 * 
	 * @throws IOException
	 * @RequestBody porque nos envían un JSON con los datos del Entity, en este caso
	 *              del Alumno y ese sería el tipo de request, el tipo de contenido:
	 *              un JSON.
	 * 
	 *              Peron en este método crearConFoto(...) nos envían parámetros, y
	 *              entre los parámetros está la foto, por lo tanto el @RequestBody
	 *              no va, no se incluye en el método, es decir sería así:
	 *              ....crearConFoto(@Valid Alumno entity....) Lo que sí debemos
	 *              agregar es un MultipartFile con la imagen.
	 * 
	 *              Este método crearConFoto, es para recibir la foto y asignarla al
	 *              alumno y en seguida pasarlo a la clase padre para que haga el
	 *              respectivo guardado, es decir estamos reutilizando el código de
	 *              guardar
	 */

	@PostMapping(path = "/crear-con-foto")
	public ResponseEntity<?> crearConFoto(@Valid Alumno alumno, BindingResult result,
			@RequestParam MultipartFile archivo) throws IOException {
		if (!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		return super.crear(alumno, result);
	}

	// Aquí tampoco va el @RequestBody, cuando estamos recibiendo un archivo
	// (imagen, foto, etc.) el tipo de request no es un JSON,
	// es otro tipo de request, es un Multipart Form Data.
	// De forma automática el Alumno se poblará con los campos: nombre, apellido,
	// email
	// y la imagen se poblará en el MultipartFile archivo
	@PutMapping(path = "/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(@PathVariable Long id, @Valid Alumno alumno, BindingResult result,
			@RequestParam MultipartFile archivo) throws IOException {

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

		if (!archivo.isEmpty()) {
			alumnoBD.setFoto(archivo.getBytes());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(alumnoBD));
	}

	@GetMapping(path = "/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id) {
		Optional<Alumno> opAlumno = this.service.findById(id);
		if (opAlumno.isEmpty() || opAlumno.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}

		Resource imagen = new ByteArrayResource(opAlumno.get().getFoto());

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
	}

	/**
	 * En el parámetro es mejor usar List, ya que si usamos el Iterable produce
	 * error con Feign, quien será usado desde el microservicio cursos para
	 * comunicarse con este endpoint
	 */
	@GetMapping(path = "/alumnos-por-curso")
	public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids) {
		return ResponseEntity.ok(this.service.findAllById(ids));
	}

}
