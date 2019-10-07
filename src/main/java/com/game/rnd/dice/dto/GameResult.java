package com.game.rnd.dice.dto;

import com.game.rnd.dice.type.Result;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class GameResult {

    private Result result;
    private String payload;
}
