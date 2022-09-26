package model;

import exceptions.GameTypeNotFound;
import org.apache.commons.lang3.EnumUtils;
import utils.FileFormatConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SingleGameStats {

    private GameType gameType;
    private List<List<String>> playerStats;
    private List<Player> players;

    public SingleGameStats() {}

    public SingleGameStats(List<List<String>> fileLines) throws GameTypeNotFound {
        gameType = getGameTypeFromFileLines(fileLines);
        playerStats = removeGameTypeNameFromLines(fileLines);

        // TO DO
        // validate csv body




        players = new ArrayList<>();
    }

    private void validatePlayerStats(List<List<String>> stats) {

    }

    private GameType getGameTypeFromFileLines(List<List<String>> fileLines) throws GameTypeNotFound {
        String gameTypeStr = fileLines.get(FileFormatConstants.GAME_TYPE_LINE_INDEX).get(0).trim();
        if(EnumUtils.isValidEnum(GameType.class, gameTypeStr)) {
            return GameType.valueOf(gameTypeStr);
        } else {
            throw new GameTypeNotFound();
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

    public List<Player> getPlayers() {
        return players;
    }

    public void setGameType(GameType gameType) {
        this.gameType = gameType;
    }

}
