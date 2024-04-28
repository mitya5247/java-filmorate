package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.List;

public interface UserStorage {

    public User getUser(long id);

    public User createUser(User user);

    public void deleteUser(long id);

    public User updateUser(User user);

    public default HashMap<Long, User> getUserHashMap() {
        return null;
    }

    public default List<User> addFriend(User user1, User user2) {
        return null;
    }

    public default boolean removeFriend(User user) {
        return false;
    }
}
