package handlers.implementations;

import handlers.interfaces.GameHandler;

import java.util.HashMap;
import java.util.Map;

import utils.FileFormatConstants;
import utils.GameRulesConstants;

public class BasketballGameHandler extends GameHandler {

    @Override
    protected void createAndFillIndexToMultiplierMap() {
        indexToMultiplierMap = new HashMap<>();
        indexToMultiplierMap.put(FileFormatConstants.BASKETBALL_SCORED_POINTS_INDEX,
                GameRulesConstants.BASKETBALL_SCORED_POINTS_MULTIPLIER);
        indexToMultiplierMap.put(FileFormatConstants.BASKETBALL_REBOUNDS_INDEX,
                GameRulesConstants.BASKETBALL_REBOUNDS_MULTIPLIER);
        indexToMultiplierMap.put(FileFormatConstants.BASKETBALL_ASSISTS_INDEX,
                GameRulesConstants.BASKETBALL_ASSISTS_MULTIPLIER);
    }

}
