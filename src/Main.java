import exceptions.GameTypeNotSupported;
import exceptions.IncorrectFileFormatException;
import io.GameReader;
import model.Player;
import model.SingleGameStats;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String fileExtension = ".csv";
        String delimiter = ";";
        GameReader gameReader = new GameReader(fileExtension, delimiter);

        try {
            List<SingleGameStats> games = gameReader.readDirectory("csv");
            MVPCalculator mvpCalculator = new MVPCalculator();
            Player mvpPlayer = mvpCalculator.calculateMVP(games);
            System.out.println("Most Valuable Player is: " + mvpPlayer);
        } catch (IOException | GameTypeNotSupported | IncorrectFileFormatException e) {
            e.printStackTrace();
        }
    }
}
