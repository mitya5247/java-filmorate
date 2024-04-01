package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserServiceInterface {

    public List<User> addFriend(User friend1, User friend2);


    public boolean removeFriend(User friend1, User friend2);

    public List<Long> getListCommonFriends(User user1, User user2);

    public List<Long> getListFriends(User user);
}
