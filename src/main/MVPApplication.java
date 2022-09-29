package main;

import main.exceptions.*;
import main.io.GameReader;
import main.model.Player;
import main.model.SingleGameStats;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import main.validators.ValidatorManager;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MVPApplication {

    private ValidatorManager validatorManager;
    private MVPCalculator mvpCalculator;

    public MVPApplication() {
        validatorManager = new ValidatorManager();
        mvpCalculator = new MVPCalculator();
    }

    public List<SingleGameStats> processFilesFromDirectory(String directoryPath, String fileExtension, String delimiter) throws FileWrappedException, NoFilesWithExtensionFoundException, NoDirectoryFoundException {
        GameReader gameReader = new GameReader(fileExtension, delimiter);
        List<SingleGameStats> games = new ArrayList<>();

        File[] files = getFilesOfExtensionFromDirectoryFile(directoryPath, fileExtension);
        for(File file : files) {
            try {
                SingleGameStats game = gameReader.readGame(file.getPath());
                validatorManager.validate(game);
                games.add(game);
            } catch (IOException | GameTypeNotSupported | IncorrectFileFormatException | IncorrectFileDataException e) {
                throw new FileWrappedException("Exception in file '" + file.getPath() + "'. ", e);
            }
        }
        return games;
    }

    private File getDirectoryFromPath(String directoryPath) throws NoDirectoryFoundException {
        File directory = new File(directoryPath);
        if(!directory.isDirectory())
            throw new NoDirectoryFoundException(directoryPath);
        return directory;
    }


    private File[] getFilesOfExtensionFromDirectoryFile(String directoryPath, String fileExtension) throws NoDirectoryFoundException {
        File directory = getDirectoryFromPath(directoryPath);
        FileFilter fileFilter = new WildcardFileFilter("*" + fileExtension);
        return Objects.requireNonNull(directory.listFiles(fileFilter));
    }

    public Player calculateMVP(List<SingleGameStats> games) throws IncorrectFileDataException {
        return mvpCalculator.calculateMVP(games);
    }

    public ValidatorManager getValidatorManager() {
        return validatorManager;
    }

    public void setValidatorManager(ValidatorManager validatorManager) {
        this.validatorManager = validatorManager;
    }

    public MVPCalculator getMvpCalculator() {
        return mvpCalculator;
    }

    public void setMvpCalculator(MVPCalculator mvpCalculator) {
        this.mvpCalculator = mvpCalculator;
    }

    public static void main(String[] args) {
        MVPApplication application = new MVPApplication();
        String directoryPath = "csv/valid";
        String fileExtension = ".csv";
        String delimiter = ";";

        try {
            List<SingleGameStats> games = application.processFilesFromDirectory(directoryPath, fileExtension, delimiter);
            Player mvpPlayer = application.calculateMVP(games);
            System.out.println("Most Valuable Player is: " + mvpPlayer);
        } catch (FileWrappedException | NoFilesWithExtensionFoundException | IncorrectFileDataException |
                 NoDirectoryFoundException e) {
            System.out.println("MVP calculation is impossible.\nCannot process files in the directory '"
                    + directoryPath + "'.\n");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MVPApplication that = (MVPApplication) o;
        return Objects.equals(validatorManager, that.validatorManager) && Objects.equals(mvpCalculator, that.mvpCalculator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(validatorManager, mvpCalculator);
    }
}
