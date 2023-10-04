package com.tpool.restproject.rest;

import com.tpool.restproject.entity.Student;
import com.tpool.restproject.exceptions.StudentErrorReponse;
import com.tpool.restproject.exceptions.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    private List<Student> students;

    @PostConstruct
    public void loadData(){
        students = new ArrayList<>(List.of(
                new Student("Tresten", "Pool"),
                new Student("Josh", "Peck"),
                new Student("Drake", "Nichols")
        ));
    }

    @GetMapping("/students")
    public List<Student> hi(){
        return students;
    }

    @GetMapping("/student/{studentId}")
    public Student getStudent(@PathVariable int studentId){
        if(studentId < 0 || studentId >= students.size()){
            throw new StudentNotFoundException("Student with id: " +studentId + " was not found");
        }
        return students.get(studentId);
    }



}

