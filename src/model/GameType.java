package model;

import handlers.implementations.BasketballHandler;
import handlers.implementations.HandballHandler;
import handlers.interfaces.GameHandler;

public enum GameType {

    BASKETBALL {
        @Override
        public GameHandler getGameHandler() {
            return new BasketballHandler();
        }
    } ,
    HANDBALL {
        @Override
        public GameHandler getGameHandler() {
            return new HandballHandler();
        }
    };

    public abstract GameHandler getGameHandler();

}
