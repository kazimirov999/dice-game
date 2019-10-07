ALTER TABLE game_history
    DROP FOREIGN KEY FK_PLAYER_GAME_HISTORY;
TRUNCATE TABLE game_history;
TRUNCATE TABLE dg_player;
ALTER TABLE game_history
    ADD CONSTRAINT FK_PLAYER_GAME_HISTORY
        foreign key (player_id)
            references dg_player (id);

INSERT INTO dg_player (id, secure, status, login_name, last_name, first_name, created_on, last_modified_on)
VALUES ('114b75c0-2b7f-4750-b40b-1637ab09a907', 'secureCode', 'ACTIVE', 'abevz', 'Bevz', 'Alex',
        '2019-05-06 12:09:35.3510000', '2019-05-06 12:09:35.3510000');

INSERT INTO dg_player (id, secure, status, login_name, last_name, first_name, created_on, last_modified_on)
VALUES ('114b75c0-2b7f-4750-b40b-1637ab09a906', 'secureCode', 'ACTIVE', 'amazur', 'Mazur', 'Alex',
        '2019-05-06 12:09:35.3510000', '2019-05-06 12:09:35.3510000');

INSERT INTO dg_player (id, secure, status, login_name, last_name, first_name, created_on, last_modified_on)
VALUES ('114b75c0-2b7f-4750-b40b-1637ab09a905', 'secureCode', 'INACTIVE', 'alev', 'Lev', 'Alex',
        '2019-05-06 12:09:35.3510000', '2019-05-06 12:09:35.3510000');

