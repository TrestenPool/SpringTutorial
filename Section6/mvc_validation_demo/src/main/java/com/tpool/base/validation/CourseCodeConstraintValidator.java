package com.tpool.base.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// <Annotation class, variable that we will use in the isValid method()
public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {
    // assign this when the annotation is initialized
    private String coursePrefix;

    @Override
    public void initialize(CourseCode courseCode) {
        coursePrefix = courseCode.value();
    }

    @Override
    public boolean isValid(String codeToCheck, ConstraintValidatorContext constraintValidatorContext) {
        // return true if no value was supplied
        if(codeToCheck == null){
            return true;
        }

        // return true if the code supplied starts with the course prefix, false otherwise
        return codeToCheck.startsWith(coursePrefix);
    }

}
