package com.magadiflo.usuarios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservicio-cursos")
public interface ICursoFeignClient {

	@DeleteMapping(path = "/eliminar-alumno/{id}")
	void eliminarCursoAlumnoPorId(@PathVariable Long id);

}
