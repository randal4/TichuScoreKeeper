package com.pulse.tichuscorekeeper.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

/**
 * Created by puLse on 3/30/15.
 */
@Table(name="Hands")
public class TichuHand extends Model{ //implements Parcelable{

    public TichuHand(){ super(); }

    @Expose
    @Column
    public String player;

    @Expose
    @Column
    public String partner;

    @Expose
    @Column
    public int score;

    @Expose
    @Column
    public Boolean oneTwo;

    @Expose
    @Column
    public Boolean oneTwoAgainst;

    @Expose
    @Column
    public Boolean tichu;

    @Expose
    @Column
    public Boolean grandTichu;

    @Expose
    @Column
    public Boolean imperialTichu;

    @Expose
    @Column
    public int game;

    @Expose
    @Column
    public int hand;
}
