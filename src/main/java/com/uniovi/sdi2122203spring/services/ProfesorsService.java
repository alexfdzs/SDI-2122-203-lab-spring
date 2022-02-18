package com.uniovi.sdi2122203spring.services;

import com.uniovi.sdi2122203spring.entities.Profesor;
import com.uniovi.sdi2122203spring.repositories.ProfesorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ProfesorsService {

    @Autowired
    private ProfesorsRepository profesorsRepository;

    @PostConstruct
    public void init(){
        profesorsRepository.save(new Profesor(1L, "1", "Alex", "Fernandez", "Sustituto"));
        profesorsRepository.save(new Profesor(2L, "2", "Iyana", "González", "Catedrática"));
    }

    public Profesor getProfesor(Long id){
       return profesorsRepository.findById(id).get();
    }

    public void addProfesor(Profesor p)
    {
        profesorsRepository.save(p);
    }

    public void deleteProfesor(Long id){
        profesorsRepository.deleteById(id);
    }

}
