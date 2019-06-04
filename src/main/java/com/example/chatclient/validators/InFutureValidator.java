package com.example.chatclient.validators;

import com.example.chatclient.exception.type.ApplicationException;
import java.util.Calendar;
import java.util.Date;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class InFutureValidator implements ConstraintValidator<InFuture, Integer> {

  public final void initialize(final InFuture annotation) {
  }

  /**
   * Given a timeout, calculate the current date + timeout and validate if the date is in not past.
   * @param timeout - timeout in seconds
   * @param context - validator context for the incoming request
   * @return - true if in the future, false if not
   */
  public final boolean isValid(final Integer timeout, final ConstraintValidatorContext context) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Date());
    cal.add(Calendar.SECOND, timeout);
    if (cal.getTime().after(new Date())) {
      return true;
    } else {
      throw new ApplicationException("Expiration date is not in the future.");
    }
  }
}
