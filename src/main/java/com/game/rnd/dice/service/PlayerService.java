package com.game.rnd.dice.service;

import com.game.rnd.dice.entity.Player;
import com.game.rnd.dice.exception.PlayerAlreadyExists;
import com.game.rnd.dice.exception.PlayerNotFoundException;
import com.game.rnd.dice.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);

    private final PlayerRepository playerRepository;

    @Transactional
    public Player createPlayer(Player player) {
        checkPlayerPresence(player);
        Player createdPlayer = playerRepository.save(player);
        LOGGER.info("Created Player: {}", player);
        return createdPlayer;
    }

    @Transactional(readOnly = true)
    public Collection<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Player getPlayerById(UUID id) {
        return playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
    }

    @Transactional(readOnly = true)
    public boolean checkPlayerLoginNamePresence(String loginName) {
        return playerRepository.existsByLoginName(loginName);
    }

    private void checkPlayerPresence(Player player) {
        if (playerRepository.existsByLoginName(player.getLoginName())) {
            throw new PlayerAlreadyExists(player.getLoginName());
        }
    }

}
