package main.handlers.abstractions;

import main.exceptions.IncorrectFileDataException;
import main.model.Player;
import main.model.TeamWithPoints;
import main.utils.FileFormatConstants;
import main.utils.GameRulesConstants;
import main.model.SingleGameStats;
import main.utils.UtilMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class GameHandler {

    protected Map<Integer, Integer> indexToMultiplierMap;

    public GameHandler(){
        createAndFillIndexToMultiplierMap();
    }

    protected abstract void createAndFillIndexToMultiplierMap();

    public void handleGame(SingleGameStats game) throws IncorrectFileDataException {
        String winnerTeam = getWinnerTeamName(game);

        List<Player> gamePlayers = new ArrayList<>();
        for(List<String> record : game.getPlayerStats()) {

            int rating = 0;
            for(Map.Entry<Integer, Integer> entry : indexToMultiplierMap.entrySet()) {
                int index = entry.getKey();
                int points = UtilMethods.tryParseInt(record.get(index));
                int multiplier = entry.getValue();
                rating += points * multiplier;
            }

            String playerTeam = record.get(FileFormatConstants.PLAYER_TEAM_NAME_INDEX);
            if(playerTeam.equals(winnerTeam))
                rating += getPointsForWin();


            String nickname = record.get(FileFormatConstants.PLAYER_NICKNAME_INDEX);
            String playerName = record.get(FileFormatConstants.PLAYER_NAME_INDEX);

            System.out.println(nickname + " - " + rating);
            Player player = new Player(nickname, playerName, rating);
            gamePlayers.add(player);
        }
        System.out.println();
        game.setPlayersWithRatings(gamePlayers);
    }

    protected abstract Map<String, Integer> getTeamToPointsMap(SingleGameStats game) throws IncorrectFileDataException;

    protected String getWinnerTeamName(SingleGameStats game) throws IncorrectFileDataException {
        Map<String, Integer> teamToPointsMap = getTeamToPointsMap(game);
        List<TeamWithPoints> teams = new ArrayList<>();
        for(Map.Entry<String, Integer> entry : teamToPointsMap.entrySet()) {
            TeamWithPoints teamWithPoints = new TeamWithPoints(entry.getKey(), entry.getValue());
            teams.add(teamWithPoints);
        }
        TeamWithPoints maxPointsTeam = teams.stream().max(TeamWithPoints::compareTo).orElseThrow();
        TeamWithPoints minPointsTeam = teams.stream().min(TeamWithPoints::compareTo).orElseThrow();
        if (maxPointsTeam.getPoints() == minPointsTeam.getPoints())
            throw new IncorrectFileDataException("Game does not have a winner.");
        return maxPointsTeam.getTeamName();
    }

    protected int getPointsForWin() {
        return GameRulesConstants.STANDARD_WIN_POINTS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameHandler that = (GameHandler) o;
        return Objects.equals(indexToMultiplierMap, that.indexToMultiplierMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexToMultiplierMap);
    }
}
