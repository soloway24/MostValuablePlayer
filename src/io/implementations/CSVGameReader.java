package io.implementations;

import exceptions.GameTypeNotFound;
import io.interfaces.GameReader;
import model.SingleGameStats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVGameReader implements GameReader {
    private static final String DELIMITER = ";";
    @Override
    public SingleGameStats readGame(String filePath) throws IOException, GameTypeNotFound {
        List<List<String>> fileLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(DELIMITER);
                fileLines.add(Arrays.asList(values));
            }
        }
        return new SingleGameStats(fileLines);
    }
}
