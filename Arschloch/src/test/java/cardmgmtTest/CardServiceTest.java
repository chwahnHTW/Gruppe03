package cardmgmtTest;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cardmgmt.Card;
import cardmgmt.Card.Zahl;
import cardmgmt.CardService;
import cardmgmt.CardServiceImpl;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * In dieser Klasse werden alle Methoden aus dem Karten-Management getestet
 */
public class CardServiceTest {

    private CardService service;

    @Before
    public void setUp() {
        service = new CardServiceImpl();
    }


    @Test
    public void testOrderCardsByValue() {
        List cardsOnHand = new LinkedList<Card>();
        Card card1 = new Card(Zahl.ZEHN, Card.Symbol.HERZ);
        Card card2 = new Card(Zahl.SIEBEN, Card.Symbol.HERZ);
        cardsOnHand.add(card1);
        cardsOnHand.add(card2);
        service.orderCardsByValue(cardsOnHand);
        Assert.assertEquals(cardsOnHand.get(0), card2);
    }

    @Test
    public void testGenerateDeck() {
        List<Card> deck = service.generateDeck(52);
        Assert.assertEquals(deck.size(), 52);
    }


}
