package ru.yandex.practicum.filmorate.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Film.
 */
@Data
@Builder
public class Film {
    int id;
    String name;
    String description;
    LocalDate releaseDate;
    int duration;

}
