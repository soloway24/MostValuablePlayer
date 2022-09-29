package main.validators.abstractions;

import main.exceptions.IncorrectFileDataException;
import main.exceptions.IncorrectFileFormatException;
import main.utils.FileFormatConstants;
import main.utils.UtilMethods;

import java.util.*;
import java.util.stream.Collectors;

public abstract class FileValidator {

    protected int recordLength = 0;
    protected int numberOfTeams = 2;
    public void validate(List<List<String>> records) throws IncorrectFileFormatException, IncorrectFileDataException {
        validateFormatFull(records);
        validateDataFull(records);
    }

    private void validateFormatFull(List<List<String>> records) throws IncorrectFileFormatException {
        validateRecordLength(records);
        validateNotBlankFields(records);
    }

    private void validateDataFull(List<List<String>> records) throws IncorrectFileDataException {
        validateDataGeneral(records);
        validateDataGameTypeSpecific(records);
    }

    private void validateDataGeneral(List<List<String>> records) throws IncorrectFileDataException {
        validateNicknames(records);
        validateTeamSizesAndUniqueNumbersInTeams(records);
    }

    protected abstract void validateDataGameTypeSpecific(List<List<String>> records) throws IncorrectFileDataException;

    private void validateRecordLength(List<List<String>> records) throws IncorrectFileFormatException {
        for(List<String> record : records) {
            if(record.size() != recordLength)
                throw new IncorrectFileFormatException(recordLength - record.size() + " fields are missing.");
        }
    }

    private void validateNotBlankFields(List<List<String>> records) throws IncorrectFileFormatException {
        for(List<String> record : records) {
            for(String field : record) {
                if(field.isBlank())
                    throw new IncorrectFileFormatException("No blank fields allowed in a game file.");
            }
        }
    }

    private void validateNicknames(List<List<String>> records) throws IncorrectFileDataException {
       Set<String> nicknames = new HashSet<>();

       for(List<String> record : records) {
           String nickname = record.get(FileFormatConstants.PLAYER_NICKNAME_INDEX);
           nicknames.add(nickname);
       }

       if(nicknames.size() != records.size())
           throw new IncorrectFileDataException("Player nicknames should be unique.");
   }

    private void validateTeamSizesAndUniqueNumbersInTeams(List<List<String>> records) throws IncorrectFileDataException {
        Map<String, Set<Integer>> teamToNumbersMap = new HashMap<>();

        for(List<String> record : records) {
            int number = UtilMethods.tryParseInt(record.get(FileFormatConstants.PLAYER_NUMBER_INDEX));
            if(number <= 0)
                throw new IncorrectFileDataException("Player number should be positive.");

            String teamName = record.get(FileFormatConstants.PLAYER_TEAM_NAME_INDEX);
            if(teamToNumbersMap.containsKey(teamName)) {
                teamToNumbersMap.get(teamName).add(number);
            } else {
                Set<Integer> numberSet = new HashSet<>();
                numberSet.add(number);
                teamToNumbersMap.put(teamName, numberSet);
            }
        }

        if(teamToNumbersMap.size() != numberOfTeams)
            throw new IncorrectFileDataException("Incorrect number of teams for the given game type.");

        List<Integer> teamSizes = teamToNumbersMap.values().stream().map(Set::size).collect(Collectors.toList());
        int sumOfTeamSizes = teamSizes.stream().mapToInt(Integer::intValue).sum();
        if(sumOfTeamSizes != records.size())
            throw new IncorrectFileDataException("For every team, player numbers should be unique.");

        Set<Integer> teamSizesSet = new HashSet<>(teamSizes);
        if(teamSizesSet.size() != 1)
            throw new IncorrectFileDataException("Team sizes are not equal.");

    }

    protected abstract void validatePointFields(List<List<String>> records) throws IncorrectFileDataException;

    public int getRecordLength() {
        return recordLength;
    }

    public void setRecordLength(int recordLength) {
        this.recordLength = recordLength;
    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public void setNumberOfTeams(int numberOfTeams) {
        this.numberOfTeams = numberOfTeams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileValidator that = (FileValidator) o;
        return recordLength == that.recordLength && numberOfTeams == that.numberOfTeams;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordLength, numberOfTeams);
    }
}
