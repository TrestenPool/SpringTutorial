package springboot.demo.Dependency_injection_demo.rest;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{

    public BaseballCoach() {
        System.out.println("IN CONSTRUCTOR: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Do 10 poles and hit 5 homeruns";
    }


}
