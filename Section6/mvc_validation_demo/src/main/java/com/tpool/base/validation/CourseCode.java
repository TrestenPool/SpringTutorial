package com.tpool.base.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


// tells the annotation what class that is going to validate this annotation
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
// tells the jvm what the annotation can be used for
@Target({ElementType.FIELD, ElementType.METHOD})
// The retention policy determines how long the annotated annotation should be retained or available in the compiled class files and at runtime.
@Retention(RetentionPolicy.RUNTIME)
// it indicates that this annotation should be included in the generated Javadoc and other documentation tools.
@Documented()
public @interface CourseCode {
    // default course code
    String value() default "LUV";

    // default error message
    String message() default "must start with LUV";

    // define default groups
    Class<?>[] groups() default {} ;

    // define default payloads
    // payloads provide custom details about the validation failure(security level, error code, etc..)
    Class<? extends Payload>[] payload() default {};
}
