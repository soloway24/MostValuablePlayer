package handlers.interfaces;

import utils.GameRulesConstants;
import model.SingleGameStats;

import java.util.Map;

public abstract class GameHandler {

    private Map<Integer, Integer> indexToPointsMap;

    public abstract Map<Integer, Integer> getIndexToMultiplierMap();


    public void handleGame(SingleGameStats stats) {

    }

    public int getPointsForWin() {
        return GameRulesConstants.STANDARD_WIN_POINTS;
    }

}
