package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.yandex.practicum.filmorate.model.userEnum.FriendshipStatus;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class User {
    @PositiveOrZero
    long id;
    @Email
    @NotBlank
    final String email;
    @NotBlank
    final String login;
    String name;
    @PastOrPresent
    final LocalDate birthday;
    Set<Long> filmIdLiked;
    List<Long> friends;
    FriendshipStatus status = FriendshipStatus.REJECTED;


}
