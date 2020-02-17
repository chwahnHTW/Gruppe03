package kbe.gamemgmtTest;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;
import kbe.gamemgmt.GameInstanceService;
import kbe.gamemgmt.GameInstanceServiceImpl;
import kbe.playermgmt.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * In dieser Klasse werden alle Methoden aus dem GameInstance-Management getestet.
 */
public class GameInstanceServiceTest {

    private GameInstanceService service;

    @Before
    public void setUp() {
        service = new GameInstanceServiceImpl();
    }

    @Test
    public void testStartGame(){
        GameInstance gi = service.startGame();
        Assert.assertNotNull(gi);
    }

    @Test
    public void testCalculateGameStateFinished() {
        GameInstance gi = new GameInstance();
        List<Card> handsPlayer1 = new LinkedList<Card>();
        List<Card> handsPlayer2 = new LinkedList<Card>();
        Card card1 = new Card(Card.Zahl.DAMEN, Card.Symbol.HERZ);
        handsPlayer1.add(card1);
        Player player1 = new Player("Player1", handsPlayer1, null);
        player1.setHandCards(handsPlayer1);
        Player player2 = new Player("Player2", handsPlayer2, null);
        List<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        gi.setPlayers(players);
        System.out.println(gi.getPlayers().get(0).getHand().isEmpty());
        Assert.assertEquals(service.calculateGameState(gi) == "Finished", true);
    }

    @Test
    public void testCalculateGameStateRunning() {
        GameInstance gi = new GameInstance();
        List<Card> handsPlayer = new LinkedList<Card>();
        Card card = new Card(Card.Zahl.DAMEN, Card.Symbol.HERZ);
        handsPlayer.add(card);
        Player player1 = new Player("Player1", handsPlayer, null);
        Player player2 = new Player("Player2", handsPlayer, null);
        player1.setHandCards(handsPlayer);
        player2.setHandCards(handsPlayer);
        List<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        gi.setPlayers(players);
        System.out.println(gi.getPlayers().get(1).getHand());
        Assert.assertEquals(service.calculateGameState(gi) == "Running", true);
    }
}
