package io;

import exceptions.GameTypeNotSupported;
import exceptions.IncorrectFileFormatException;
import model.SingleGameStats;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import java.io.*;
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

    public List<SingleGameStats> readDirectory(String directoryPath) throws GameTypeNotSupported, IOException, IncorrectFileFormatException {
        File folder = new File(directoryPath);
        if(!folder.isDirectory())
            throw new IllegalArgumentException("You should provide a correct directory path.");

        List<SingleGameStats> games = new ArrayList<>();
        FileFilter fileFilter = new WildcardFileFilter("*" + fileExtension);
        File[] files = Objects.requireNonNull(folder.listFiles(fileFilter));
        for(File file : files) {
            SingleGameStats game = readGame(file.getPath());
            games.add(game);
        }
        return games;
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
