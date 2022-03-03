package com.uniovi.sdi2122203spring.services;

import com.uniovi.sdi2122203spring.entities.Mark;
import com.uniovi.sdi2122203spring.entities.User;
import com.uniovi.sdi2122203spring.repositories.MarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
public class MarksService {

    @Autowired
    private MarksRepository marksRepository;

    private final HttpSession httpSession;
    public MarksService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public List<Mark> getMarks(){
        List<Mark> marks = new ArrayList<>();
        marksRepository.findAll().forEach(marks::add);
        return marks;
    }

    public List<Mark> getMarksForUser(User user) {
        List<Mark> marks = new ArrayList<Mark>();
        if (user.getRole().equals("ROLE_STUDENT")) {
            marks = marksRepository.findAllByUser(user);
        }
        if (user.getRole().equals("ROLE_PROFESSOR")) {
            marks = getMarks();
        }
        return marks;
    }

    public Mark getMark(Long id){
        Set<Mark> consultedList = (Set<Mark>) httpSession.getAttribute("consultedList");
        if ( consultedList == null ) {
            consultedList = new HashSet<Mark>();
        }
        Mark obtainedMark = marksRepository.findById(id).get(); consultedList.add(obtainedMark);
        httpSession.setAttribute("consultedList", consultedList); return obtainedMark;
    }

    public void addMark(Mark mark){
        marksRepository.save(mark);
    }

    public void deleteMark(Long id){
        marksRepository.deleteById(id);
    }

    public void setMarkResend(boolean revised, Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String dni = auth.getName();
        Mark mark = marksRepository.findById(id).get();
        if(mark.getUser().getDni().equals(dni)  ) {
            marksRepository.updateResend(revised, id);
        }
    }



}