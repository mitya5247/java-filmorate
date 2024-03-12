package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validator.UserValidator;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    ArrayList<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    @PostMapping(value = "/user")
    public User createUser(@RequestBody User user) throws ValidationException {
        UserValidator.checkUser(user);
        users.add(user);
        return user;
    }

    @PutMapping(value = "/user")
    public void updateUser(@RequestBody User user) throws ValidationException {
        UserValidator.checkUser(user);
        for (User user1 : users) {
            if (user1.getId() == user.getId()) {
                users.remove(user1);
                break;
            }
        }
        users.add(user);
    }


}
