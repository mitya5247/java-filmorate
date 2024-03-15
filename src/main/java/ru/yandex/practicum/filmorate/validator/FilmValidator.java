package ru.yandex.practicum.filmorate.validator;

import lombok.extern.slf4j.Slf4j;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;

@Slf4j
public class FilmValidator {

    private static final LocalDate maxDateRelease = LocalDate.of(1895, 12, 28);
    private static final int descriptionLength = 200;

    public static boolean checkFilm(Film film) throws ValidationException {
        try {
        //    if (film.getName().isBlank() || film.getDescription().length() > descriptionLength ||
        //            film.getReleaseDate().isBefore(maxDateRelease) || film.getDuration() < 0) {
        //        return false;
        //    }
            if (film.getName().isBlank()) {
                throw new ValidationException("Поле name не должно быть пустым");
            }
            if (film.getDescription().length() > descriptionLength) {
                throw new ValidationException("Количество символов поля description не должно быть больше 200, " +
                        "длина составляет " + film.getDescription().length());
            }
            if (film.getReleaseDate().isBefore(maxDateRelease)) {
                throw new ValidationException("Дата релиза не должна быть раньше " + maxDateRelease + " . Указана " +
                        "дата " + film.getReleaseDate());
            }
            if (film.getDuration() < 0) {
                throw new ValidationException("Продолжительность фильма не может быть отрицательной");
            }
            return true;
        } catch (NullPointerException e) {
            throw new ValidationException("Поля не должны быть пустыми " + e.getMessage());
        }


    }
}
