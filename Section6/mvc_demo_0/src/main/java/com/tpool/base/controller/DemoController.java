package com.tpool.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// has to be @Controller and not @RestController !!!
@Controller
public class DemoController {

    @RequestMapping("/hello")
    public String hello(Model model){

        // add an attribute that can be referenced in the view
        model.addAttribute(
                "theDate",
                new java.util.Date()
        );

        // looks for src/main/resources/templates/helloworld.html
        return "helloworld";
    }
}
