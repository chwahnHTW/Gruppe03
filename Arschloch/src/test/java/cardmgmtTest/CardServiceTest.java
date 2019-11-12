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


public class CardServiceTest {
	
private CardService service;

@Before
public void setUp() {
	service = new CardServiceImpl();
}


@Test
public void testOrderCardsByValue() {
	List cardsOnHand= new LinkedList<Card>();
	Card card1 = new Card(Zahl.ZEHN ,  null);
	Card card2 = new Card(Zahl.SIEBEN, null);
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
