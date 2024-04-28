package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.List;

public interface FilmServiceInterface {

    public boolean addLike(User user, Film film);

    public boolean deleteLike(User user, Film film);

    public Collection<Film> showFilms(int count);

    public Film getFilm(int id);
}
