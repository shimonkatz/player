package com.example.player.controller;

import com.example.player.model.Player;
import com.example.player.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    PlayerService playerService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getPlayers() throws Exception {
        List<Player> players = List.of(Player.builder()
                        .playerID("aardsda01")
                        .nameFirst("David")
                        .nameLast("Aardsma")
                        .build(),
                Player.builder()
                        .playerID("aaronha01")
                        .nameFirst("Hank")
                        .nameLast("Aaron")
                        .build()
        );

        Page<Player> page = new PageImpl<>(players);
        given(playerService.getPlayers(null, null)).willReturn(page);

        mockMvc.perform(get(PlayerController.PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content.length()", is(2)))
                .andExpect(jsonPath("$.content[0].nameLast", is("Aardsma")));
    }

    @Test
    void getPlayerByPlayerID() throws Exception {
        Player player = Player.builder()
                .playerID("aardsda01")
                .nameFirst("David")
                .nameLast("Aardsma")
                .build();

        given(playerService.findByPlayerID((any()))).willReturn(Optional.of(player));

        mockMvc.perform(get(PlayerController.PATH + PlayerController.PLAYER_ID, "aardsda01"))
                .andExpect(jsonPath("$.playerID", is("aardsda01")));

    }

    @Test
    void getPlayerByPlayerIDNotFound() throws Exception {
        given(playerService.findByPlayerID((any()))).willReturn(Optional.empty());

        mockMvc.perform(get(PlayerController.PATH + PlayerController.PLAYER_ID, "3"))
                .andExpect(status().isNotFound());
    }
}