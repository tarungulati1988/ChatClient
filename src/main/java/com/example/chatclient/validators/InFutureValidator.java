package com.example.chatclient.validators;

import com.example.chatclient.exception.type.ApplicationException;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InFutureValidator implements ConstraintValidator<InFuture, Date> {
  public final void initialize(final InFuture annotation) {
  }

  public final boolean isValid(final Date value, final ConstraintValidatorContext context) {
    if (value.after(new Date())) {
      return true;
    } else {
//      throw new RuntimeException("Expiration date is not in the future");
      throw new ApplicationException("Expiration date is not in the future.");
    }
  }
}
