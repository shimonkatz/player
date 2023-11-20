package com.example.player.service;

import com.example.player.model.PlayerCSVRecord;

import java.io.File;
import java.util.List;

public interface PlayerCsvService {
    List<PlayerCSVRecord> convertCSV(File csvFile);
}
