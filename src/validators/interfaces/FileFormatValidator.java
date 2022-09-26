package validators.interfaces;

import exceptions.IncorrectFileFormatException;
import java.util.*;

public abstract class FileFormatValidator {

    protected static int RECORD_LENGTH = 0;

    protected static int NUMBER_OF_TEAMS = 2;
    protected static int PLAYER_NICKNAME_INDEX = 1;
    protected static int PLAYER_NUMBER_INDEX = 2;
    protected static int PLAYER_TEAM_NAME_INDEX = 3;

    public void validate(List<List<String>> records) throws IncorrectFileFormatException {
        doGeneralValidation(records);
        doSpecificValidation(records);
    }

    protected abstract void doSpecificValidation(List<List<String>> records);
    private void doGeneralValidation(List<List<String>> records) throws IncorrectFileFormatException {
        validateRecordLength(records);
        validateNotBlankFields(records);
        validateCommonFields(records);
    }

    private void validateRecordLength(List<List<String>> records) throws IncorrectFileFormatException {
        for(List<String> record : records) {
            if(record.size() != RECORD_LENGTH)
                throw new IncorrectFileFormatException();
        }
    }

    private void validateNotBlankFields(List<List<String>> records) throws IncorrectFileFormatException {
        for(List<String> record : records) {
            for(String field : record) {
                if(field.isBlank())
                    throw new IncorrectFileFormatException();
            }
        }
    }

    /*  not decomposing to smaller methods like getUniqueNicknames/Numbers
        in order not to iterate through the loop too many times
    */
    private void validateCommonFields(List<List<String>> records) throws IncorrectFileFormatException {
        Set<String> nicknames = new HashSet<>();
        Set<Integer> numbers  = new HashSet<>();
        Map<String, Integer> teamPlayersMap = new HashMap<>();


        for(List<String> record : records) {
            String nickname = record.get(PLAYER_NICKNAME_INDEX);
            nicknames.add(nickname);

            int number = Integer.parseInt(record.get(PLAYER_NUMBER_INDEX));
            if(number <= 0)
                throw new IncorrectFileFormatException();
            numbers.add(number);

            String teamName = record.get(PLAYER_TEAM_NAME_INDEX);
            teamPlayersMap.put(teamName, teamPlayersMap.getOrDefault(teamName, 0) + 1);
        }

        if (nicknames.size() != records.size()
                ||  numbers.size() != records.size()
                ||  teamPlayersMap.size() != NUMBER_OF_TEAMS
                ||  !teamsHaveEqualNumberOfPlayers(teamPlayersMap)) throw new IncorrectFileFormatException();
    }

    private boolean teamsHaveEqualNumberOfPlayers(Map<String, Integer> teamPlayersMap) {
        Set<Integer> uniqueQuantities = new HashSet<>(teamPlayersMap.values());
        return uniqueQuantities.size() == 1;
    }

}
