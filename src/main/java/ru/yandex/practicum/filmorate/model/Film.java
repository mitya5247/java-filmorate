package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.yandex.practicum.filmorate.annotations.NotEarly;

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
    String name;
    @Length(max = 200)
    String description;
    @NotEarly
    LocalDate releaseDate;
    @PositiveOrZero
    int duration;
    List<Long> likes;

}
