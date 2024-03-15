package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validator.UserValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

public class ValidationUserTests {
    private static Validator validator;

    @BeforeEach
    public void createValidatorCheck() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }

    }

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
        Assertions.assertThrows(ValidationException.class, () -> UserValidator.checkUser(user));
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
        Assertions.assertThrows(ValidationException.class, () -> UserValidator.checkUser(user));
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
        Assertions.assertThrows(ValidationException.class, () -> UserValidator.checkUser(user));
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
        Assertions.assertThrows(ValidationException.class, () -> UserValidator.checkUser(user));
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
        Assertions.assertThrows(ValidationException.class, () -> UserValidator.checkUser(user));
    }

    @Test
    public void checkAnnotationValidationWithoutViolations() {
        User user = User.builder()
                .email("user@yandex.ru")
                .id(1)
                .name("name")
                .birthday(LocalDate.of(1995, 3, 24))
                .login("loginWithoutBlank")
                .build();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void checkAnnotationValidationWithViolationEmail() {
        User user = User.builder()
                .email("useryandex.ru")
                .id(1)
                .name("name")
                .birthday(LocalDate.of(1995, 3, 24))
                .login("loginWithoutBlank")
                .build();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void checkAnnotationValidationWithViolationBirthday() {
        User user = User.builder()
                .email("user@yandex.ru")
                .id(1)
                .name("name")
                .birthday(LocalDate.of(2095, 3, 24))
                .login("loginWithoutBlank")
                .build();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void checkAnnotationValidationWithViolationLogin() {
        User user = User.builder()
                .email("user@yandex.ru")
                .id(1)
                .name("name")
                .birthday(LocalDate.of(1995, 3, 24))
                .login("")
                .build();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Assertions.assertFalse(violations.isEmpty());
    }

}
