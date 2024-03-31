package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;

public interface UserStorage {

    public User getUser(long id);

    public User createUser(User user);

    public void deleteUser(long id);

    public User updateUser(User user);

    public HashMap<Long, User> getUserHashMap();
}
