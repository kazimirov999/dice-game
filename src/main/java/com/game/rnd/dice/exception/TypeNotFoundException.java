package com.game.rnd.dice.exception;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TypeNotFoundException extends RuntimeException {

    public <T extends Enum<T>> TypeNotFoundException(Class<T> enumType, String value) {
        super(String.format("'%s' is an invalid value for %s type. Possible values: %s",
                value, enumType.getSimpleName(), getPossibleValues(enumType)));
    }

    private static <T extends Enum<T>> String getPossibleValues(Class<T> enumType) {
        return Stream.of(enumType.getEnumConstants())
                .map(t -> t.name().toLowerCase())
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
