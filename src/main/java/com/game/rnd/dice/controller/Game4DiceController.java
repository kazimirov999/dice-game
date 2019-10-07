package com.game.rnd.dice.controller;

import com.game.rnd.dice.dto.GameResult;
import com.game.rnd.dice.service.GameService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static java.net.HttpURLConnection.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@ApiResponses(value = {
        @ApiResponse(code = HTTP_UNAUTHORIZED, message = "You are not authorized to play game"),
        @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "System error occurred")
})
@RestController
@RequestMapping(value = "/v1/game/game4dice")
@RequiredArgsConstructor
public class Game4DiceController {

    private final GameService game4DiceService;

    @ApiOperation(value = "Play game")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_OK, message = "Game was played"),
    })
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GameResult> playGame(@PathVariable UUID id) {

        return ResponseEntity.ok(game4DiceService.playGame(id));
    }
}
