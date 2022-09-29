package main.validators.implementations;

import main.exceptions.IncorrectFileDataException;
import main.utils.FileFormatConstants;
import main.utils.UtilMethods;
import main.validators.interfaces.FileValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandBallFileValidator extends FileValidator {

    private final Map<String, Integer> teamToGoalsMap;
    public HandBallFileValidator() {
        RECORD_LENGTH = 6;
        teamToGoalsMap = new HashMap<>();
    }

    @Override
    protected void validateDataGameTypeSpecific(List<List<String>> records) throws IncorrectFileDataException {
        validatePointFields(records);
        reinitializeTeamToGoalsMap(records);
        validateReceivedGoals(records);
    }

    private void reinitializeTeamToGoalsMap(List<List<String>> records) throws IncorrectFileDataException {
        teamToGoalsMap.clear();
        for(List<String> record : records) {

            int goalsMade = UtilMethods.tryParseInt(record.get(FileFormatConstants.HANDBALL_GOALS_MADE_INDEX));
            String teamName = record.get(FileFormatConstants.PLAYER_TEAM_NAME_INDEX);
            teamToGoalsMap.put(teamName, teamToGoalsMap.getOrDefault(teamName, 0) + goalsMade);
        }
    }
    @Override
    protected void validatePointFields(List<List<String>> records) throws IncorrectFileDataException {
        for(List<String> record : records) {

            int goalsMade = UtilMethods.tryParseInt(record.get(FileFormatConstants.HANDBALL_GOALS_MADE_INDEX));
            int goalsReceived = UtilMethods.tryParseInt(record.get(FileFormatConstants.HANDBALL_GOALS_RECEIVED_INDEX));

            if(goalsMade < 0 || goalsReceived < 0)
                throw new IncorrectFileDataException("No negative values allowed for point fields.");
        }
    }

    private void validateReceivedGoals(List<List<String>> records) throws IncorrectFileDataException {
        for(List<String> record : records) {

            int goalsReceived = UtilMethods.tryParseInt(record.get(FileFormatConstants.HANDBALL_GOALS_RECEIVED_INDEX));

            String teamName = record.get(FileFormatConstants.PLAYER_TEAM_NAME_INDEX);
            for(String secondTeam : teamToGoalsMap.keySet()) {
                if(!teamName.equals(secondTeam) && goalsReceived != teamToGoalsMap.get(secondTeam))
                    throw new IncorrectFileDataException("Scored goals by '" + secondTeam + "' is not equal " +
                            "to received goals by '" + teamName + "'.");
            }
        }
    }

}
