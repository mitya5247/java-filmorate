package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class User {
    @PositiveOrZero
    int id;
    @Email
    @NotBlank
    String email;
    @NotBlank
    String login;
    String name;
    @PastOrPresent
    LocalDate birthday;



}
