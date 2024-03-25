package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class User {
    @PositiveOrZero
    int id;
    @Email
    @NotBlank
    final String email;
    @NotBlank
    final String login;
    String name;
    @PastOrPresent
    final LocalDate birthday;
    Set<Integer> filmIdLiked;


}
