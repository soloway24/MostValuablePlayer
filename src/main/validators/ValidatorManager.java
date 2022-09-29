package main.validators;

import main.exceptions.IncorrectFileDataException;
import main.exceptions.IncorrectFileFormatException;
import main.model.GameType;
import main.model.SingleGameStats;
import main.validators.interfaces.FileValidator;

import java.util.HashMap;
import java.util.Map;

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

}
