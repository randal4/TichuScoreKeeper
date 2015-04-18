package com.pulse.tichuscorekeeper.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.ExclusionStrategy;
import com.google.gson.annotations.Expose;

/**
 * Created by puLse on 3/30/15.
 */
@Table(name="Hands")
public class TichuHand extends Model{ //implements Parcelable{

    public TichuHand(){ super(); }

    /*public TichuHand(Parcel in){
        player = in.readString();
        partner = in.readString();
        score = in.readInt();
        oneTwo = (Boolean) in.readValue(null);
        oneTwoAgainst = (Boolean) in.readValue(null);
        tichu = (Boolean) in.readValue(null);
        grandTichu = (Boolean) in.readValue(null);
        imperialTichu = (Boolean) in.readValue(null);
        game = in.readInt();
    }*/

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

    /*
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(player);
        dest.writeString(partner);
        dest.writeInt(score);
        dest.writeValue(oneTwo);
        dest.writeValue(oneTwoAgainst);
        dest.writeValue(tichu);
        dest.writeValue(grandTichu);
        dest.writeValue(imperialTichu);
        dest.writeInt(game);
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public TichuHand createFromParcel(Parcel in) {
                    return new TichuHand(in);
                }

                public TichuHand[] newArray(int size) {
                    return new TichuHand[size];
                }
    };
    */
}
