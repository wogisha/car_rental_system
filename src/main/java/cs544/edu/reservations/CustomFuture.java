package cs544.edu.reservations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomFutureValidator.class)
public @interface CustomFuture {

    String message() default "Date should be today or in the future and before Return date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean todayDate() default true;

}
