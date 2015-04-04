package com.pulse.tichuscorekeeper.manager;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.pulse.tichuscorekeeper.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by puLse on 3/22/15.
 */
public class PlayerManager {

    public List<Player> getAllPlayers(){
        return new Select()
                .distinct()
                .from(Player.class)
                .execute();
    }

    public void savePlayer(Player player){
        player.save();
    }

}
