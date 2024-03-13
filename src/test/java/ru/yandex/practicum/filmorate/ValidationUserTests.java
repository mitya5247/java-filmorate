package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validator.UserValidator;

import java.time.LocalDate;

public class ValidationUserTests {
    @Test
    public void createValidUser() throws ValidationException {
        User user = User.builder()
                .email("user@yandex.ru")
                .id(1)
                .name("name")
                .birthday(LocalDate.of(1995, 3, 24))
                .login("login")
                .build();
        Assertions.assertTrue(UserValidator.checkUser(user));
    }

    @Test
    public void createBlankEmail() throws ValidationException {
        User user = User.builder()
                .email("")
                .id(1)
                .name("name")
                .birthday(LocalDate.of(1995, 3, 24))
                .login("login")
                .build();
        Assertions.assertFalse(UserValidator.checkUser(user));
    }

    @Test
    public void emailShouldContainDogSign() throws ValidationException {
        User user = User.builder()
                .email("user-yandex.ru")
                .id(1)
                .name("name")
                .birthday(LocalDate.of(1995, 3, 24))
                .login("login")
                .build();
        Assertions.assertFalse(UserValidator.checkUser(user));
    }

    @Test
    public void createBlankLogin() throws ValidationException {
        User user = User.builder()
                .email("user@yandex.ru")
                .id(1)
                .name("name")
                .birthday(LocalDate.of(1995, 3, 24))
                .login("")
                .build();
        Assertions.assertFalse(UserValidator.checkUser(user));
    }

    @Test
    public void createLoginWithBlanks() throws ValidationException {
        User user = User.builder()
                .email("user@yandex.ru")
                .id(1)
                .name("name")
                .birthday(LocalDate.of(1995, 3, 24))
                .login("loginWithBlank ")
                .build();
        Assertions.assertFalse(UserValidator.checkUser(user));
    }

    @Test
    public void createBlankName() throws ValidationException {
        User user = User.builder()
                .email("user@yandex.ru")
                .id(1)
                .name("")
                .birthday(LocalDate.of(1995, 3, 24))
                .login("loginWithoutBlank")
                .build();
        Assertions.assertTrue(UserValidator.checkUser(user));
    }

    @Test
    public void createFutureBirthday() throws ValidationException {
        User user = User.builder()
                .email("user@yandex.ru")
                .id(1)
                .name("name")
                .birthday(LocalDate.of(2095, 3, 24))
                .login("loginWithoutBlank")
                .build();
        Assertions.assertFalse(UserValidator.checkUser(user));
    }

}
