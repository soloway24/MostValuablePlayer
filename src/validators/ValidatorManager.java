package validators;

import exceptions.IncorrectFileDataException;
import exceptions.IncorrectFileFormatException;
import model.GameType;
import model.SingleGameStats;
import validators.interfaces.FileValidator;

import java.util.HashMap;
import java.util.List;
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
    public void validateAll(List<SingleGameStats> games) throws IncorrectFileFormatException, IncorrectFileDataException {
        for (SingleGameStats game : games) {
            validate(game);
        }
    }

}
