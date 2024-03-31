package ru.yandex.practicum.filmorate.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
@Component
public class FilmController {

    final FilmService filmService;
    final UserService userService;

    @Autowired
    public FilmController(FilmService filmService, UserService userService) {
        this.filmService = filmService;
        this.userService = userService;
    }

      @GetMapping
      public List<Film> getFilms() {
        List<Film> films = new ArrayList<>(filmService.getFilmStorage().getFilmHashMap().values());
          return films;
       }

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
        return filmService.getFilmStorage().createFilm(film);
    }

    @PutMapping
    public Film updateFilm(@NotNull @NotBlank @RequestBody Film film) throws ValidationException {
        FilmValidator.checkFilm(film);
        if (filmService.getFilmStorage().updateFilm(film) != null) {
            return film;
        } else {
            throw new NullPointerException("Фильм c id " + film.getId() +  " не найден");
        }

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
