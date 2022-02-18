package com.uniovi.sdi2122203spring.repositories;

import com.uniovi.sdi2122203spring.entities.Profesor;
import org.springframework.data.repository.CrudRepository;

public interface ProfesorsRepository extends CrudRepository<Profesor, Long> {
}
