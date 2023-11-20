package com.example.player.repositories;

import com.example.player.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
    Page<Player> findAll(Pageable pageable);
    Optional<Player> findPlayerByPlayerID(String playerID);

}
