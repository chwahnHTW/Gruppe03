package Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.Assert;


public class CardTest {

	/*
	 * create Card, set Symbol  and test if Card.Symbol equals the symbol you chose
	 */
	@Test
	public void testCard() {
		Card card = new Card(7, "ACE");
		Assert.assertEquals(card.number,7);
		Assert.assertEquals(card.symbol,"ACE");
	}
	

}
