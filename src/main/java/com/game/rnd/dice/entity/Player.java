package com.game.rnd.dice.entity;

import com.game.rnd.dice.listener.PlayerSecureKeyListener;
import com.game.rnd.dice.type.Status;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

import static javax.persistence.EnumType.STRING;

@Data
@Entity
@Table(name = "dg_player")
@EntityListeners(PlayerSecureKeyListener.class)
public class Player extends BaseEntity {

    @Column(name = "LOGIN_NAME")
    @NotBlank
    @Size(max = 64)
    private String loginName;

    @Column(name = "FIRST_NAME")
    @NotBlank
    @Size(max = 64)
    private String firstName;

    @Column(name = "LAST_NAME")
    @NotBlank
    @Size(max = 64)
    private String lastName;

    @Column(name = "SECURE")
    @NotBlank
    private String secure;

    @Enumerated(STRING)
    @Column(name = "STATUS")
    @NotNull
    private Status status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "player_id", foreignKey = @ForeignKey(name = "FK_PLAYER_GAME_HISTORY"))
    private List<GameHistory> gameHistory = Collections.emptyList();

}
