import handlers.HandlerManager;
import handlers.interfaces.GameHandler;
import model.Player;
import model.SingleGameStats;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MVPCalculator {

    private List<Player> players;
    private HandlerManager handlerManager;

    public MVPCalculator(){
        players = new ArrayList<>();
        handlerManager = new HandlerManager();
    }

    public Player calculateMVP(List<SingleGameStats> games) {
        for(SingleGameStats game : games) {
            GameHandler handler = handlerManager.getHandlerByGameType(game.getGameType());
            SingleGameStats reviewedGame = handler.handleGame(game);
            addLatestRatingsToPlayers(reviewedGame);
        }
        return findPlayerWithMaxRating();
    }

    private Player findPlayerWithMaxRating() {
        return players.stream().max(new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return Integer.compare(o1.getRating(), o2.getRating());
            }
        }).orElseThrow();
    }

    private void addLatestRatingsToPlayers(SingleGameStats gameStats) {

    }

}
