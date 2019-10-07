package com.game.rnd.dice.service;

import com.game.rnd.dice.dto.GameResult;
import com.game.rnd.dice.entity.GameHistory;
import com.game.rnd.dice.entity.Player;
import com.game.rnd.dice.type.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.game.rnd.dice.type.Result.LOSE;
import static com.game.rnd.dice.type.Result.WIN;

@RequiredArgsConstructor
@Service
public class Game4DiceService implements GameService {

    private final PlayerService playerService;
    private final Random random;

    @Transactional
    @Override
    public GameResult playGame(UUID playerId) {
        List<Integer> playResult = roll4Dice();

        Result gameResult = playResult.contains(6) ? WIN : LOSE;

        writeHistory(playerId, gameResult, playResult.toString());

        return GameResult.builder()
                .result(gameResult)
                .payload(playResult.toString())
                .build();
    }

    private List<Integer> roll4Dice() {
        return random.ints(4, 1, 7)
                .boxed()
                .collect(Collectors.toList());
    }

    private void writeHistory(UUID playerId, Result result, String payload) {
        Player player = playerService.getPlayerById(playerId);

        player.getGameHistory().add(new GameHistory(result, payload));
    }
}
