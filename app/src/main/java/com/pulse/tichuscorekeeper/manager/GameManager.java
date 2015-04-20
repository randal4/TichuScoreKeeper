package com.pulse.tichuscorekeeper.manager;

import android.database.Cursor;
import android.database.DatabaseUtils;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Cache;
import com.activeandroid.query.Select;
import com.activeandroid.util.SQLiteUtils;
import com.pulse.tichuscorekeeper.exception.InvalidHandException;
import com.pulse.tichuscorekeeper.model.Game;
import com.pulse.tichuscorekeeper.model.Hand;
import com.pulse.tichuscorekeeper.model.Player;
import com.pulse.tichuscorekeeper.model.Team;
import com.pulse.tichuscorekeeper.model.TichuHand;

import java.util.List;

/**
 * Created by puLse on 3/22/15.
 */
public class GameManager {

    public int getMaxGame(){
        int max = 0;
        Cursor cursor = Cache.openDatabase().rawQuery("Select max(game) as maxGame from hands", null);
        if(cursor.moveToFirst()) {
            max = cursor.getInt(cursor.getColumnIndex("maxGame"));
        }

        return max;
    }

    public List<TichuHand> getTichuHandsForGame(int gameId){
        List<TichuHand> hands = new Select().from(TichuHand.class).where("game = ?", gameId).execute();

        return hands;
    }
/*    private HandManager handManager;
    private PlayerManager playerManager;

    public GameManager(){
        handManager = new HandManager();
        playerManager = new PlayerManager();
    }

    public GameManager(HandManager handManager, PlayerManager playerManager){
        this.handManager = handManager;
        this.playerManager = playerManager;
    }

    public void endHand(Game game, Hand hand) throws InvalidHandException{
        if(hand.isValid()) {
            insertHandInGame(game, hand);
            calculateTeamScoresForHand(hand, game.getT1(), game.getT2());
            calculateHandStats(game, hand);
        }
    }

    public void endGame(Game game){
        calculateGameStats(game);
    }

    private void calculateGameStats(Game game) {
        Team t1 = game.getT1();
        Player p1 = t1.getP1();
        Player p2 = t1.getP2();

        Team t2 = game.getT2();
        Player p3 = t2.getP1();
        Player p4 = t2.getP2();

        p1.setNumberOfGames(p1.getNumberOfGames() + 1);
        p2.setNumberOfGames(p2.getNumberOfGames() + 1);
        p3.setNumberOfGames(p3.getNumberOfGames() + 1);
        p4.setNumberOfGames(p4.getNumberOfGames() + 1);
    }

    private void calculateHandStats(Game game, Hand hand) {
        Team t1 = game.getT1();
        Player p1 = t1.getP1();
        Player p2 = t1.getP2();

        Team t2 = game.getT2();
        Player p3 = t2.getP1();
        Player p4 = t2.getP2();

        calculateTichuStats(hand, p1, 0);
        calculateTichuStats(hand, p2, 1);
        calculateTichuStats(hand, p3, 2);
        calculateTichuStats(hand, p4, 3);

        p1.setNumberOfHands(p1.getNumberOfHands() + 1);
        p2.setNumberOfHands(p2.getNumberOfHands() + 1);
        p3.setNumberOfHands(p3.getNumberOfHands() + 1);
        p4.setNumberOfHands(p4.getNumberOfHands() + 1);
    }

    private void calculateTichuStats(Hand hand, Player player, int playerIndex) {
        List<Boolean> tichus = hand.getTichus();
        List<Boolean> grandTichus = hand.getGrandTichus();
        List<Boolean> imperialTichus = hand.getImperialTichus();

        if(tichus.get(playerIndex) != null){
            player.setTichuCalls(player.getTichuCalls() + 1);

            if(tichus.get(playerIndex)){
                player.setTichuSuccess(player.getTichuSuccess() + 1);
            }else{
                player.setTichuFail(player.getTichuFail() + 1);
            }
        }else if(grandTichus.get(playerIndex) != null){
            player.setGrandTichuCalls(player.getGrandTichuCalls() + 1);

            if(grandTichus.get(playerIndex)){
                player.setGrandTichuSuccess(player.getGrandTichuSuccess() + 1);
            }else{
                player.setGrandTichuFail(player.getGrandTichuFail() + 1);
            }
        }else if(imperialTichus.get(playerIndex) != null){
            player.setImperialTichuCalls(player.getImperialTichuCalls() + 1);

            if(grandTichus.get(playerIndex)){
                player.setImperialTichuSuccess(player.getImperialTichuSuccess() + 1);
            }else{
                player.setImperialTichuFail(player.getImperialTichuFail() + 1);
            }
        }
    }

    private void calculateTeamScoresForHand(Hand hand, Team t1, Team t2) {
        t1.addScore(hand.getHandScoreTeam1());
        t2.addScore(hand.getHandScoreTeam2());
    }

    private void insertHandInGame(Game game, Hand hand) {
        game.getHands().add(hand.getHandNumber(), hand);
    }

    public void saveGame(Game game){

    }

    public void deleteHand(Game game, int i) {
        Hand hand = game.getHands().get(i);
        game.getHands().remove(i);
        //game.getT1().deductScore(hand.getScoreTeam1());
        //game.getT2().deductScore(hand.getScoreTeam2());
    }

    public void deleteLastHand(Game game) {
        deleteHand(game, game.getHands().size()-1);
    }
    */
}
