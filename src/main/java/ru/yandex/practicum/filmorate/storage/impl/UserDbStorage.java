package ru.yandex.practicum.filmorate.storage.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

@Component("UserDbStorage")
@Primary
public class UserDbStorage implements UserStorage {

    private final JdbcTemplate jdbcTemplate;

    int idGen = 1;

    public UserDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getUser(long id) {
        String sql = "select * from users where user_id = ?";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, id);
        if (sqlRowSet.next()) {
            User user = User.builder().build();
            user.setId(id);
            user.setEmail(sqlRowSet.getString("email"));
            user.setLogin(sqlRowSet.getString("login"));
            user.setName(sqlRowSet.getString("name"));
            user.setBirthday(sqlRowSet.getDate("birthday").toLocalDate());
            return user;
        } else {
        return null;
        }
    }

    @Override
    public User createUser(User user) {
        user.setId(idGen++);
        String sql = "insert into users (user_id, email, login, name, birthday, status) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, user.getId(), user.getEmail(), user.getLogin(), user.getName(), user.getBirthday());
        return user;
    }

    @Override
    public void deleteUser(long id) {
        String sql = "delete from users where user_id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public User updateUser(User user) {
        String sql = "update users where user_id = ?";
        jdbcTemplate.update(sql, user.getId());
        return user;
    }
}
