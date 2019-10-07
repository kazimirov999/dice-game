package com.game.rnd.dice.util;

import com.game.rnd.dice.exception.TypeNotFoundException;
import lombok.NoArgsConstructor;

import java.util.stream.Stream;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class TypeUtil {

    public static <T extends Enum<T>> T fromString(String value, Class<T> enumType) {
        return Stream.of(enumType.getEnumConstants())
                .filter(v -> v.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new TypeNotFoundException(enumType, value));
    }
}
