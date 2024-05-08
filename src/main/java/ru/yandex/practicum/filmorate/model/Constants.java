package ru.yandex.practicum.filmorate.model;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class Constants {

    static String ACCEPTEDFRIENDSHIP = "ACCEPTED";
    static String REJECTEDFRIENDSHIP = "REJECTED";


    static String GRATING = "G";
    static String PGRATING = "PG";
    static String PG_13RATING = "PG-13";
    static String RRATING = "R";
    static String NC_17RATING = "NC-17";

    static String COMEDYGENRE = "Комедия";
    static String DRAMAGENRE = "Драма";
    static String CARTOONGENRE = "Мультфильм";
    static String TRILLERGENRE = "Триллер";
    static String DOCUMENTALGENRE = "Документальный";
    static String MILITANTGENRE = "Боевик";


}
