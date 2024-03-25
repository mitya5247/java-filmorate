package ru.yandex.practicum.filmorate.controller;


import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.validator.FilmValidator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/films")
public class FilmController {
    ArrayList<Film> films = new ArrayList<>();
    int id = 1;

    @GetMapping
    public List<Film> getFilms() {
        return films;
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) throws ValidationException {
        FilmValidator.checkFilm(film);
        film.setId(id++);
        films.add(film);
        return film;


    }

    @PutMapping
    public Film updateFilm(@NotNull @NotBlank @RequestBody Film film) throws ValidationException {
        FilmValidator.checkFilm(film);
        if (film.getId() == 0) {
            throw new ValidationException("Задан нулевой id");
        } else {
            for (Film film1 : films) {
                if (film1.getId() == film.getId()) {
                    films.remove(film1);
                    films.add(film);
                    return film;
                }

            }
        }
        throw new ValidationException("Фильм с id " + film.getId() + " не добавлен, так как его нет в списке.");
    }


}
