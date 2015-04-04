package com.pulse.tichuscorekeeper.model;

import com.pulse.tichuscorekeeper.exception.InvalidHandException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by puLse on 3/22/15.
 */
public class Hand {
    private int handNumber;

    private int handScoreTeam1;
    private int handScoreTeam2;

    private int pointsTeam1;
    private int pointsTeam2;

    private List<Boolean> oneTwo = new ArrayList<>(2);

    private List<Boolean> tichus = new ArrayList<>(4);
    private List<Boolean> grandTichus = new ArrayList<>(4);
    private List<Boolean> imperialTichus = new ArrayList<>(4);

    public Hand(){
        this(0);
    }

    public Hand(int handNumber){
        this.handNumber = handNumber;
        initializeListToNull(oneTwo);
        initializeListToNull(tichus);
        initializeListToNull(grandTichus);
        initializeListToNull(imperialTichus);
    }

    private void initializeListToNull(List<Boolean> list) {
        for(int x = 0; x<4; x++){
            list.add(x, null);
        }
    }

    public int getHandNumber(){ return handNumber; }

    public void setHandNumber(int handNumber){ this.handNumber = handNumber; }

    public int getHandScoreTeam1() {
        return handScoreTeam1;
    }

    public void setHandScoreTeam1(int handScoreTeam1) {
        this.handScoreTeam1 = handScoreTeam1;
    }

    public int getHandScoreTeam2() {
        return handScoreTeam2;
    }

    public void setHandScoreTeam2(int handScoreTeam2) {
        this.handScoreTeam2 = handScoreTeam2;
    }

    public int getPointsTeam1() {
        return pointsTeam1;
    }

    public void setPointsTeam1(int pointsTeam1) {
        this.pointsTeam1 = pointsTeam1;
    }

    public int getPointsTeam2() {
        return pointsTeam2;
    }

    public void setPointsTeam2(int pointsTeam2) {
        this.pointsTeam2 = pointsTeam2;
    }

    public List<Boolean> getOneTwo() {
        return oneTwo;
    }

    public void setOneTwo(List<Boolean> oneTwo) {
        this.oneTwo = oneTwo;
    }

    public List<Boolean> getTichus() {
        return tichus;
    }

    public void setTichus(List<Boolean> tichus) {
        this.tichus = tichus;
    }

    public List<Boolean> getGrandTichus() {
        return grandTichus;
    }

    public void setGrandTichus(List<Boolean> grandTichus) {
        this.grandTichus = grandTichus;
    }

    public List<Boolean> getImperialTichus() {
        return imperialTichus;
    }

    public void setImperialTichus(List<Boolean> imperialTichus) {
        this.imperialTichus = imperialTichus;
    }


    public boolean isValid() throws InvalidHandException{
        if(pointsTeam1 + pointsTeam2 != 100 && oneTwo.isEmpty()){
            throw new InvalidHandException("Points must equal 100");
        } else if (oneTwo.get(0) == true && oneTwo.get(1) == true){
            throw new InvalidHandException("Only one team can go one-two");
        } else if (
                   (tichus.get(0) != null && (grandTichus.get(0) != null || imperialTichus.get(0) != null))
                || (tichus.get(1) != null && (grandTichus.get(1) != null || imperialTichus.get(1) != null))
                || (tichus.get(2) != null && (grandTichus.get(2) != null || imperialTichus.get(2) != null))
                || (tichus.get(3) != null && (grandTichus.get(3) != null || imperialTichus.get(3) != null))

                || ((tichus.get(0) != null || grandTichus.get(0) != null) && imperialTichus.get(0) != null)
                || ((tichus.get(1) != null || grandTichus.get(1) != null) && imperialTichus.get(1) != null)
                || ((tichus.get(2) != null || grandTichus.get(2) != null) && imperialTichus.get(2) != null)
                || ((tichus.get(3) != null || grandTichus.get(3) != null) && imperialTichus.get(3) != null)

                || ((tichus.get(0) != null || imperialTichus.get(0) != null) && grandTichus.get(0) != null)
                || ((tichus.get(1) != null || imperialTichus.get(1) != null) && grandTichus.get(1) != null)
                || ((tichus.get(2) != null || imperialTichus.get(2) != null) && grandTichus.get(2) != null)
                || ((tichus.get(3) != null || imperialTichus.get(3) != null) && grandTichus.get(3) != null)
                ){
            throw new InvalidHandException("Only one tichu per player allowed");
        }

        return true;
    }
}
