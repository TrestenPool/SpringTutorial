package com.tpool.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/showMyLoginPage")
    public String loginPage(){
        return "security/plain-login";
    }

    @GetMapping("/leaders")
    public String leadersRoute(){
        return "leaders-home";
    }

    @GetMapping("/systems")
    public String systemsRoute(){
        return "systems-home";
    }

    @GetMapping("/access-denied")
    public String access_denied(){
        return "security/access-denied";
    }
}
