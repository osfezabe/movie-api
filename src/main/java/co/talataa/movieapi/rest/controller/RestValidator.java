package co.talataa.movieapi.rest.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class RestValidator {
    private static final int DEFAULT_PAGE = 1;

    public static int validPageNumberParam(Integer pageNumber) {
        return Optional.ofNullable(pageNumber).map(number -> Math.max(number, DEFAULT_PAGE)).orElse(DEFAULT_PAGE);
    }
}
