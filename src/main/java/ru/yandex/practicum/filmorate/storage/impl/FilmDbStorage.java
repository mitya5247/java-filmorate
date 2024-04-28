package ru.yandex.practicum.filmorate.storage.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.ArrayList;
import java.util.List;


@Component("FilmDbStorage")
@Primary
public class FilmDbStorage implements FilmStorage {

    private final JdbcTemplate jdbcTemplate;


    int idGen = 1;

    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film createFilm(Film film) throws ValidationException {
        film.setId(idGen++);
        String sql = "insert into films (film_id, name, description, release_date, duration, genre, likes) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, film.getId(), film.getName(), film.getDescription(), film.getReleaseDate(),
                film.getLikes());
        return film;
    }

    @Override
    public void deleteFilm(long id) {
        String sql = "delete from films where film_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Film updateFilm(Film film) {
        String sql = "update fimls where film_id = ?";
        jdbcTemplate.update(sql, film.getId());
        return film;
    }

    @Override
    public Film getFilm(long id) {
        String sql = "select * from films where film_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);
        if (rowSet.next()) {
            Film film = Film.builder().build();
            film.setId(rowSet.getInt("film_id"));
            film.setName(rowSet.getString("name"));
            film.setDescription(rowSet.getString("description"));
            film.setReleaseDate(rowSet.getDate("release_date").toLocalDate());
            return film;
        } else {
            return null;
        }
    }

    public List<Film> getFilms() {
        List<Film> films = new ArrayList<>();
        String sql = "select * from films";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            Film film = Film.builder()
                    .id(rowSet.getInt("film_id"))
                    .name(rowSet.getString("name"))
                    .description(rowSet.getString("description"))
                    .releaseDate(rowSet.getDate("release_date").toLocalDate())
                    .build();
            films.add(film);
        }
        return films;
    }

    public List<Film> getFilms(int count) {
        List<Film> films = new ArrayList<>();
        String sql = "select * from films order by release_date desc";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next() && films.size() != count) {
            Film film = Film.builder()
                    .id(rowSet.getInt("film_id"))
                    .name(rowSet.getString("name"))
                    .description(rowSet.getString("description"))
                    .releaseDate(rowSet.getDate("release_date").toLocalDate())
                    .build();
            films.add(film);
        }
        return films;
    }

    @Override
    public boolean addLike(User user, Film film) {
        String sql = "insert into film_liked (films_id, users_id) values (?,?)";
        int edit = jdbcTemplate.update(sql, film.getId(), user.getId());
        return edit == 2;
    }

    @Override
    public boolean deleteLike(User user, Film film) {
        String sql = "delete from film_liked where films_id = ? and users_id = ?";
        int edit = jdbcTemplate.update(sql, film.getId(), user.getId());
        return edit == 2;
    }

    @Override
    public List<Integer> getGenres(int id) { // доработать
        List<Integer> genresId = new ArrayList<>();
        String sql = "select genre_id";
        return null;
    }

}
