package validators.interfaces;

import exceptions.IncorrectFileDataException;
import exceptions.IncorrectFileFormatException;

import java.util.*;
import java.util.stream.Collectors;

public abstract class FileValidator {

    protected static int RECORD_LENGTH = 0;

    protected static int NUMBER_OF_TEAMS = 2;
    protected static int PLAYER_NICKNAME_INDEX = 1;
    protected static int PLAYER_NUMBER_INDEX = 2;
    protected static int PLAYER_TEAM_NAME_INDEX = 3;

    public void validate(List<List<String>> records) throws IncorrectFileFormatException, IncorrectFileDataException {
        validateFormatFull(records);
        validateDataFull(records);
    }

    private void validateFormatFull(List<List<String>> records) throws IncorrectFileFormatException {
        validateFormatGeneral(records);
        validateFormatSpecificGameType(records);
    }

    private void validateDataFull(List<List<String>> records) throws IncorrectFileDataException {
        validateDataGeneral(records);
        validateDataSpecificGameType(records);
    }

    private void validateFormatGeneral(List<List<String>> records) throws IncorrectFileFormatException {
        validateRecordLength(records);
        validateNotBlankFields(records);
    }
    protected abstract void validateFormatSpecificGameType(List<List<String>> records);

    private void validateDataGeneral(List<List<String>> records) throws IncorrectFileDataException {
        validateNicknames(records);
        validateTeamSizesAndUniqueNumbersInTeams(records);
    }

    protected abstract void validateDataSpecificGameType(List<List<String>> records);

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

    private void validateNicknames(List<List<String>> records) throws IncorrectFileDataException {
       Set<String> nicknames = new HashSet<>();

       for(List<String> record : records) {
           String nickname = record.get(PLAYER_NICKNAME_INDEX);
           nicknames.add(nickname);
       }

       if(nicknames.size() != records.size())
           throw new IncorrectFileDataException("Player nicknames should be unique.");
   }

    private void validateTeamSizesAndUniqueNumbersInTeams(List<List<String>> records) throws IncorrectFileDataException {
        Map<String, Set<Integer>> teamToNumbersMap = new HashMap<>();

        for(List<String> record : records) {
            int number = Integer.parseInt(record.get(PLAYER_NUMBER_INDEX));
            if(number <= 0)
                throw new IncorrectFileDataException("Player number should be positive.");

            String teamName = record.get(PLAYER_TEAM_NAME_INDEX);
            if(teamToNumbersMap.containsKey(teamName)) {
                teamToNumbersMap.get(teamName).add(number);
            } else {
                Set<Integer> numberSet = new HashSet<>();
                numberSet.add(number);
                teamToNumbersMap.put(teamName, numberSet);
            }
        }

        if(teamToNumbersMap.size() != NUMBER_OF_TEAMS)
            throw new IncorrectFileDataException("Incorrect number of teams for the given game type.");

        List<Integer> teamSizes = teamToNumbersMap.values().stream().map(Set::size).collect(Collectors.toList());
        int sumOfTeamSizes = teamSizes.stream().mapToInt(Integer::intValue).sum();
        if(sumOfTeamSizes != records.size())
            throw new IncorrectFileDataException("For every team, player numbers should be unique.");

        Set<Integer> teamSizesSet = new HashSet<>(teamSizes);
        if(teamSizesSet.size() != 1)
            throw new IncorrectFileDataException("Team sizes are not equal.");

    }

}
