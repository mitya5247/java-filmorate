package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmDbService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/films")
public class FilmDbController {
    private final FilmDbService filmDbService;

    public FilmDbController(FilmDbService filmDbService) {
        this.filmDbService = filmDbService;
    }

    @GetMapping
    public Collection<Film> getFilms() {
        return filmDbService.showFilms(0);
    }

    @GetMapping("/popular")
    public Collection<Film> getFilms(@RequestParam(required = false) Integer count) {
        if (count == null) {
            return filmDbService.showFilms(10);
        } else {
            return filmDbService.showFilms(count);
        }
    }

    @GetMapping(value = "/{id}")
    public Film getFilm(@PathVariable int id) {
        return filmDbService.getFilm(id);
    }
}
