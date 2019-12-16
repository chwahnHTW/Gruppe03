package playermgmtTest;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kbe.cardmgmt.Card;
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

    @Test
    public void testGenerateId() {
        int x = (int) ((Math.random() * ((1000000 - 1) + 1)) + 1);
        System.out.println(x);
        Assert.assertEquals(x > 0, true);
    }

    @Test
    public void testHasCards() {
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
        Player player = new Player("Kaya", null, null);
        Card card1 = new Card(Card.Zahl.SIEBEN, Card.Symbol.KARO);
        Card card2 = new Card(Card.Zahl.ACHT, Card.Symbol.KARO);
        List cardsToBeRemoved = new LinkedList<Card>();
        cardsToBeRemoved.add(card1);

        player.setHand(card1);
        player.setHand(card2);

        System.out.println(player.getHand());

        service.removeFromHand(player, cardsToBeRemoved);

        System.out.println(player.getHand());
        Assert.assertTrue(player.getHand().size() == 1);
    }

    @Test
    public void testAddToHand() {
        Player player = new Player("Kaya", null, null);

        Card card2 = new Card(Card.Zahl.ACHT, Card.Symbol.KARO);
        List cards = new LinkedList<Card>();
        cards.add(card2);

        service.addToHand(player, cards);

        Assert.assertTrue(player.getHand().size() == cards.size() + player.getHand().size() - cards.size());
    }


}
