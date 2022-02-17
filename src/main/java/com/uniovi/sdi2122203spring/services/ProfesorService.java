package com.uniovi.sdi2122203spring.services;

import com.uniovi.sdi2122203spring.entities.Profesor;
import com.uniovi.sdi2122203spring.repositories.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @PostConstruct
    public void init(){
        profesorRepository.save(new Profesor(1L, "1", "Alex", "Fernandez", "Sustituto"));
        profesorRepository.save(new Profesor(2L, "2", "Iyana", "González", "Catedrática"));
    }

    public Profesor getProfesor(Long id){
       return profesorRepository.findById(id).get();
    }

    public void addProfesor(Profesor p)
    {
        profesorRepository.save(p);
    }

    public void deleteProfesor(Long id){
        profesorRepository.deleteById(id);
    }

}
