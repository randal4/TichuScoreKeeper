package com.pulse.tichuscorekeeper.manager;

import com.pulse.tichuscorekeeper.exception.InvalidHandException;
import com.pulse.tichuscorekeeper.model.Game;
import com.pulse.tichuscorekeeper.model.Hand;
import com.pulse.tichuscorekeeper.model.Player;
import com.pulse.tichuscorekeeper.model.Team;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;

import static org.junit.Assert.*;

public class GameManagerTest {
/*

    GameManager gm = new GameManager();
    Game game;
    List<Player> players;
    List<Team> teams;

    @Before
    public void setUp() throws Exception {
        players = new ArrayList(4);

        for(int x = 0; x<4; x++){
            players.add(x, new Player("Player " + x));
        }

        teams = new ArrayList(2);
        teams.add(0, new Team(players.get(0),players.get(1)));
        teams.add(1, new Team(players.get(2),players.get(3)));

        game = new Game(teams.get(0), teams.get(1));
    }

    @After
    public void tearDown() throws Exception {

    }

    //fail when points < 100
    @Test(expected = InvalidHandException.class)
    public void testLessThan100PointsPerHand() throws InvalidHandException {
        Hand hand = new Hand(0);
        hand.setScoreTeam1(10);
        hand.setScoreTeam2(10);

        gm.endHand(game, hand);
    }

    //fail when points > 100
    @Test(expected = InvalidHandException.class)
    public void testMoreThan100PointsPerHand() throws InvalidHandException {
        Hand hand = new Hand(1);

        hand.setScoreTeam1(100);
        hand.setScoreTeam2(100);

        gm.endHand(game, hand);
    }

    @Test
    public void finishHand() throws InvalidHandException {
        Hand hand = new Hand(0);
        hand.setScoreTeam1(50);
        hand.setScoreTeam2(50);

        gm.endHand(game, hand);
    }

    @Test
    public void deleteLastHand() throws InvalidHandException {
        Hand hand1 = new Hand(0);
        hand1.setScoreTeam1(50);
        hand1.setScoreTeam2(50);

        gm.endHand(game, hand1);

        Hand hand2 = new Hand(1);
        hand2.setScoreTeam1(25);
        hand2.setScoreTeam2(75);
        hand2.setHandNumber(1);

        gm.endHand(game, hand2);

        assertEquals(game.getHands().size(), 2);

        gm.deleteLastHand(game);

        assertEquals(game.getHands().size(), 1);
        assertEquals(game.getNumberOfHands(),1);
        assertEquals(game.getT1().getScore(), 50);
        assertEquals(game.getT2().getScore(), 50);
    }

    @Test
    public void deleteSpecificHand() throws InvalidHandException {
        Hand hand1 = new Hand(0);
        hand1.setScoreTeam1(50);
        hand1.setScoreTeam2(50);

        gm.endHand(game, hand1);

        Hand hand2 = new Hand(1);
        hand2.setScoreTeam1(25);
        hand2.setScoreTeam2(75);

        gm.endHand(game, hand2);

        assertEquals(game.getHands().size(), 2);

        gm.deleteHand(game, 1);

        assertEquals(game.getHands().size(), 1);
        assertEquals(game.getNumberOfHands(),1);
        assertEquals(game.getT1().getScore(), 25);
        assertEquals(game.getT2().getScore(), 75);
    }

    @Test
    public void testMaxPlayersOnTeam(){

    }

    @Test
    public void testMinPlayersOnTeam(){

    }

    @Test(expected = InvalidHandException.class)
    public void oneTichuAllowed() throws InvalidHandException {
        Hand hand = new Hand();
        hand.getTichus().add(0, true);
        hand.getGrandTichus().add(0, true);

        gm.endHand(game, hand);
    }

    @Test
    public void noTichuAllowed(){

    }

    @Test
    public void testTotalPointsSum(){

    }
*/

}