package main.model;

import main.handlers.implementations.BasketballGameHandler;
import main.handlers.implementations.HandballGameHandler;
import main.handlers.interfaces.GameHandler;
import main.model.interfaces.HandleableGame;
import main.model.interfaces.ValidatableGame;
import main.validators.implementations.BasketballFileValidator;
import main.validators.implementations.HandBallFileValidator;
import main.validators.interfaces.FileValidator;

public enum GameType implements HandleableGame, ValidatableGame {

    BASKETBALL {
        @Override
        public GameHandler getGameHandler() {
            return new BasketballGameHandler();
        }

        @Override
        public FileValidator getFileFormatValidator() {
            return new BasketballFileValidator();
        }
    } ,
    HANDBALL {
        @Override
        public GameHandler getGameHandler() {
            return new HandballGameHandler();
        }

        @Override
        public FileValidator getFileFormatValidator() {
            return new HandBallFileValidator();
        }
    }





}
