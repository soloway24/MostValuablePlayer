package io;

import exceptions.GameTypeNotSupported;
import exceptions.IncorrectFileFormatException;
import model.SingleGameStats;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GameReader {

    private String fileExtension;
    private String delimiter;

    public GameReader(String fileExtension, String delimiter) {
        this.fileExtension = fileExtension;
        this.delimiter = delimiter;
    }

    public SingleGameStats readGame(String filePath) throws IOException, GameTypeNotSupported, IncorrectFileFormatException {
        List<List<String>> fileLines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                fileLines.add(Arrays.asList(values));
            }
        }
        return new SingleGameStats(fileLines);
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameReader that = (GameReader) o;
        return Objects.equals(fileExtension, that.fileExtension) && Objects.equals(delimiter, that.delimiter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileExtension, delimiter);
    }
}
