package com.game.rnd.dice.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.game.rnd.dice.util.TypeUtil;

public enum Status {

    ACTIVE, INACTIVE;

    @JsonCreator
    public static Status fromString(String value) {
        return TypeUtil.fromString(value, Status.class);
    }

    @JsonValue
    public String asString() {
        return this.name().toLowerCase();
    }
}
