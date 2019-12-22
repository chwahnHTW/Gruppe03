package gamemgmtTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;
import kbe.gamemgmt.GameInstanceService;
import kbe.gamemgmt.GameInstanceServiceImpl;
import kbe.playermgmt.Player;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
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
//
////    @Test
////    public void testDeterminePlayercount() {
////        service.determinePlayercount();
////        Assert.assertEquals(service.determinePlayercount(), 4);
////    }
//
//
//    @Test
//    public void testCreatePlayers() {
//        List players = service.createPlayers(4);
//        Assert.assertEquals(4, players.size());
//    }
//
//    @Test
//    public void testSelectCards() {
//        List<Card> testList = new LinkedList<Card>();
//        testList.add(new Card(Card.Zahl.ACHT, Card.Symbol.HERZ));
//        Player player = service.createPlayers(1).get(0);
//        player.setHand(testList);
//        List selectedCards = service.selectCards(player);
//        Assert.assertEquals(selectedCards, testList);
//    }
//
//    @Test
//    public void testPlayCards() {
//        List<Card> testList = new LinkedList<Card>();
//        testList.add(new Card(Card.Zahl.ACHT, Card.Symbol.HERZ));
//        Player player = new Player("Tom", 0, testList, null);
//        service.playCards(player, testList);
//        Assert.assertEquals(player.getHand().size(), 0);
//    }
//
//    @Test
//    public void testSwapCards() {
//        GameInstance gi = new GameInstance();
//        List<Player> players = gi.players;
//        List<Card> player0Cards = gi.players.get(0).getHand();
//        List<Card> player1Cards = gi.players.get(0).getHand();
//        service.swapCards(players);
//        Assert.assertEquals(player0Cards, gi.players.get(1).getHand());
//        Assert.assertEquals(player1Cards, gi.players.get(0).getHand());
//    }
//
//    @Test
//    public void testGetNextPlayer() {
//        GameInstance gi = new GameInstance();
//        List<Card> testList = new LinkedList<Card>();
//        Player player = new Player("Tom", 0, testList, null);
//        Player player2 = new Player("Grit", 1, testList, null);
//        gi.players.add(player);
//        gi.players.add(player2);
//        Player currentPlayer = gi.currentPlayer;
//        Player nextPlayer = service.getNextPlayer();
//        Assert.assertTrue(currentPlayer != nextPlayer);
//    }
//
    @Test
    public void testCalculateGameStateFinished() {
        GameInstance gi = new GameInstance();
        List<Card> handsPlayer1 = new LinkedList<Card>();
        List<Card> handsPlayer2 = new LinkedList<Card>();
        Card card1 = new Card(Card.Zahl.DAMEN, Card.Symbol.HERZ);
        handsPlayer1.add(card1);
        Player player1 = new Player("Tom", handsPlayer1, null);
        player1.setHandCards(handsPlayer1);
        Player player2 = new Player("Grit", handsPlayer2, null);
        List<Player> players = new ArrayList<Player> ();
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
        Player player1 = new Player("Tom", handsPlayer, null);
        Player player2 = new Player("Grit", handsPlayer, null);
        player1.setHandCards(handsPlayer);
        player2.setHandCards(handsPlayer);
        List<Player> players = new ArrayList<Player> ();
        players.add(player1);
        players.add(player2);
        gi.setPlayers(players);
        System.out.println(gi.getPlayers().get(1).getHand());
        Assert.assertEquals(service.calculateGameState(gi) == "Running", true);
    }
//
//    @Test
//    public void testCalculateInitialPlayer() {
//        GameInstance gi = new GameInstance();
//        gi.players = service.createPlayers(4);
//        Player initialPlayer = service.calculateInitialPlayer(gi);
//        Assert.assertEquals(initialPlayer, gi.getPlayers().get(0));
//    }
//
//    @Test
//    public void testGetPlayerMove() {
//        GameInstance gi = new GameInstance();
//        gi.players = service.createPlayers(1);
//        List cards = service.getPlayerMove(gi.players.get(0));
//        Assert.assertEquals(cards, null);
//    }
//
}
