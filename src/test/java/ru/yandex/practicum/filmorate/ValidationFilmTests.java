package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validator.FilmValidator;

import java.time.LocalDate;

public class ValidationFilmTests {

    @Test
    public void createEmptyFilm() {
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
}