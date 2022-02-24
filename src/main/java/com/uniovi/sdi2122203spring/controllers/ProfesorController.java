package com.uniovi.sdi2122203spring.controllers;

import com.uniovi.sdi2122203spring.services.ProfesorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfesorController {

    @Autowired
    private ProfesorsService profesorsService;

    @RequestMapping("/profesor/add")
    public String setProfesor(){
        return "Formulario para añadir profesor";
    }

    @RequestMapping("/profesor/details/{id}")
    public String getDetails(@PathVariable Long id){
        return profesorsService.getProfesor(id).toString();
    }

    @RequestMapping("/profesor/edit/{id}")
    public String getEdit(@PathVariable Long id){
        return "Formulario para editar el " + profesorsService.getProfesor(id).toString();
    }

    @RequestMapping("/profesor/delete/{id}")
    public String deleteProfesor(@PathVariable Long id){
        profesorsService.deleteProfesor(id);
        return "Eliminado el profesor con id -> " + id;
    }
}