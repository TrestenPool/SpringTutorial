package springboot.demo.Dependency_injection_demo.rest;

public class SwimCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "swim 10 laps";
    }
}
