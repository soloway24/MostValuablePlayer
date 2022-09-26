package model;

import handlers.implementations.BasketballGameHandler;
import handlers.implementations.HandballGameHandler;
import handlers.interfaces.GameHandler;

public enum GameType {

    BASKETBALL {
        @Override
        public GameHandler getGameHandler() {
            return new BasketballGameHandler();
        }
    } ,
    HANDBALL {
        @Override
        public GameHandler getGameHandler() {
            return new HandballGameHandler();
        }
    };

    public abstract GameHandler getGameHandler();



}
