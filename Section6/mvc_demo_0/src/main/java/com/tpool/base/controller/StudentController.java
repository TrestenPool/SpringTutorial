package com.tpool.base.controller;

import com.tpool.base.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentController {
    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${operatingSystems}")
    private List<String> operatingSystems;

    @GetMapping("/studentForm")
    public String studentForm(Model model){
        // create student object
        Student student = new Student();

        // set the properties student ojbect
        student.setFirstName("Tresten");
        student.setLastName("Pool");
        student.setFavoriteLanguage("Zig");
        student.setFavoriteOperatingSystems(new ArrayList<>(List.of("Linux")));

        // add the student to the attributes
        model.addAttribute("student", student);

        // add the countries to the model
        model.addAttribute("countries", countries);

        // add the languages to the model
        model.addAttribute("languages", languages);

        // add the operating systems
        model.addAttribute("operatingSystems", operatingSystems);

        // render the form
        return "Student/student-form";
    }

    @PostMapping("/processStudentForm")
    public String processStudentForm(@ModelAttribute("student") Student student){
        // log the output
        System.out.printf("Student\nfirstName: %s\nlastName: %s\n", student.getFirstName(), student.getLastName());
        System.out.printf("country = %s\n", student.getCountry());
        System.out.printf("os = %s\n", student.getFavoriteOperatingSystems());

        // return the view
        return "Student/student-results";
    }
}
