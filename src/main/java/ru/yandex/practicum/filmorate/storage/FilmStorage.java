package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.List;


public interface FilmStorage {
    public Film createFilm(Film film) throws ValidationException;

    public void deleteFilm(long id);

    public Film updateFilm(Film film);

    public Film getFilm(long id);

    public Collection<Film> getFilms();

    public default boolean addLike(User user, Film film) {
        return false;
    }

    public default boolean deleteLike(User user, Film film) {
        return false;
    }

    public default List<Integer> getGenres(int id) {
        return null;
    }
}
