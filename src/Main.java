import exceptions.GameTypeNotFound;
import io.implementations.CSVGameReader;
import io.interfaces.GameReader;
import model.GameType;
import model.SingleGameStats;
import org.apache.commons.lang3.EnumUtils;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        GameReader gameReader = new CSVGameReader();
        try {
//            gameReader.readGame("csv/game1.csv");
            gameReader.readGame("csv/game2.csv");

        } catch (IOException | GameTypeNotFound e) {
            throw new RuntimeException(e);
        }

    }
}
