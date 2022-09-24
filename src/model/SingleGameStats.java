package model;

import exceptions.GameTypeNotFound;
import org.apache.commons.lang3.EnumUtils;

import java.util.List;

public class SingleGameStats {

    private GameType gameType;
    private List<List<String>> playerStats;

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


}
