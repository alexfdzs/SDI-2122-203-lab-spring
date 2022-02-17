package com.uniovi.sdi2122203spring.services;

import com.uniovi.sdi2122203spring.entities.Profesor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfesorService {
    List<Profesor> profesorList = new LinkedList<>();

    @PostConstruct
    public void init(){
        profesorList.add(new Profesor(1L,"1", "Alex", "Fernandez", "Sustituto"));
        profesorList.add(new Profesor(2L, "2", "Manuel", "González", "Interino"));
    }

    public List<Profesor> getProfesors(){
        return profesorList;
    }

    public Profesor getProfesor(Long id){
        return profesorList.stream()
                .filter(profesor -> profesor.getId().equals(id)).findFirst().get();
    }

    public void addProfesor(Profesor p)
    {
        if (p.getDni()==null)
            p.setDni(profesorList.get(profesorList.size() - 1).getDni() + 1);
        profesorList.add(p);
    }

    public void deleteProfesor(Long id){
        profesorList.removeIf(prof -> prof.getId() == id);
    }

}
