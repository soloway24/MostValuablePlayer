package handlers.implementations;

import handlers.interfaces.GameHandler;
import utils.FileFormatConstants;
import utils.GameRulesConstants;

import java.util.HashMap;

public class HandballGameHandler extends GameHandler {

    @Override
    protected void createAndFillIndexToMultiplierMap() {
        indexToMultiplierMap = new HashMap<>();
        indexToMultiplierMap.put(FileFormatConstants.HANDBALL_GOALS_MADE_INDEX,
                GameRulesConstants.HANDBALL_GOALS_MADE_MULTIPLIER);
        indexToMultiplierMap.put(FileFormatConstants.HANDBALL_GOALS_RECEIVED_INDEX,
                GameRulesConstants.HANDBALL_GOALS_RECEIVED_MULTIPLIER);
    }


}
