package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validator.FilmValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

public class ValidationFilmTests {

    private static Validator validator;

    @BeforeEach
    public void createValidatorCheck() {
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
        }

    }

    @Test
    public void createEmptyFilm() throws ValidationException {
        Film film = Film.builder()
                .build();
        Assertions.assertThrows(ValidationException.class, () -> FilmValidator.checkFilm(film));
    }

    @Test
    public void createEmptyName() throws ValidationException {
        Film film = Film.builder()
                .id(1)
                .name("")
                .description("Great empty name film!")
                .build();
        Assertions.assertFalse(FilmValidator.checkFilm(film));

    }

    @Test
    public void createDescriptionLengthMoreThan200() throws ValidationException {
        Film film = Film.builder()
                .id(1)
                .name("The best description I have ever had!")
                .description("Great short description!fdisojgdoikopaslko0shko0ko0kho0ko0kgo0hk0ofko0khogpdslzzfkokfoi" +
                        "sjdkfgoiskdoifgfksdoifkmsdogimksdoifmgodkf,kgohdmiogfkasfieorkfeoewieafkoiwekfoikmkf" +
                        "glfdasfowakfopakwrigjse9fosdptgjiaerjgi9aerjfise9jtsgmiaerjgiawjrk0ig9aekrig9a0erkfiaejgkege" +
                        "erigjiae9rjgi9srkgro0ieajrgiaewkrgiserig9jea09rjgkaei9rjgi9erjg9erjg0aejrg9isrjtgi0awejkg9isertgi")
                .build();
        Assertions.assertFalse(FilmValidator.checkFilm(film));
        Assertions.assertTrue(film.getDescription().length() > 200);
    }

    @Test
    public void createDescriptionLengthLessThan200() throws ValidationException {
        Film film = Film.builder()
                .id(1)
                .name("The best description I have ever had!")
                .description("Great short description!fdisojgdoikopaslko0shko0ko0kho0ko0kgo0hk0ofko0khogpdslzzfkokfoi" +
                        "sjdkfgoiskdoifgfksdoifkmsdogimksdoifmgodkf,kgohdmiogfkasfieorkfeoewieafkoiwekfoikmkf" +
                        "glfdasfowakfopakwr")
                .duration(234)
                .releaseDate(LocalDate.of(1994, 12, 23))
                .build();
        Assertions.assertTrue(FilmValidator.checkFilm(film));
        Assertions.assertFalse(film.getDescription().length() > 200);
    }

    @Test
    public void createDescriptionLength200() throws ValidationException {
        Film film = Film.builder()
                .id(1)
                .name("The best description I have ever had!")
                .description("Great short description!fdisojgdoikopaslko0shko0ko0kho0ko0kgo0hk0ofko0khogpdslzzfkokfoi" +
                        "sjdkfgoiskdoifgfksdoifkmsdogimksdoifmgodkf,kgohdmiogfkasfieorkfeoewieafkoiwekfoikmkf" +
                        "glfdasfowakfopakwrghjkljhgfdg")
                .duration(234)
                .releaseDate(LocalDate.of(1994, 12, 23))
                .build();
        Assertions.assertTrue(FilmValidator.checkFilm(film));
        Assertions.assertEquals(200, film.getDescription().length());
    }

    @Test
    public void createFilmEarlierThanItsPossible() throws ValidationException {
        Film film = Film.builder()
                .id(1)
                .name("The most easrlier film")
                .description("Great description")
                .duration(234)
                .releaseDate(LocalDate.of(1894, 12, 23))
                .build();
        Assertions.assertFalse(FilmValidator.checkFilm(film));

    }

    @Test
    public void createFilmLaterThanItsPossible() throws ValidationException {
        Film film = Film.builder()
                .id(1)
                .name("The most easrlier film")
                .description("Great description")
                .duration(234)
                .releaseDate(LocalDate.of(1895, 12, 29))
                .build();
        Assertions.assertTrue(FilmValidator.checkFilm(film));

    }

    @Test
    public void createFilmPreciselyThanItsPossible() throws ValidationException {
        Film film = Film.builder()
                .id(1)
                .name("The most easrlier film")
                .description("Great description")
                .duration(234)
                .releaseDate(LocalDate.of(1895, 12, 28))
                .build();
        Assertions.assertTrue(FilmValidator.checkFilm(film));

    }

    @Test
    public void createNegativeDurationOfFilm() throws ValidationException {
        Film film = Film.builder()
                .id(1)
                .name("The most easrlier film")
                .description("Great description")
                .duration(-234)
                .releaseDate(LocalDate.of(1994, 12, 23))
                .build();
        Assertions.assertFalse(FilmValidator.checkFilm(film));

    }

    @Test
    public void createZeroDurationOfFilm() throws ValidationException {
        Film film = Film.builder()
                .id(1)
                .name("The most easrlier film")
                .description("Great description")
                .duration(0)
                .releaseDate(LocalDate.of(1994, 12, 23))
                .build();
        Assertions.assertTrue(FilmValidator.checkFilm(film));

    }

    @Test
    public void createPositiveDurationOfFilm() throws ValidationException {
        Film film = Film.builder()
                .id(1)
                .name("The most easrlier film")
                .description("Great description")
                .duration(10)
                .releaseDate(LocalDate.of(1994, 12, 23))
                .build();
        Assertions.assertTrue(FilmValidator.checkFilm(film));

    }

    @Test
    public void checkAnnotationValidationWithoutViolations() {
        Film film = Film.builder()
                .id(1)
                .name("The most easrlier film")
                .description("Grt")
                .duration(10)
                .releaseDate(LocalDate.of(1994, 12, 23))
                .build();
        System.out.println(film.getDescription().length());
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertTrue(violations.isEmpty());
    }

    @Test
    public void checkAnnotationValidationWithViolationDescription() {
        Film film = Film.builder()
                .id(1)
                .name("The most easrlier film")
                .description("Great short description!fdisojgdoikopaslko0shko0ko0kho0ko0kgo0hk0ofko0khogpdslzzfkokfoi\" +\n" +
                        "  \"sjdkfgoiskdoifgfksdoifkmsdogimksdoifmgodkf,kgohdmiogfkasfieorkfeoewieafkoiwekfoikmkf\" +\n" +
                        "glfdasfowakfopakwrghjkljhgfdg")
                .duration(10)
                .releaseDate(LocalDate.of(1994, 12, 23))
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertFalse(violations.isEmpty());
    }

    @Test
    public void checkAnnotationValidationWithViolationDuration() {
        Film film = Film.builder()
                .id(1)
                .name("The most easrlier film")
                .description("Great short description!")
                .duration(-10)
                .releaseDate(LocalDate.of(1994, 12, 23))
                .build();
        Set<ConstraintViolation<Film>> violations = validator.validate(film);
        Assertions.assertFalse(violations.isEmpty());
    }
}
