package ru.yandex.practicum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.validator.UserValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    ArrayList<User> users = new ArrayList<>();
    int id = 1;

    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user) throws ValidationException {
        UserValidator.checkUser(user);
        user.setId(id++);
        users.add(user);
        return user;

    }

    @PutMapping
    public User updateUser(@Valid @RequestBody User user) throws ValidationException {
        UserValidator.checkUser(user);
        for (User user1 : users) {
            if (user1.getId() == user.getId()) {
                users.remove(user1);
                users.add(user);
                return user;
            }
        }
        throw new ValidationException("Пользователя с указанным id не существует");

    }


}
