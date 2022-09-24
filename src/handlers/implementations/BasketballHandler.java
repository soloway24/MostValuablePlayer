package handlers.implementations;

import handlers.interfaces.GameHandler;

import java.util.HashMap;
import java.util.Map;
import utils.GameRulesConstants;

public class BasketballHandler extends GameHandler {

    @Override
    public Map<Integer, Integer> getIndexToMultiplierMap() {
        Map<Integer, Integer> indexToMultiplierMap = new HashMap<>();
        indexToMultiplierMap.put(GameRulesConstants.BASKETBALL_SCORED_POINTS_INDEX,
                GameRulesConstants.BASKETBALL_SCORED_POINTS_MULTIPLIER);
        indexToMultiplierMap.put(GameRulesConstants.BASKETBALL_REBOUNDS_INDEX,
                GameRulesConstants.BASKETBALL_REBOUNDS_MULTIPLIER);
        indexToMultiplierMap.put(GameRulesConstants.BASKETBALL_ASSISTS_INDEX,
                GameRulesConstants.BASKETBALL_ASSISTS_MULTIPLIER);
        return indexToMultiplierMap;
    }

}
