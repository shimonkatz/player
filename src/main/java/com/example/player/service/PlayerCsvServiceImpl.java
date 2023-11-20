package com.example.player.service;

import com.example.player.model.PlayerCSVRecord;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class PlayerCsvServiceImpl implements PlayerCsvService {
    @Override
    public List<PlayerCSVRecord> convertCSV(File csvFile) {
        try {
            List<PlayerCSVRecord> playerCSVRecords = new CsvToBeanBuilder<PlayerCSVRecord>(new FileReader(csvFile))
                    .withType(PlayerCSVRecord.class)
                    .build().parse();
            return playerCSVRecords;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
