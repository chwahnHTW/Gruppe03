package Model;


import java.awt.List;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


public class CardiCardImplTest {

	
	CardiCardImpl card;
	
	
	
	/**
	 * Test instantiation of PlayeriPlayer ( Player-Class implementing iPlayer-Interface) 
	 * tests, if @param card.number & @param card.symbol are being set properly at instantiation
	 */
	@Test
	public void testCardiCardInstantiation() {
		card = new CardiCardImpl(7,"Hearts");
		Assert.assertEquals(7, card.getNumber());
		Assert.assertEquals("Hearts",card.getSymbol());
	}
}
