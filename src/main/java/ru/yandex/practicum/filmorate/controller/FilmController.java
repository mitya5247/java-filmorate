package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validator.FilmValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/films")
public class FilmController {
    ArrayList<Film> films = new ArrayList<>();

    @GetMapping
    public List<Film> getFilms() {
        return films;
    }

    @PostMapping(value = "/film")
    public Film createFilm(@RequestBody Film film) throws ValidationException {
        if (FilmValidator.checkFilm(film)) {
            films.add(film);
            return film;
        } else {
            return null;
        }

    }

    @PutMapping(value = "/film")
    public Film updateFilm(@RequestBody Film film) throws ValidationException {
        if (FilmValidator.checkFilm(film)) {


            if (!films.contains(film)) {
                films.add(film);
            } else {
                for (Film film1 : films) {
                    if (film1.getId() == film.getId()) {
                        films.remove(film1);
                        break;
                    }
                    films.add(film);
                }
            }
            return film;
        } else {
            return null;
        }

    }


}
