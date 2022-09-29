package main.handlers.implementations;

import main.exceptions.IncorrectFileDataException;
import main.handlers.interfaces.GameHandler;
import main.model.SingleGameStats;
import main.utils.FileFormatConstants;
import main.utils.GameRulesConstants;
import main.utils.UtilMethods;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandballGameHandler extends GameHandler {

    @Override
    protected void createAndFillIndexToMultiplierMap() {
        indexToMultiplierMap = new HashMap<>();
        indexToMultiplierMap.put(FileFormatConstants.HANDBALL_GOALS_MADE_INDEX,
                GameRulesConstants.HANDBALL_GOALS_MADE_MULTIPLIER);
        indexToMultiplierMap.put(FileFormatConstants.HANDBALL_GOALS_RECEIVED_INDEX,
                GameRulesConstants.HANDBALL_GOALS_RECEIVED_MULTIPLIER);
    }

    @Override
    protected Map<String, Integer> getTeamToPointsMap(SingleGameStats game) throws IncorrectFileDataException {
        List<List<String>> records = game.getPlayerStats();

        Map<String, Integer> teamToGoalsMap = new HashMap<>();
        for(List<String> record : records) {

            int goalsMade = UtilMethods.tryParseInt(record.get(FileFormatConstants.HANDBALL_GOALS_MADE_INDEX));

            String teamName = record.get(FileFormatConstants.PLAYER_TEAM_NAME_INDEX);
            teamToGoalsMap.put(teamName, teamToGoalsMap.getOrDefault(teamName, 0) + goalsMade);
        }
        return teamToGoalsMap;
    }
}
