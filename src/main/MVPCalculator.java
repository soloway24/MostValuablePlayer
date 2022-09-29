package main;

import main.exceptions.IncorrectFileDataException;
import main.handlers.HandlerManager;
import main.model.Player;
import main.model.SingleGameStats;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
            handlerManager.handleGame(game);
            addLatestRatingsToPlayers(game);
        }
        System.out.println(nicknamesToPlayersMap.values().stream().sorted().collect(Collectors.toList()));
        return findPlayerWithMaxRating();
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

    private Player findPlayerWithMaxRating() {
        return nicknamesToPlayersMap.values().stream().max(Player::compareTo).orElseThrow();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MVPCalculator that = (MVPCalculator) o;
        return Objects.equals(nicknamesToPlayersMap, that.nicknamesToPlayersMap) && Objects.equals(handlerManager, that.handlerManager);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nicknamesToPlayersMap, handlerManager);
    }
}
