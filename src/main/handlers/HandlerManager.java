package main.handlers;

import main.exceptions.IncorrectFileDataException;
import main.handlers.abstractions.GameHandler;
import main.model.GameType;
import main.model.SingleGameStats;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class HandlerManager {

    private final Map<GameType, GameHandler> gameTypeToHandlerMap;

    public HandlerManager() {
        gameTypeToHandlerMap = new HashMap<>();
    }

    private GameHandler getHandlerByGameType(GameType gameType) {
        if(gameTypeToHandlerMap.containsKey(gameType)) {
            return gameTypeToHandlerMap.get(gameType);
        } else {
            GameHandler handler = gameType.getGameHandler();
            gameTypeToHandlerMap.put(gameType, handler);
            return handler;
        }
    }

    public void handleGame(SingleGameStats game) throws IncorrectFileDataException {
        GameHandler handler = getHandlerByGameType(game.getGameType());
        handler.handleGame(game);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HandlerManager that = (HandlerManager) o;
        return Objects.equals(gameTypeToHandlerMap, that.gameTypeToHandlerMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameTypeToHandlerMap);
    }
}
