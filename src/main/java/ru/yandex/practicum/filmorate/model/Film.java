package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * Film.
 */
@Data
@Builder
public class Film {
    @PositiveOrZero
    int id;
    @NotBlank
    final String name;
    @Length(max = 200)
    final String description;
    final LocalDate releaseDate;
    @PositiveOrZero
    final int duration;

}
