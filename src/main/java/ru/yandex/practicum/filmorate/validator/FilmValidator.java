package ru.yandex.practicum.filmorate.validator;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

@Slf4j
public class FilmValidator {

    private final static LocalDate maxDateRelease = LocalDate.of(1895, 12, 28);
    private final static int descriptionLength = 200;
    public static boolean checkFilm(Film film) throws ValidationException {
        if (film.getName().isBlank() || film.getDescription().length() > descriptionLength ||
                film.getReleaseDate().isBefore(maxDateRelease) || film.getDuration() < 0) {
            throw new ValidationException("Проверьте правильность введенных полей фильма");
        }
        return true;
    }
}
