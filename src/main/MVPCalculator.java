package main;

import main.exceptions.IncorrectFileDataException;
import main.handlers.HandlerManager;
import main.handlers.interfaces.GameHandler;
import main.model.Player;
import main.model.SingleGameStats;

import java.util.*;
import java.util.stream.Collectors;

public class MVPCalculator {

    private Map<String, Player> nicknamesToPlayersMap;
    private HandlerManager handlerManager;

    public MVPCalculator(){
        nicknamesToPlayersMap = new HashMap<>();
        handlerManager = new HandlerManager();
    }

    public Player calculateMVP(List<SingleGameStats> games) throws IncorrectFileDataException {
        for(SingleGameStats game : games) {
            GameHandler handler = handlerManager.getHandlerByGameType(game.getGameType());
            SingleGameStats reviewedGame = handler.handleGame(game);
            addLatestRatingsToPlayers(reviewedGame);
        }
        System.out.println(nicknamesToPlayersMap.values().stream().sorted().collect(Collectors.toList()));
        return findPlayerWithMaxRating();
    }

    private Player findPlayerWithMaxRating() {
        return nicknamesToPlayersMap.values().stream().max(Player::compareTo).orElseThrow();
    }

    private void addLatestRatingsToPlayers(SingleGameStats gameStats) throws IncorrectFileDataException {
        for(Player player : gameStats.getPlayersWithRatings()) {
            if(nicknamesToPlayersMap.containsKey(player.getNickname())) {
                Player playerInMap = nicknamesToPlayersMap.get(player.getNickname());
                if(!playerInMap.getName().equals(player.getName()))
                    throw new IncorrectFileDataException("Two or more players have the same nickname.");
                playerInMap.setRating(playerInMap.getRating() + player.getRating());
            } else {
                nicknamesToPlayersMap.put(player.getNickname(), player);
            }
        }
    }

    public Map<String, Player> getNicknamesToPlayersMap() {
        return nicknamesToPlayersMap;
    }

    public void setNicknamesToPlayersMap(Map<String, Player> nicknamesToPlayersMap) {
        this.nicknamesToPlayersMap = nicknamesToPlayersMap;
    }

    public HandlerManager getHandlerManager() {
        return handlerManager;
    }

    public void setHandlerManager(HandlerManager handlerManager) {
        this.handlerManager = handlerManager;
    }
}
