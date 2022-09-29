package main.handlers.implementations;

import main.exceptions.IncorrectFileDataException;
import main.handlers.abstractions.GameHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.model.SingleGameStats;
import main.utils.FileFormatConstants;
import main.utils.GameRulesConstants;
import main.utils.UtilMethods;

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

    @Override
    protected Map<String, Integer> getTeamToPointsMap(SingleGameStats game) throws IncorrectFileDataException {
        List<List<String>> records = game.getPlayerStats();
        Map<String, Integer> teamToPointsMap = new HashMap<>();
        for(List<String> record : records) {
            int scored = UtilMethods.tryParseInt(record.get(FileFormatConstants.BASKETBALL_SCORED_POINTS_INDEX));
            String teamName = record.get(FileFormatConstants.PLAYER_TEAM_NAME_INDEX);
            teamToPointsMap.put(teamName, teamToPointsMap.getOrDefault(teamName, 0) + scored);
        }
        return teamToPointsMap;
    }

}
