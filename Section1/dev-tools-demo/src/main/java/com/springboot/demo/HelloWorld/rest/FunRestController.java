package com.springboot.demo.HelloWorld.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    // inject the values
    @Value("${coach.name}")
    private String coachName;

    @Value("${team.name}")
    private String teamName;



    @GetMapping("/teaminfo")
    public String teamInfo(){
        return "Team name: %s Coach name: %s".formatted(teamName, coachName);
    }


    // expose "/" that return "Hello world"
    @GetMapping("/")
    public String sayHello(){
        return "Hello TPool";
    }

    @GetMapping("/run")
    public String workOut(){
        return "Run a full marathon";
    }

}
