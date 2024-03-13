package ru.yandex.practicum.filmorate.validator;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

@Slf4j
public class UserValidator {
    public static boolean checkUser(User user) throws ValidationException {
        try {
            makeNameuserNotEmpty(user);
            if (!validEmail(user.getEmail()) || !validLogin(user.getLogin()) || !validDateBirthday(user.getBirthday())) {
                return false;
            }
            return true;
        } catch (NullPointerException e) {
            throw new ValidationException("Поля не должны быть пустыми");
        }


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
    private static void makeNameuserNotEmpty(User user) {
        if (user.getName() == null) {
            user.setName(user.getLogin());
        } else {
            return;
        }
    }
}
