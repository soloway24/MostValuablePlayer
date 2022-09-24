import handlers.interfaces.GameHandler;
import model.GameType;
import model.Player;
import model.SingleGameStats;

import java.util.ArrayList;
import java.util.List;

public class CalculatorMVP {

    private List<Player> players;

    public CalculatorMVP(){
        players = new ArrayList<>();
    }

    public Player calculateMVP(List<SingleGameStats> games) {
        for(SingleGameStats game : games) {
            List<GameHandler> handlers = new ArrayList<>();

        }
        return null;
    }

    private void addLatestRatingsToPlayers(SingleGameStats gameStats) {

    }

}
