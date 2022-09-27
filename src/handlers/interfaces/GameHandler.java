package handlers.interfaces;

import utils.GameRulesConstants;
import model.SingleGameStats;

import java.util.List;
import java.util.Map;

public abstract class GameHandler {

    protected Map<Integer, Integer> indexToMultiplierMap;

    public GameHandler(){
        createAndFillIndexToMultiplierMap();
    }

    protected abstract void createAndFillIndexToMultiplierMap();

    public SingleGameStats handleGame(SingleGameStats stats) {
        // TO DO
        // use map to calculate added points

        for(List<String> record : stats.getPlayerStats()) {
            int rating = 0;
            for(Map.Entry<Integer, Integer> entry : indexToMultiplierMap.entrySet()) {
                int index = entry.getKey();
                int points = Integer.parseInt(record.get(index));
                int multiplier = entry.getValue();
                rating += points * multiplier;
            }
            System.out.println(rating);

        }


        // add points to players in List<Player> players in SingleGameStats
        return stats;
    }

    public int getPointsForWin() {
        return GameRulesConstants.STANDARD_WIN_POINTS;
    }

}
