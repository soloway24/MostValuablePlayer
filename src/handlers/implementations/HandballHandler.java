package handlers.implementations;

import handlers.interfaces.GameHandler;
import model.SingleGameStats;
import utils.GameRulesConstants;

import java.util.HashMap;
import java.util.Map;

public class HandballHandler extends GameHandler {

    @Override
    public Map<Integer, Integer> getIndexToMultiplierMap() {
        Map<Integer, Integer> indexToMultiplierMap = new HashMap<>();
        indexToMultiplierMap.put(GameRulesConstants.HANDBALL_GOALS_MADE_INDEX,
                GameRulesConstants.HANDBALL_GOALS_MADE_MULTIPLIER);
        indexToMultiplierMap.put(GameRulesConstants.HANDBALL_GOALS_RECEIVED_INDEX,
                GameRulesConstants.HANDBALL_GOALS_RECEIVED_MULTIPLIER);
        return indexToMultiplierMap;
    }

}
