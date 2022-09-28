package validators.implementations;

import exceptions.IncorrectFileDataException;
import utils.FileFormatConstants;
import validators.interfaces.FileValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketballFileValidator extends FileValidator {

    public BasketballFileValidator() {
        RECORD_LENGTH = 7;
    }

    @Override
    protected void validateDataGameTypeSpecific(List<List<String>> records) throws IncorrectFileDataException {
        validatePointFields(records);
        validateGameWinner(records);
    }

    @Override
    protected void validatePointFields(List<List<String>> records) throws IncorrectFileDataException {
        for(List<String> record : records) {

            int scored = Integer.parseInt(record.get(FileFormatConstants.BASKETBALL_SCORED_POINTS_INDEX));
            int rebounds = Integer.parseInt(record.get(FileFormatConstants.BASKETBALL_SCORED_POINTS_INDEX));
            int assists = Integer.parseInt(record.get(FileFormatConstants.BASKETBALL_SCORED_POINTS_INDEX));

            if(scored < 0 || rebounds < 0 || assists < 0)
                throw new IncorrectFileDataException("No negative values allowed for point fields.");
        }
    }

    @Override
    protected void validateGameWinner(List<List<String>> records) throws IncorrectFileDataException {
        Map<String, Integer> teamToPointsMap = new HashMap<>();
        for(List<String> record : records) {
            int scored = Integer.parseInt(record.get(FileFormatConstants.BASKETBALL_SCORED_POINTS_INDEX));
            String teamName = record.get(PLAYER_TEAM_NAME_INDEX);
            teamToPointsMap.put(teamName, teamToPointsMap.getOrDefault(teamName, 0) + scored);
        }
        checkGameHasWinner(teamToPointsMap);
    }
}
