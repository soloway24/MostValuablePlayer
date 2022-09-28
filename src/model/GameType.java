package model;

import handlers.implementations.BasketballGameHandler;
import handlers.implementations.HandballGameHandler;
import handlers.interfaces.GameHandler;
import model.interfaces.HandleableGame;
import model.interfaces.ValidatableGame;
import validators.implementations.BasketballFileValidator;
import validators.implementations.HandBallFileValidator;
import validators.interfaces.FileValidator;

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
