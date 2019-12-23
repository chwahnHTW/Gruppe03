package rulesmgmtTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kbe.cardmgmt.Card;
import kbe.rulesmgmt.CardRulesService;
import kbe.rulesmgmt.CardRulesServiceReverseImpl;

public class CardRulesServiceReverseTest {
	
	private CardRulesService service;

    @Before
    public void setUp() {
        service = new CardRulesServiceReverseImpl();
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
