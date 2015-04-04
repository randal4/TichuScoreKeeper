package com.pulse.tichuscorekeeper.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by puLse on 3/22/15.
 */
public class Game {
    private Team t1;
    private Team t2;

    private List<Team> teams;

    private List<Hand> hands;

    private int finalScoreTeam1;
    private int finalScoreTeam2;

    public Game(Team t1, Team t2) {
        this.t1 = t1;
        this.t2 = t2;

        teams = new ArrayList<>(2);
        hands = new ArrayList<>();
    }

    public Team getT1() {
        return t1;
    }

    public void setT1(Team t1) {
        this.t1 = t1;
    }

    public Team getT2() {
        return t2;
    }

    public void setT2(Team t2) {
        this.t2 = t2;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public void setHands(List<Hand> hands) {
        this.hands = hands;
    }

    public int getNumberOfHands(){
        return hands.size();
    }


}
