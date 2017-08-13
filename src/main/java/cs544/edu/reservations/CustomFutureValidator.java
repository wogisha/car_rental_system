package cs544.edu.reservations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

public class CustomFutureValidator implements ConstraintValidator<CustomFuture, Date> {
    private boolean isTodayDate = true;

    @Override
    public void initialize(CustomFuture constraintAnnotation) {
        isTodayDate = constraintAnnotation.todayDate();
    }

    @Override
    public boolean isValid(Date value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date todayDate = calendar.getTime();

        if (isTodayDate) {

            return (value.equals(todayDate) || value.after(todayDate));

        }

        return value.after(todayDate);
    }
}
