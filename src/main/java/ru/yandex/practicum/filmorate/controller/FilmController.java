package ru.yandex.practicum.filmorate.controller;


import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.UserService;
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
    //   int id = 1;

    final FilmService filmService;
    final UserService userService;

    public FilmController(FilmService filmService, UserService userService) {
        this.filmService = filmService;
        this.userService = userService;
    }

    //  @GetMapping
    // public List<Film> getFilms() {
    //     return filmService.showFilms();
    // }

    @GetMapping(value = "/{id}")
    public Film getFilm(@PathVariable int id) {
        return filmService.getFilm(id);
    }

    @GetMapping("/popular")
    public List<Film> getFilms(@RequestParam(required = false) Integer count) {
        if (count == null) {
            return filmService.showFilms(10);
        } else {
            return filmService.showFilms(count);
        }
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) throws ValidationException {
        FilmValidator.checkFilm(film);
        //   film.setId(id++);
        //   films.add(film);
        //   return film;
        return filmService.getFilmStorage().createFilm(film);
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

    @PutMapping(value = "/{id}/like/{userId}")
    public void addLikeFilm(@PathVariable Integer id, @PathVariable Integer userId) {
        Film film = filmService.getFilm(id);
        User user = userService.getUserStorage().getUser(userId);
        filmService.addLike(user, film);
    }

    @DeleteMapping(value = "/{id}/like/{userId}")
    public void deleteFilm(@PathVariable int id, @PathVariable int userId) {
        Film film = filmService.getFilm(id);
        User user = userService.getUserStorage().getUser(userId);
        filmService.deleteLike(user, film);
    }


}
