package com.pulse.tichuscorekeeper.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.google.gson.annotations.Expose;

/**
 * Created by puLse on 3/22/15.
 */
@Table(name = "Player")
public class Player extends Model {

    @Expose
    @Column(unique = true, onUniqueConflict = Column.ConflictAction.IGNORE)
    public String name;

    public Player(){}

    public Player(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
