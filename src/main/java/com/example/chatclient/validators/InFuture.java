package com.example.chatclient.validators;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target( {FIELD, METHOD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InFutureValidator.class)
@Documented
public @interface InFuture {
  String message() default "Expiration date is not in the future";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}