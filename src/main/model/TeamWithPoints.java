package main.model;

import java.util.Objects;

public class TeamWithPoints implements Comparable<TeamWithPoints>{

    private String teamName;
    private int points;

    public TeamWithPoints(String teamName, int points) {
        this.teamName = teamName;
        this.points = points;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamWithPoints that = (TeamWithPoints) o;
        return points == that.points && Objects.equals(teamName, that.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName, points);
    }

    @Override
    public String toString() {
        return "TeamWithPoints{" +
                "teamName='" + teamName + '\'' +
                ", points=" + points +
                '}';
    }

    @Override
    public int compareTo(TeamWithPoints o) {
        return Integer.compare(this.points, o.points);
    }
}
