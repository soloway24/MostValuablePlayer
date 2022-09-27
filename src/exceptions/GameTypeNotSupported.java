package exceptions;

public class GameTypeNotSupported extends Exception {

    public GameTypeNotSupported(String gameType) {
        super("Game type '" + gameType + "' is not supported.");
    }

}
