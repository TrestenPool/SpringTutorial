package com.springboot.demo.HelloWorld.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
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
