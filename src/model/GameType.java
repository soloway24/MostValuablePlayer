package model;

import handlers.implementations.BasketballGameHandler;
import handlers.implementations.HandballGameHandler;
import handlers.interfaces.GameHandler;
import model.interfaces.HandleableGame;
import model.interfaces.ValidatableGame;
import validators.implementations.BasketballFileFormatValidator;
import validators.implementations.HandBallFileFormatValidator;
import validators.interfaces.FileFormatValidator;

public enum GameType implements HandleableGame, ValidatableGame {

    BASKETBALL {
        @Override
        public GameHandler getGameHandler() {
            return new BasketballGameHandler();
        }

        @Override
        public FileFormatValidator getFileFormatValidator() {
            return new BasketballFileFormatValidator();
        }
    } ,
    HANDBALL {
        @Override
        public GameHandler getGameHandler() {
            return new HandballGameHandler();
        }

        @Override
        public FileFormatValidator getFileFormatValidator() {
            return new HandBallFileFormatValidator();
        }
    };





}
