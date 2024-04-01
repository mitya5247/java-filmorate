package ru.yandex.practicum.filmorate.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class NotEarlyValidator implements ConstraintValidator<NotEarly, LocalDate> {

    LocalDate earlyDate = LocalDate.of(1895, 12, 28);

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext constraintValidatorContext) {
        return date.isAfter(earlyDate);
    }
}
