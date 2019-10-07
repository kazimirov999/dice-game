package com.game.rnd.dice.controller;

import com.game.rnd.dice.dto.NewPlayerDto;
import com.game.rnd.dice.dto.PlayerDto;
import com.game.rnd.dice.entity.Player;
import com.game.rnd.dice.service.PlayerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.net.HttpURLConnection.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@ApiResponses(value = {
        @ApiResponse(code = HTTP_BAD_REQUEST, message = "Request validation is failed"),
        @ApiResponse(code = HTTP_INTERNAL_ERROR, message = "System error occurred")
})
@RestController
@RequestMapping(value = "/v1/players")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final ModelMapper modelMapper;

    @ApiOperation(value = "Get list of the players")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_OK, message = "Request was successfully processed")
    })
    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Collection<PlayerDto>> getPlayers() {

        return ResponseEntity.ok(playerService.getAllPlayers()
                .stream()
                .map(player -> modelMapper.map(player, PlayerDto.class))
                .collect(Collectors.toList()));
    }

    @ApiOperation(value = "Create new player")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_CREATED, message = "Resource was successfully created")
    })
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PlayerDto> createPlayer(@Valid @RequestBody NewPlayerDto playerDto) {

        Player createdPlayer = playerService.createPlayer(modelMapper.map(playerDto, Player.class));

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdPlayer.getId()).toUri();

        return ResponseEntity.created(location).body(modelMapper.map(createdPlayer, PlayerDto.class));
    }

    @ApiOperation(value = "Get existing player")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_OK, message = "Request was successfully processed"),
            @ApiResponse(code = HTTP_NOT_FOUND, message = "The resource you were trying to reach is not found"),
    })
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<PlayerDto> getPlayerById(@PathVariable UUID id) {

        return ResponseEntity.ok(modelMapper.map(playerService.getPlayerById(id), PlayerDto.class));
    }

    @ApiOperation(value = "Get info is requested Player login name unique per system")
    @ApiResponses(value = {
            @ApiResponse(code = HTTP_OK, message = "Player login name is already taken"),
            @ApiResponse(code = HTTP_NOT_FOUND, message = "Player login name is unique")
    })
    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkPlayerPresence(@RequestParam String loginName) {
        return playerService.checkPlayerLoginNamePresence(loginName) ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }

}
