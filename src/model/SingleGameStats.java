package model;

import exceptions.GameTypeNotSupported;
import exceptions.IncorrectFileFormatException;
import org.apache.commons.lang3.EnumUtils;
import utils.FileFormatConstants;

import java.util.List;

public class SingleGameStats {

    private GameType gameType;
    private final List<List<String>> playerStats;

    public SingleGameStats(List<List<String>> fileLines) throws GameTypeNotSupported {
        gameType = getGameTypeFromFileLines(fileLines);
        playerStats = removeGameTypeNameFromLines(fileLines);
    }

    private GameType getGameTypeFromFileLines(List<List<String>> fileLines) throws GameTypeNotSupported {
        String gameTypeStr = fileLines.get(FileFormatConstants.GAME_TYPE_LINE_INDEX).get(0).trim();
        if(EnumUtils.isValidEnum(GameType.class, gameTypeStr)) {
            return GameType.valueOf(gameTypeStr);
        } else {
            throw new GameTypeNotSupported(gameTypeStr);
        }
    }
    private List<List<String>> removeGameTypeNameFromLines(List<List<String>> fileLines) {
        fileLines.remove(FileFormatConstants.GAME_TYPE_LINE_INDEX);
        return fileLines;
    }

    public GameType getGameType() {
        return gameType;
    }

    public List<List<String>> getPlayerStats() {
        return playerStats;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

}
