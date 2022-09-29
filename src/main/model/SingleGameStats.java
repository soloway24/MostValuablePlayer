package main.model;

import main.exceptions.GameTypeNotSupported;
import org.apache.commons.lang3.EnumUtils;
import main.utils.FileFormatConstants;

import java.util.ArrayList;
import java.util.List;

public class SingleGameStats {

    private GameType gameType;
    private final List<List<String>> playerStats;

    private List<Player> playersWithRatings;

    public SingleGameStats(List<List<String>> fileLines) throws GameTypeNotSupported {
        gameType = getGameTypeFromFileLines(fileLines);
        playerStats = removeGameTypeNameFromLines(fileLines);
        playersWithRatings = new ArrayList<>();
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

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

    public List<List<String>> getPlayerStats() {
        return playerStats;
    }

    public List<Player> getPlayersWithRatings() {
        return playersWithRatings;
    }

    public void setPlayersWithRatings(List<Player> playersWithRatings) {
        this.playersWithRatings = playersWithRatings;
    }
}
