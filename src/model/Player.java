package model;

import java.util.Objects;

public class Player {

    private String nickname;
    private String name;
    private int rating;

    public Player(String nickname, String name) {
        this.nickname = nickname;
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return rating == player.rating && nickname.equals(player.nickname) && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, name, rating);
    }

    @Override
    public String toString() {
        return "Player{" +
                "nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}
