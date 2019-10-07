package com.game.rnd.dice.entity;


import com.game.rnd.dice.type.Result;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Entity
public class GameHistory extends BaseEntity {

    @Column(name = "GAME_RESULT")
    @NotNull
    private Result gameResult;

    @Column(name = "PAYLOAD")
    @NotBlank
    private String payload;

    public GameHistory(@NotNull Result gameResult, @NotBlank String payload) {
        this.gameResult = gameResult;
        this.payload = payload;
    }
}
