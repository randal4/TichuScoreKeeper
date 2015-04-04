package com.pulse.tichuscorekeeper.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Map;

/**
 * Created by puLse on 3/22/15.
 */
@Table(name = "Player")
public class Player extends Model implements Parcelable{


    @Column(unique = true, onUniqueConflict = Column.ConflictAction.IGNORE)
    public String name;

    public Player(){}

    public Player(String name){
        this.name = name;
    }

    public Player(Parcel in){
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Player createFromParcel(Parcel in) {
                    return new Player(in);
                }

                public Player[] newArray(int size) {
                    return new Player[size];
                }
            };
}
