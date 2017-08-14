package cs544.edu.reservations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class ReservationDatesCustomValidator implements ConstraintValidator {
    @Override
    public void initialize(Annotation constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return false;
    }
}
