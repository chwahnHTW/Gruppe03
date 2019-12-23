package playermgmtTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import kbe.playermgmt.PlayerService;
import kbe.playermgmt.PlayerServiceImpl;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * In dieser Klasse werden alle Methoden aus dem Player-Management getestet.
 */
public class PlayerServiceTest {


    private PlayerService service;

    @Before
    public void setUp() {
        service = new PlayerServiceImpl();
    }

//    @Test
//    public void testGenerateId() {
//        int x = (int) ((Math.random() * ((1000000 - 1) + 1)) + 1);
//        System.out.println(x);
//        Assert.assertEquals(x > 0, true);
//    }
    
    @Test
    public void testHasCardsFalse() {
        Player player = new Player("Kaya", null, null);
        Assert.assertEquals(service.hasCards(player), false);
    }

    @Test
    public void testHasCardsTrue() {
        Player player = new Player("Kaya", null, null);
        Card card1 = new Card(Card.Zahl.SIEBEN, Card.Symbol.KARO);
        player.setHand(card1);
        Assert.assertEquals(service.hasCards(player), true);
    }
    
    @Test
    public void testRemoveFromHand() {
    	Card card1 = new Card(Card.Zahl.SIEBEN, Card.Symbol.KARO);
    	Card card2 = new Card(Card.Zahl.ACHT, Card.Symbol.HERZ);
    	List<Card> handCards = new ArrayList<Card>();
    	handCards.add(card1);
    	handCards.add(card2);
    	System.out.println(handCards);
    	Player player = new Player("TestPlayer", handCards, null);
    	
    	service.removeFromHand(player, handCards);
    	System.out.println(player.getHand());
    	Assert.assertTrue(player.getHand().isEmpty());
    }

    @Test
    public void testAddToHand() {
    	Card card1 = new Card(Card.Zahl.SIEBEN, Card.Symbol.KARO);
    	Card card2 = new Card(Card.Zahl.ACHT, Card.Symbol.HERZ);
    	List<Card> handCards = new ArrayList<Card>();
    	handCards.add(card1);
    	handCards.add(card2);
        Player player = new Player("Kaya", handCards, null);

        Card card3 = new Card(Card.Zahl.NEUN, Card.Symbol.PIK);
        Card card4 = new Card(Card.Zahl.ZEHN, Card.Symbol.KREUZ);
        List<Card> cardsToAdd = new ArrayList<Card>();
        cardsToAdd.add(card3);
        cardsToAdd.add(card4);
//        System.out.println(cardsToAdd);
//        System.out.println(handCards);
        
        service.addToHand(player, cardsToAdd);
//        System.out.println(player.getHand());
//        Assert.assertTrue(player.getHand().size() == cards.size() + player.getHand().size() - cards.size());

        Assert.assertEquals(cardsToAdd.get(0), player.handCards.get(0));
        Assert.assertEquals(cardsToAdd.get(1), player.handCards.get(1));
    }
    
    @Test
    public void testGetNextPlayer() {
    	System.out.println("**************** TEST GET NEXT PLAYER ****************");
    	GameInstance gi = new GameInstance();
    	Card card1 = new Card(Card.Zahl.SIEBEN, Card.Symbol.HERZ);
    	Card card2 = new Card(Card.Zahl.ACHT, Card.Symbol.PIK);
    	Card card3 = new Card(Card.Zahl.SIEBEN, Card.Symbol.KREUZ);
    	List<Card> handCardsEins = new ArrayList<Card>();
    	List<Card> handCardsZwei = new ArrayList<Card>();
    	List<Card> handCardsDrei = new ArrayList<Card>();
    	handCardsEins.add(card1);
    	handCardsZwei.add(card2);
    	handCardsDrei.add(card3);
    	
    	Player player1 = new Player("Player Eins", null, null);
    	player1.setHandCards(handCardsEins);
    	Player player2 = new Player("Player Zwei", handCardsZwei, null);
    	player2.setHandCards(handCardsZwei);
    	Player player3 = new Player("Player Drei", handCardsDrei, null);
    	player3.setHandCards(handCardsDrei);
    	List<Player> players = new ArrayList<Player>();
    	players.add(player1);
    	players.add(player2);
    	players.add(player3);
    	gi.setPlayers(players);
    	
    	System.out.println("Player Hand: " + gi.getPlayers().get(0).getName() + players.get(0).getHand());
    	
    	service.getNextPlayer(gi);
    	
    	System.out.println("Current Player: " + gi.getCurrentPlayer().getName());
    }
    
    @Test
    public void testSetFirstPlayer() {
    	System.out.println("**************** TEST SETFIRSTPLAYER ****************");
    	GameInstance gi = new GameInstance();
    	
    	Card card1 = new Card(Card.Zahl.SIEBEN, Card.Symbol.HERZ);
    	Card card2 = new Card(Card.Zahl.ACHT, Card.Symbol.PIK);
    	Card card3 = new Card(Card.Zahl.SIEBEN, Card.Symbol.KARO);
    	List<Card> handCardsEins = new ArrayList<Card>();
    	List<Card> handCardsZwei = new ArrayList<Card>();
    	List<Card> handCardsDrei = new ArrayList<Card>();
    	handCardsEins.add(card1);
    	handCardsZwei.add(card2);
    	handCardsDrei.add(card3);
    	
    	Player player1 = new Player("Player Eins", null, null);
    	player1.setHandCards(handCardsEins);
    	Player player2 = new Player("Player Zwei", handCardsZwei, null);
    	player2.setHandCards(handCardsZwei);
    	Player player3 = new Player("Player Drei", handCardsDrei, null);
    	player3.setHandCards(handCardsDrei);
    	List<Player> players = new ArrayList<Player>();
    	players.add(player1);
    	players.add(player2);
    	players.add(player3);
    	gi.setPlayers(players);
    	
    	
    }


}
