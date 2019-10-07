ALTER TABLE game_history
    DROP FOREIGN KEY FK_PLAYER_GAME_HISTORY;
TRUNCATE TABLE dg_player;
ALTER TABLE game_history
    ADD CONSTRAINT FK_PLAYER_GAME_HISTORY
        foreign key (player_id)
            references dg_player (id);