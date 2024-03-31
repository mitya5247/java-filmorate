package ru.yandex.practicum.filmorate.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.validator.UserValidator;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    final UserService userService;
    final FilmService filmService;

    String nullExceptionComment = "Параметр %s не может быть null";

    @Autowired
    public UserController(UserService userService, FilmService filmService) {
        this.userService = userService;
        this.filmService = filmService;
    }

    @GetMapping
    public List<User> getUsers() {
        return new ArrayList<>(userService.getUserStorage().getUserHashMap().values());
    }

    @GetMapping(value = "/{id}")
    public User getUser(@PathVariable Integer id) {
        return userService.getUserStorage().getUser(id);
    }

    @GetMapping(value = "/{id}/friends")
    public List<User> getUserFriends(@PathVariable Integer id) {
        if (id == null) {
            throw new NullPointerException(String.format(nullExceptionComment, "id"));
        }
        try {
            User user = userService.getUserStorage().getUser(id);
            List<User> friendUsers = new ArrayList<>();
            for (long idFriend : user.getFriends()) {
                friendUsers.add(userService.getUserStorage().getUser(idFriend));
            }
            return friendUsers;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Параметр id должен быть числом");
        }
    }

    @GetMapping(value = "/{id}/friends/common/{otherId}")
    public List<User> getCommonFriends(@PathVariable Long id, @PathVariable Long otherId) {
        if (id == null) {
            throw new NullPointerException(String.format(nullExceptionComment, "id"));
        }
        if (otherId == null) {
            throw new NullPointerException(String.format(nullExceptionComment, "otherId"));
        }
        User user1 = userService.getUserStorage().getUser(id);
        User user2 = userService.getUserStorage().getUser(otherId);
        List<Long> listId = userService.getListCommonFriends(user1, user2);
        List<User> commonFriends = new ArrayList<>();
        for (long idFriend : listId) {
            commonFriends.add(userService.getUserStorage().getUser(idFriend));
        }
        return commonFriends;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) throws ValidationException {
        UserValidator.checkUser(user);
        if (userService.getUserStorage().createUser(user) != null) {
            return userService.getUserStorage().getUser(user.getId());
        } else {
            throw new ValidationException("Пользователь" + user.getLogin() + " уже существует");
        }
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) throws ValidationException {
        UserValidator.checkUser(user);
        if (userService.getUserStorage().updateUser(user) != null) {
            return userService.getUserStorage().updateUser(user);
        } else {
            throw new NullPointerException("Пользователя" + user.getLogin() + " нет в списке");
        }
    }

    @PutMapping(value = "/{id}/friends/{friendId}")
    public List<User> addFriend(@PathVariable Long id, @PathVariable Long friendId) {
        if (id == null) {
            throw new NullPointerException(String.format(nullExceptionComment, "id"));
        }
        if (friendId == null) {
            throw new NullPointerException(String.format(nullExceptionComment, "friendId"));
        }
        try {
            User user = userService.getUserStorage().getUser(id);
            User friend = userService.getUserStorage().getUser(friendId);
            return userService.addFriend(user, friend);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(" Проверьте, что параметры id и friendId - числа");
        }

    }


    @DeleteMapping(value = "/{id}/friends/{friendId}")
    public boolean deleteFriend(@PathVariable Integer id, @PathVariable Integer friendId) {
        if (id == null) {
            throw new NullPointerException(String.format(nullExceptionComment, "id"));
        }
        if (friendId == null) {
            throw new NullPointerException(String.format(nullExceptionComment, "friendId"));
        }
        try {
            User user = userService.getUserStorage().getUser(id);
            User friend = userService.getUserStorage().getUser(friendId);
            return userService.removeFriend(user, friend);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Проверьте параметры id и friendId - числа");
        }
    }


}
