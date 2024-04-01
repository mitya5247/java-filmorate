package ru.yandex.practicum.filmorate.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
public class FilmService implements FilmServiceInterface {

    final FilmStorage filmStorage;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    @Override
    public boolean addLike(User user, Film film) {
        if (!user.getFilmIdLiked().contains(film.getId())) {
            user.getFilmIdLiked().add(film.getId());
            film.getLikes().add(user.getId());
            return true;
        } else {
            System.out.println("Лайк от пользователя " + user.getLogin() + " уже поставлен!");
            return false;
        }
    }

    @Override
    public boolean deleteLike(User user, Film film) {
        if (!user.getFilmIdLiked().contains(film.getId())) {
            user.getFilmIdLiked().remove(film.getId());
            film.getLikes().remove(user.getId());
            return true;
        } else {
            System.out.println("Лайк от пользователя " + user.getLogin() + " не удален!");
            return false;
        }
    }

    @Override
    public List<Film> showFilms(int count) {
        List<Film> list = new ArrayList<>();
        list = filmStorage.getFilmHashMap().values().stream()
                .sorted((film, t1) -> t1.getLikes().size() - film.getLikes().size())
                .limit(count)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public Film getFilm(int id) {
        return filmStorage.getFilm(id);
    }
}
