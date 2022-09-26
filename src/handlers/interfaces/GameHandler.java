package handlers.interfaces;

import utils.GameRulesConstants;
import model.SingleGameStats;

import java.util.Map;

public abstract class GameHandler {

    protected Map<Integer, Integer> indexToMultiplierMap;

    public GameHandler(){
        createAndFillIndexToMultiplierMap();
    }
    public Map<Integer, Integer> getIndexToMultiplierMap() {
        return indexToMultiplierMap;
    }

    protected abstract void createAndFillIndexToMultiplierMap();

    public SingleGameStats handleGame(SingleGameStats stats) {
        // TO DO
        // use map to calculate added points


        // add points to players in List<Player> players in SingleGameStats
        return stats;
    }

    public int getPointsForWin() {
        return GameRulesConstants.STANDARD_WIN_POINTS;
    }

}
