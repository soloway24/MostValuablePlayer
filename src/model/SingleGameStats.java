package model;

import exceptions.GameTypeNotFound;
import org.apache.commons.lang3.EnumUtils;

import java.util.List;
import java.util.Map;

public class SingleGameStats {

    private GameType gameType;
    private List<List<String>> playerStats;
    private List<Player> players;

    public SingleGameStats() {}

    public SingleGameStats(List<List<String>> fileLines) {

    }

    private void initialize(List<List<String>> fileLines) throws GameTypeNotFound {
        setGameTypeFromFileLines(fileLines);

    }

    private void setGameTypeFromFileLines(List<List<String>> fileLines) throws GameTypeNotFound {
        String gameTypeStr = fileLines.get(0).get(0);
        if(EnumUtils.isValidEnum(GameType.class, gameTypeStr)) {
            gameType = GameType.valueOf(gameTypeStr);
        } else {
            throw new GameTypeNotFound();
        }
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
