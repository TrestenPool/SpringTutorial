package com.tpool.base.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloWorldController {

    // show the form
    @GetMapping(value = "/show")
    public String showForm(){
        return "helloworld-form";
    }

    // process the form
    @PostMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    // shot the form data
    @PostMapping("/processFormTwo")
    public String processFormTwo(HttpServletRequest request, Model model){
        // get thename from the  query parameters
        String name = request.getParameter("studentName");

        // convert name to upper case
        name = name.toUpperCase();

        // construct the message
        String message = String.format("HELLO %s, IT IS VERY NICE TO MEET YOU!!!", name);

        // add the attribute to the model
        model.addAttribute("message", message);

        // return the form
        return "processForm-2";
    }

    @PostMapping("/processFormThree")
    public String processFormThree(@RequestParam("studentName") String name, Model model){
        name = name.toUpperCase();
        String message = String.format("HELLO %s, IT IS NICE TO FINALLY MEET YOU", name);
        model.addAttribute("message", message);
        return "processForm-2";
    }

}
