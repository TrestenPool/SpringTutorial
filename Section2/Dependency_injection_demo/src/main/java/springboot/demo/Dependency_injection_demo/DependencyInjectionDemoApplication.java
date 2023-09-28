package springboot.demo.Dependency_injection_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages= {"springboot"}
)
public class DependencyInjectionDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DependencyInjectionDemoApplication.class, args);
	}

}
