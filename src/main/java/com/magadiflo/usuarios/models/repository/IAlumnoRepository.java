package com.magadiflo.usuarios.models.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.magadiflo.commons.alumnos.models.entity.Alumno;

public interface IAlumnoRepository extends PagingAndSortingRepository<Alumno, Long> {

	@Query("SELECT a FROM Alumno AS a WHERE UPPER(a.nombre) LIKE UPPER(CONCAT('%', ?1, '%')) OR UPPER(a.apellido) LIKE UPPER(CONCAT('%', ?1, '%'))")
	List<Alumno> encontrarPorNombreOrApellido(String termino);

	Iterable<Alumno> findAllByOrderByIdAsc();

	Page<Alumno> findAllByOrderByIdAsc(Pageable pageable);

}
