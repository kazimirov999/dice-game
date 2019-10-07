package com.game.rnd.dice.service;

import com.game.rnd.dice.dto.GameResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface GameService {
    @Transactional
    GameResult playGame(UUID playerId);
}
