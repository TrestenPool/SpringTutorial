package springboot.demo.Dependency_injection_demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    private Coach mycoach;

    @Autowired
    public Controller(@Qualifier("swimCoach") Coach mycoach) {
        this.mycoach = mycoach;
    }

    @GetMapping("/")
    public String test(){
        return "Home route";
    }

    @GetMapping("/getdailyworkout")
    public String getdailyWorkout(){
        return this.mycoach.getDailyWorkout();
    }

}
