package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.Map;

public interface FilmStorage {
    public Film createFilm(Film film) throws ValidationException;

    public void deleteFilm(long id);

    public Film updateFilm(Film film);

    public Film getFilm(long id);

    public Map<Long, Film> getFilmHashMap();
}
