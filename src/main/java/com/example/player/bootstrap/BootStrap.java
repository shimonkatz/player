package com.example.player.bootstrap;

import com.example.player.model.Player;
import com.example.player.model.PlayerCSVRecord;
import com.example.player.repositories.PlayerRepository;
import com.example.player.service.PlayerCsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootStrap implements CommandLineRunner {

    private final PlayerCsvService playerCsvService;
    private final PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadPlayersFromCsv();
    }

    // load from file and save into database
    private void loadPlayersFromCsv() throws FileNotFoundException {

        if (playerRepository.count() == 0) {

            File file = ResourceUtils.getFile("classpath:csvdata/player.csv");

            List<PlayerCSVRecord> playerCSVRecords = playerCsvService.convertCSV(file);

            // save to DataBase
            // You can use this point to change data types
            playerCSVRecords.forEach(record -> {
                playerRepository.save(Player.builder()
                        .playerID(record.getPlayerID())
                        .birthYear(record.getBirthYear())
                        .birthMonth(record.getBirthMonth())
                        .birthDay(record.getBirthDay())
                        .birthCountry(record.getBirthCountry())
                        .birthState(record.getBirthState())
                        .birthCity(record.getBirthCity())
                        .deathYear(record.getDeathYear())
                        .deathMonth(record.getDeathMonth())
                        .deathDay(record.getDeathDay())
                        .deathCountry(record.getDeathCountry())
                        .deathState(record.getDeathState())
                        .deathCity(record.getDeathCity())
                        .nameFirst(record.getNameFirst())
                        .nameLast(record.getNameLast())
                        .nameGiven(record.getNameGiven())
                        .weight(record.getWeight())
                        .height(record.getHeight())
                        .bats(record.getBats())
                        .throwsL(record.getThrowsL())
                        .debut(record.getDebut())
                        .finalGame(record.getFinalGame())
                        .retroID(record.getRetroID())
                        .bbrefID(record.getBbrefID())
                        .build());
            });
        }
    }
}
