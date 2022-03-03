package com.uniovi.sdi2122203spring.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Professor {

    @Id
    @GeneratedValue
    private Long id;
    private String dni;
    private String name;
    private String surname;
    private String category;

    public Professor(Long id, String dni, String name, String surname, String category) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.category = category;
    }

    public Professor(){

    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + name + '\'' +
                ", apellido='" + surname + '\'' +
                ", categoria='" + category + '\'' +
                '}';
    }
}
