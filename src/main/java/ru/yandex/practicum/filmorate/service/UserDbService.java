package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

@Service
public class UserDbService implements UserServiceInterface {

    @Qualifier("UserDbStorage")
    private final UserServiceInterface userServiceInterface;

    @Autowired
    public UserDbService(UserServiceInterface userServiceInterface) {
        this.userServiceInterface = userServiceInterface;
    }

    @Override
    public List<User> addFriend(User friend1, User friend2) {
        return List.of();
    }

    @Override
    public boolean removeFriend(User friend1, User friend2) {
        return false;
    }

    @Override
    public List<Long> getListCommonFriends(User user1, User user2) {
        return List.of();
    }

    @Override
    public List<Long> getListFriends(User user) {
        return List.of();
    }
}
