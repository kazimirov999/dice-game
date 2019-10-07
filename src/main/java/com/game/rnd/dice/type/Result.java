package com.game.rnd.dice.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Result {

    WIN, LOSE;

    @JsonValue
    public String asString() {
        return this.name().toLowerCase();
    }
}
