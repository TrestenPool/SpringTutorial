package springboot.demo.Dependency_injection_demo.rest;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach{

    public FootballCoach() {
        System.out.println("IN CONSTRUCTOR: " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "10 up-downs, and hit  25 people";
    }
}
