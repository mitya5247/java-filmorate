package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.yandex.practicum.filmorate.annotations.NotEarly;
import ru.yandex.practicum.filmorate.model.filmEnum.Genre;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Film.
 */
@Data
@Builder
public class Film {
    @PositiveOrZero
    long id;
    @NotBlank
    final String name;
    @Length(max = 200)
    final String description;
    @NotEarly
    final LocalDate releaseDate;
    @PositiveOrZero
    final int duration;
    List<Long> likes;
    Genre genre;

}
