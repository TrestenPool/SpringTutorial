package springboot.demo.Dependency_injection_demo.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportsConfig {
  @Bean
  public Coach swimCoach(){ // bean id will default to the method name
      return new SwimCoach();
  }
}