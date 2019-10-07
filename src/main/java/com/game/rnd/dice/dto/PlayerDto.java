package com.game.rnd.dice.dto;

import com.game.rnd.dice.entity.GameHistory;
import com.game.rnd.dice.type.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PlayerDto {

    private UUID id;
    private String loginName;
    private String firstName;
    private String lastName;
    private Status status;
    private List<GameHistory> gameHistory;
}
