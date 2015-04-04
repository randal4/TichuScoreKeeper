package com.pulse.tichuscorekeeper.manager;

import com.pulse.tichuscorekeeper.model.Hand;
import com.pulse.tichuscorekeeper.model.Team;
import com.pulse.tichuscorekeeper.model.TichuHand;

import java.util.List;

/**
 * Created by puLse on 3/22/15.
 */
public class HandManager {

    public int getTichuPoints(Hand hand, int player){
        int points = 0;

        Boolean tichu = hand.getTichus().get(player);
        if(tichu != null) {
            if (tichu) {
                points = 100;
            } else if (!tichu) {
                points = -100;
            }
        }

        return points;
    }

    public int getGrandTichuPoints(Hand hand, int player) {
        int points = 0;

        Boolean grandTichu = hand.getGrandTichus().get(player);
        if(grandTichu != null) {
            if (grandTichu) {
                points = 200;
            } else if (!grandTichu) {
                points = -200;
            }
        }

        return points;
    }

    public int getImperialTichuPoints(Hand hand, int player) {
        int points = 0;

        Boolean imperialTichu = hand.getImperialTichus().get(player);
        if(imperialTichu != null) {
            if (imperialTichu) {
                points = 400;
            } else if (!imperialTichu) {
                points = -400;
            }
        }

        return points;
    }

    public int getOneTwoBonusPoints(Hand hand, int team) {
        int points = 0;

        if(hand.getOneTwo().get(team)){
           points = 200;
        }

        return points;
    }

    public void saveHand(TichuHand hand) {
        hand.save();
    }
}
