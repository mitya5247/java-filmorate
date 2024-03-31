package ru.yandex.practicum.filmorate.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
        validatedBy = {NotEarlyValidator.class}
)
public @interface NotEarly {

    static final String date = "1895 - 12 - 28";

    String message() default "Не может быть раньше даты " + date;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
