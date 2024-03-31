package ru.yandex.practicum.filmorate.exceptions;

public class ErrorResponse {

    String description;

    public ErrorResponse(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
