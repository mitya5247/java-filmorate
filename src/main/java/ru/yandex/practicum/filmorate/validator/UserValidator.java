package ru.yandex.practicum.filmorate.validator;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

@Slf4j
public class UserValidator {
    public static boolean checkUser(User user) throws ValidationException {
        if (!validEmail(user.getEmail()) || !validLogin(user.getLogin()) || !validDateBirthday(user.getBirthday())) {
            throw new ValidationException("Ошибка валидации пользователя. " +
                    "Проверьте валидность полей.");
        }
        return true;
    }

    private static boolean validEmail(String email) {
        return email.contains("@") && !email.isBlank();
    }
    private static boolean validLogin(String login) {
        return !login.isBlank() && !login.contains(" ");
    }
    private static boolean validDateBirthday(LocalDate date) {
        return date.isBefore(LocalDate.now());
    }
}
