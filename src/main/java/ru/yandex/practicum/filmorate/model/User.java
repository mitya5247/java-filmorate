package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static ru.yandex.practicum.filmorate.model.Constants.REJECTEDFRIENDSHIP;

@Data
@Builder
@AllArgsConstructor
public class User {
    @PositiveOrZero
    long id;
    @Email
    @NotBlank
    String email;
    @NotBlank
    String login;
    String name;
    @PastOrPresent
    LocalDate birthday;
    Set<Long> filmIdLiked;
    List<Long> friends;


}
