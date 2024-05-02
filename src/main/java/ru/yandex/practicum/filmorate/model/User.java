package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
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
    Date birthday;
    Set<Long> filmIdLiked;
    List<Long> friends;


}
