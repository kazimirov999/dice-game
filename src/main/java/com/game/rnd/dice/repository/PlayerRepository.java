package com.game.rnd.dice.repository;

import com.game.rnd.dice.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {

    boolean existsByLoginName(String loginName);
}
