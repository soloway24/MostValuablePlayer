package validators;

import model.GameType;
import validators.interfaces.FileFormatValidator;

import java.util.HashMap;
import java.util.Map;

public class ValidatorManager {

    private final Map<GameType, FileFormatValidator> gameTypeToValidatorMap;

    public ValidatorManager() {
        gameTypeToValidatorMap = new HashMap<>();
    }

    public FileFormatValidator getValidatorByGameType(GameType gameType) {
        if(gameTypeToValidatorMap.containsKey(gameType)) {
            return gameTypeToValidatorMap.get(gameType);
        } else {
            FileFormatValidator validator = gameType.getFileFormatValidator();
            gameTypeToValidatorMap.put(gameType, validator);
            return validator;
        }
    }

}
