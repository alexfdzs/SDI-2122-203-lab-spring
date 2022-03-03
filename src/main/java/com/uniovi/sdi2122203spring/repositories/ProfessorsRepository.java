package com.uniovi.sdi2122203spring.repositories;

import com.uniovi.sdi2122203spring.entities.Professor;
import org.springframework.data.repository.CrudRepository;

public interface ProfessorsRepository extends CrudRepository<Professor, Long>{

    Professor findByDni(String dni);

}
