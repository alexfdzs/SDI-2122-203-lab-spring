package com.uniovi.sdi2122203spring.services;

import org.springframework.stereotype.Service;

@Service
public class RolesService {
    String[] roles = {"ROLE_STUDENT", "ROLE_PROFESSOR", "ROLE_ADMIN"};

    public String[] getRoles(){
        return roles;
    }

}
