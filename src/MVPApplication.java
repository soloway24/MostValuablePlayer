import exceptions.FileWrappedException;
import exceptions.GameTypeNotSupported;
import exceptions.IncorrectFileDataException;
import exceptions.IncorrectFileFormatException;
import io.GameReader;
import model.Player;
import model.SingleGameStats;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import validators.ValidatorManager;

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

    public List<SingleGameStats> processFilesFromDirectory(String directoryPath, String fileExtension, String delimiter) throws FileWrappedException {
        File folder = new File(directoryPath);
        if(!folder.isDirectory())
            throw new IllegalArgumentException("You should provide a correct directory path.");

        GameReader gameReader = new GameReader(fileExtension, delimiter);
        List<SingleGameStats> games = new ArrayList<>();
        FileFilter fileFilter = new WildcardFileFilter("*" + fileExtension);
        File[] files = Objects.requireNonNull(folder.listFiles(fileFilter));

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

    public Player calculateMVP(List<SingleGameStats> games) {
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
        String directoryPath = "csv";
        String fileExtension = ".csv";
        String delimiter = ";";

        try {
            List<SingleGameStats> games = application.processFilesFromDirectory(directoryPath, fileExtension, delimiter);
            Player mvpPlayer = application.calculateMVP(games);
            System.out.println("Most Valuable Player is: " + mvpPlayer);
        } catch (FileWrappedException e) {
            System.out.println("MVP calculation is impossible.\nCannot process files in the directory '"
                    + directoryPath + "'.\n");
            System.out.println(e.getMessage());
        }
    }
}
