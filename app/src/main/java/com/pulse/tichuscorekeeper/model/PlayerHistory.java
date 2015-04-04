package com.pulse.tichuscorekeeper.model;

import java.util.Map;

/**
 * Created by puLse on 3/22/15.
 */
public class PlayerHistory {
    private int id;
    private String name;
    private int rank;

    private int numberOfGames;
    private int numberOfHands;
    private int wins;
    private int losses;
    private int oneTwos;

    private int tichuCalls;
    private int tichuSuccess;
    private int tichuFail;

    private int grandTichuCalls;
    private int grandTichuSuccess;
    private int grandTichuFail;

    private int imperialTichuCalls;
    private int imperialTichuSuccess;
    private int imperialTichuFail;


    private Map<String, Double> partnerPlayPercentage;
    private Map<String, Double> partnerWinPercentage;
    private Map<String, Double> partnerLossPercentage;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public int getNumberOfHands() {
        return numberOfHands;
    }

    public void setNumberOfHands(int numberOfHands) {
        this.numberOfHands = numberOfHands;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getOneTwos() {
        return oneTwos;
    }

    public void setOneTwos(int oneTwos) {
        this.oneTwos = oneTwos;
    }

    public int getTichuCalls() {
        return tichuCalls;
    }

    public void setTichuCalls(int tichuCalls) {
        this.tichuCalls = tichuCalls;
    }

    public int getTichuSuccess() {
        return tichuSuccess;
    }

    public void setTichuSuccess(int tichuSuccess) {
        this.tichuSuccess = tichuSuccess;
    }

    public int getTichuFail() {
        return tichuFail;
    }

    public void setTichuFail(int tichuFail) {
        this.tichuFail = tichuFail;
    }

    public int getGrandTichuCalls() {
        return grandTichuCalls;
    }

    public void setGrandTichuCalls(int grandTichuCalls) {
        this.grandTichuCalls = grandTichuCalls;
    }

    public int getGrandTichuSuccess() {
        return grandTichuSuccess;
    }

    public void setGrandTichuSuccess(int grandTichuSuccess) {
        this.grandTichuSuccess = grandTichuSuccess;
    }

    public int getGrandTichuFail() {
        return grandTichuFail;
    }

    public void setGrandTichuFail(int grandTichuFail) {
        this.grandTichuFail = grandTichuFail;
    }

    public int getImperialTichuCalls() {
        return imperialTichuCalls;
    }

    public void setImperialTichuCalls(int imperialTichuCalls) {
        this.imperialTichuCalls = imperialTichuCalls;
    }

    public int getImperialTichuSuccess() {
        return imperialTichuSuccess;
    }

    public void setImperialTichuSuccess(int imperialTichuSuccess) {
        this.imperialTichuSuccess = imperialTichuSuccess;
    }

    public int getImperialTichuFail() {
        return imperialTichuFail;
    }

    public void setImperialTichuFail(int imperialTichuFail) {
        this.imperialTichuFail = imperialTichuFail;
    }

    public Map<String, Double> getPartnerPlayPercentage() {
        return partnerPlayPercentage;
    }

    public void setPartnerPlayPercentage(Map<String, Double> partnerPlayPercentage) {
        this.partnerPlayPercentage = partnerPlayPercentage;
    }

    public Map<String, Double> getPartnerWinPercentage() {
        return partnerWinPercentage;
    }

    public void setPartnerWinPercentage(Map<String, Double> partnerWinPercentage) {
        this.partnerWinPercentage = partnerWinPercentage;
    }

    public Map<String, Double> getPartnerLossPercentage() {
        return partnerLossPercentage;
    }

    public void setPartnerLossPercentage(Map<String, Double> partnerLossPercentage) {
        this.partnerLossPercentage = partnerLossPercentage;
    }
}
