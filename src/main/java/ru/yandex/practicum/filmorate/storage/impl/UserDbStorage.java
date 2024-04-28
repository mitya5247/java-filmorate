package ru.yandex.practicum.filmorate.storage.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.List;

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
        String sql = "insert into users (user_id, email, login, name, birthday) values(?,?,?,?,?)";
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
        String sql = "update users set email = ?, login = ?, name = ?, birthday = ? where user_id = ?";
        jdbcTemplate.update(sql, user.getId(), user.getEmail(), user.getLogin(), user.getName(), user.getBirthday());
        return user;
    }

    public List<Long> getListFriends(User user) {
        List<Long> friendsId = new ArrayList<>();
        String sql = "select friend_id from user_friends where user_id = ?";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, user.getId());
        while (sqlRowSet.next()) {
            Long id = sqlRowSet.getLong("friend_id");
            friendsId.add(id);
        }
        return friendsId;
    }

    public List<Long> getListCommonFriends(User user1, User user2) { // доработать
        List<Long> commonFriendsId = new ArrayList<>();
        String sql = "select friend_id from user_friends where user_id = ?";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(sql, user1.getId());
        while (sqlRowSet.next()) {
            Long id = sqlRowSet.getLong("friend_id");
            commonFriendsId.add(id);
        }
        return commonFriendsId;
    }

    @Override
    public List<User> addFriend(User user1, User user2) { // доработать
        List<User> users = new ArrayList<>();
        String sql = "insert into user_friends (user_id, friend_id) values(?,?)";
        jdbcTemplate.update(sql, user1.getId(), user2.getId());
        String queryForList = "select friend_id from user_friends where user_id = ?";
        return users;
    }
}
