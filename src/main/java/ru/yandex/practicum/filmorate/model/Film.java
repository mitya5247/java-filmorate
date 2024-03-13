package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    String name;
    @Max(200)
    String description;
    LocalDate releaseDate;
    @PositiveOrZero
    int duration;

}
