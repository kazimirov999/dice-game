package com.game.rnd.dice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PlayerAlreadyExists extends RuntimeException {

    public PlayerAlreadyExists(String loginName) {
        super(String.format("Player already exists with id: %s", loginName));
    }
}
