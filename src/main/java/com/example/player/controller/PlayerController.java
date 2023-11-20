package com.example.player.controller;

import com.example.player.model.Player;
import com.example.player.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = PlayerController.PATH)
@RequiredArgsConstructor
public class PlayerController {

    public static final String PATH = "/api/players";
    public static final String PLAYER_ID= "/{playerID}";
    private final PlayerService playerService;

    @GetMapping
    Page<Player> getPlayers(@RequestParam(required = false) Integer pageNumber, @RequestParam(required = false) Integer pageSize){
        return playerService.getPlayers(pageNumber, pageSize);
    }

    @GetMapping(PLAYER_ID)
    Player getPlayerByPlayerID(@PathVariable String playerID){
        return playerService.findByPlayerID(playerID).orElseThrow(() -> new NotFoundException("Player with ID " + playerID + " not found !!"));
    }
}
