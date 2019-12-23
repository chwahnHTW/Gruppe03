package rulesmgmtTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kbe.cardmgmt.Card;
import kbe.rulesmgmt.CardRulesService;
import kbe.rulesmgmt.CardRulesServiceStandardImpl;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * In dieser Klasse werden alle Methoden aus dem Rules-Management getestet.
 */
public class CardRulesServiceTest {
    private CardRulesService service;

    @Before
    public void setUp() {
        service = new CardRulesServiceStandardImpl();
    }

    @Test
    public void testCompareCardsStandardGroesser() {
    	Card card1 = new Card(Card.Zahl.DAMEN, Card.Symbol.HERZ);
    	Card card2 = new Card(Card.Zahl.SIEBEN, Card.Symbol.HERZ);
//    	service.compareCards(card1, card2);
    	Assert.assertEquals(service.compareCards(card1, card2), 1);
    	
    }
    
    @Test
    public void testCompareCardsStandardGleich() {
    	Card card1 = new Card(Card.Zahl.DAMEN, Card.Symbol.HERZ);
    	Card card2 = new Card(Card.Zahl.DAMEN, Card.Symbol.PIK);
    	Assert.assertEquals(service.compareCards(card1, card2), 0);
    }
    
    @Test
    public void testCompareCardsStandardKleiner() {
    	Card card1 = new Card(Card.Zahl.SIEBEN, Card.Symbol.HERZ);
    	Card card2 = new Card(Card.Zahl.DAMEN, Card.Symbol.PIK);
    	Assert.assertEquals(service.compareCards(card1, card2), -1);
    }
    
    @Test
    public void testCompareCardsStandardNull() {
    	Card card1 = new Card(Card.Zahl.SIEBEN, Card.Symbol.HERZ);
    	Card card2 = null;
    	Assert.assertEquals(service.compareCards(card1, card2), 1);
    }
    
    @Test
    public void testCompareCardsReverseGroesser() {
    	Card card1 = new Card(Card.Zahl.SIEBEN, Card.Symbol.HERZ);
    	Card card2 = new Card(Card.Zahl.DAMEN, Card.Symbol.HERZ);
    	Assert.assertEquals(service.compareCardsReverse(card1, card2), 1);
    }
    
    @Test
    public void testCompareCardsReverseGleich() {
    	Card card1 = new Card(Card.Zahl.SIEBEN, Card.Symbol.HERZ);
    	Card card2 = new Card(Card.Zahl.SIEBEN, Card.Symbol.PIK);
    	Assert.assertEquals(service.compareCardsReverse(card1, card2), 0);
    }
    
    @Test
    public void testCompareCardsReverseKleiner() {
    	Card card1 = new Card(Card.Zahl.DAMEN, Card.Symbol.HERZ);
    	Card card2 = new Card(Card.Zahl.SIEBEN, Card.Symbol.PIK);
    	Assert.assertEquals(service.compareCardsReverse(card1, card2), -1);
    }
    
    @Test
    public void testCompareCardsReverseNull() {
    	Card card1 = new Card(Card.Zahl.DAMEN, Card.Symbol.HERZ);
    	Card card2 = null;
    	Assert.assertEquals(service.compareCardsReverse(card1, card2), 1);
    }

}
