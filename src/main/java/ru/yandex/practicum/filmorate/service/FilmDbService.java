package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.ErrorResponse;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.Collection;

@Service
public class FilmDbService implements FilmServiceInterface {

    @Qualifier("FilmDbStorage")
    private final FilmStorage filmStorage;

    @Autowired
    public FilmDbService(FilmStorage userStorage) {
        this.filmStorage = userStorage;
    }

    @Override
    public boolean addLike(User user, Film film) {
        return false;
    }

    @Override
    public boolean deleteLike(User user, Film film) {
        return false;
    }

    @Override
    public Collection<Film> showFilms(int count) {
        return filmStorage.getFilms();
    }


    @Override
    public Film getFilm(int id) {
        if (filmStorage.getFilm(id) != null) {
            return filmStorage.getFilm(id);
        } else {
            throw new NullPointerException("Фильм c id " + id + " не найден");
        }
    }
}
