
package ru.yandex.practicum.filmorate.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FilmServiceInterface;
import ru.yandex.practicum.filmorate.service.UserServiceInterface;
import ru.yandex.practicum.filmorate.validator.FilmValidator;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
@RequestMapping("/films")
@Component
public class FilmController {

    @Qualifier("FilmDbService")
    final FilmServiceInterface filmService;
    @Qualifier("UserDbService")
    final UserServiceInterface userService;

    @Autowired
    public FilmController(FilmServiceInterface filmService, UserServiceInterface userService) {
        this.filmService = filmService;
        this.userService = userService;
    }

    @GetMapping
    public Collection<Film> getFilms() {
        return filmService.showFilmsAll();
    }

    @GetMapping(value = "/{id}")
    public Film getFilm(@PathVariable int id) {
        return filmService.getFilm(id);
    }

    @GetMapping("/popular")
    public Collection<Film> getFilms(@RequestParam(required = false) Integer count) {
        if (count == null) {
            return filmService.showFilms(10);
        } else {
            return filmService.showFilms(count);
        }
    }

    @PostMapping
    public Film createFilm(@Valid @RequestBody Film film) throws ValidationException {
        FilmValidator.checkFilm(film);
        return filmService.createFilm(film);
    }

    @PutMapping
    public Film updateFilm(@NotNull @NotBlank @RequestBody Film film) throws ValidationException {
        FilmValidator.checkFilm(film);
        if (filmService.updateFilm(film) != null) {
            return filmService.updateFilm(film);
        } else {
            throw new NullPointerException("Фильм c id " + film.getId() + " не найден");
        }

    }

    @PutMapping(value = "/{id}/like/{userId}")
    public void addLikeFilm(@PathVariable Integer id, @PathVariable Integer userId) {
        Film film = filmService.getFilm(id);
        User user = userService.getUser(userId);
        filmService.addLike(user, film);
    }

    @DeleteMapping(value = "/{id}/like/{userId}")
    public void deleteFilm(@PathVariable int id, @PathVariable int userId) {
        Film film = filmService.getFilm(id);
        User user = userService.getUser(userId);
        filmService.deleteLike(user, film);
    }





}
