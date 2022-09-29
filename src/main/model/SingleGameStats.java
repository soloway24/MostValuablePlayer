package main.model;

import main.exceptions.GameTypeNotSupported;
import org.apache.commons.lang3.EnumUtils;
import main.utils.FileFormatConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleGameStats that = (SingleGameStats) o;
        return gameType == that.gameType && Objects.equals(playerStats, that.playerStats) && Objects.equals(playersWithRatings, that.playersWithRatings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameType, playerStats, playersWithRatings);
    }

    @Override
    public String toString() {
        return "SingleGameStats{" +
                "gameType=" + gameType +
                ", playerStats=" + playerStats +
                ", playersWithRatings=" + playersWithRatings +
                '}';
    }
}
