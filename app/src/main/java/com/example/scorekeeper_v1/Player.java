package com.example.scorekeeper_v1;

import java.io.Serializable;
import java.util.Comparator;

public class Player implements Serializable {
    public String name;
    public String color;
    public String score;

    public Player() {

    }

    public Player(String name, String color, String score) {
        this.name = name;
        this.color = color;
        this.score = score;
    }

    public String getPlayerName() {
        return name;
    }

    public void setPlayerName(String name) {
        this.name = name;
    }

    public String getPlayerColor() {
        return color;
    }

    public void setPlayerColor(String color) {
        this.color = color;
    }

    public String getPlayerScore() {
        return score;
    }

    public void setPlayerScore(String score) {
        this.score = score;
    }

    public static Comparator<Player> sortByScore = new Comparator<Player>() {
        @Override
        public int compare(Player o1, Player o2) {
            return Integer.parseInt(o2.getPlayerScore()) - Integer.parseInt(o1.getPlayerScore());
        }
    };
}
