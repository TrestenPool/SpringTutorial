package com.tpool.base.model;

import com.tpool.base.validation.CourseCode;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Range;

public class Customer {
    // fields
    @NotNull(message = "first name is required")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Range(min = 0, max = 10, message = "Must be between 0 and 10")
    @NotNull(message = "free passes is required")
    private Integer freePasses;

    @Pattern(regexp = "^[0-9]{5}", message = "Postal Code must be exactly 5 digits")
    private String postalCode;

    @CourseCode(value = "UTSA", message = "must start with UTSA")
    private String courseCode;


    // constructor
    public Customer() {
    }

    // getters and setters

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
