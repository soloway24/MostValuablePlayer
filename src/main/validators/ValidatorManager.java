package main.validators;

import main.exceptions.IncorrectFileDataException;
import main.exceptions.IncorrectFileFormatException;
import main.model.GameType;
import main.model.SingleGameStats;
import main.validators.abstractions.FileValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ValidatorManager {

    private final Map<GameType, FileValidator> gameTypeToValidatorMap;

    public ValidatorManager() {
        gameTypeToValidatorMap = new HashMap<>();
    }

    private FileValidator getValidatorByGameType(GameType gameType) {
        if(gameTypeToValidatorMap.containsKey(gameType)) {
            return gameTypeToValidatorMap.get(gameType);
        } else {
            FileValidator validator = gameType.getFileFormatValidator();
            gameTypeToValidatorMap.put(gameType, validator);
            return validator;
        }
    }

    public void validate(SingleGameStats game) throws IncorrectFileFormatException, IncorrectFileDataException {
        GameType gameType = game.getGameType();
        FileValidator validator = getValidatorByGameType(gameType);
        validator.validate(game.getPlayerStats());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidatorManager that = (ValidatorManager) o;
        return Objects.equals(gameTypeToValidatorMap, that.gameTypeToValidatorMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameTypeToValidatorMap);
    }
}
