package com.example.player.service;

import com.example.player.model.Player;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PlayerService {

    Page<Player> getPlayers(Integer pageNumber, Integer pageSize);

    Optional<Player> findByPlayerID(String playerID);

}
