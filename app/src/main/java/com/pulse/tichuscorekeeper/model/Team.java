package com.pulse.tichuscorekeeper.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by puLse on 3/22/15.
 */
public class Team {
    private Player p1;
    private Player p2;

    private List<Player> players;

    private int score;

    public Team(Player p1, Player p2){
        this.p1 = p1;
        this.p2 = p2;

        players = new ArrayList<Player>();

        players.add(p1);
        players.add(p2);
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public void deductScore(int score) {
        this.score -= score;
    }
}
