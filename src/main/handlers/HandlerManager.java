package main.handlers;

import main.handlers.interfaces.GameHandler;
import main.model.GameType;

import java.util.HashMap;
import java.util.Map;

public class HandlerManager {

    private final Map<GameType, GameHandler> gameTypeToHandlerMap;

    public HandlerManager() {
        gameTypeToHandlerMap = new HashMap<>();
    }

    public GameHandler getHandlerByGameType(GameType gameType) {
        if(gameTypeToHandlerMap.containsKey(gameType)) {
            return gameTypeToHandlerMap.get(gameType);
        } else {
            GameHandler handler = gameType.getGameHandler();
            gameTypeToHandlerMap.put(gameType, handler);
            return handler;
        }
    }

}
