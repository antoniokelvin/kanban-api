package com.pepkor.kanban.validator;

import com.pepkor.kanban.model.Status;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WorkItemValidator.class)
public @interface MaxInProgress {

    String message() default "Maximum in-progress tasks reached";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int maxValue() default 5;
}


