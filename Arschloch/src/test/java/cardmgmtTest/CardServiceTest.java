package cardmgmtTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardService;
import kbe.cardmgmt.CardServiceImpl;
import kbe.playermgmt.Player;
import kbe.cardmgmt.Card.Zahl;
import kbe.cardmgmt.Card.Symbol;


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
   public void testSwapCards() {
	   List players = new ArrayList<Player>();
	   //Player player1 = new Player();
	   List deck = service.generateDeck();
	   
	   for(int i = 0; i<=4; i++) {
		   
	   }
	   
   }

//    @Test
//    public void testOrderCardsByValue() {
//        List cardsOnHand = new LinkedList<Card>();
//        Card card1 = new Card(Zahl.ZEHN, Symbol.HERZ);
//        Card card2 = new Card(Zahl.SIEBEN, Symbol.HERZ);
//        cardsOnHand.add(card1);
//        cardsOnHand.add(card2);
//        service.orderCardsByValue(cardsOnHand);
//        Assert.assertEquals(cardsOnHand.get(0), card2);
//    }

//    @Test
//    public void testGenerateDeck() {
//        List<Card> deck = service.generateDeck(52);
//        Assert.assertEquals(deck.size(), 52);
//    }


}
