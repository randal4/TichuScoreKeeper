package com.pulse.tichuscorekeeper.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by puLse on 3/30/15.
 */
@Table(name="Hands")
public class TichuHand extends Model implements Parcelable{

    public TichuHand(){  }

    public TichuHand(Parcel in){
        player = (Player) in.readParcelable(Player.class.getClassLoader());
        partner = (Player) in.readParcelable(Player.class.getClassLoader());
        score = in.readInt();
        oneTwo = (Boolean) in.readValue(null);
        oneTwoAgainst = (Boolean) in.readValue(null);
        tichu = (Boolean) in.readValue(null);
        grandTichu = (Boolean) in.readValue(null);
        imperialTichu = (Boolean) in.readValue(null);
        game = in.readInt();
    }

    @Column
    public Player player;

    @Column
    public Player partner;

    @Column
    public int score;

    @Column
    public Boolean oneTwo;

    @Column
    public Boolean oneTwoAgainst;

    @Column
    public Boolean tichu;

    @Column
    public Boolean grandTichu;

    @Column
    public Boolean imperialTichu;

    @Column
    public int game;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(player, flags);
        dest.writeParcelable(partner, flags);
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
}
