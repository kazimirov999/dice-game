package com.game.rnd.dice.listener;

import com.game.rnd.dice.entity.Player;
import org.apache.commons.codec.digest.DigestUtils;

import javax.persistence.PrePersist;

public class PlayerSecureKeyListener {

    @PrePersist
    private void prePersist(Player player) {
        player.setSecure(DigestUtils.sha256Hex(player.getLoginName()));
    }


}
