package Model;

import java.awt.List;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

public class PlayeriPlayerImplTest {

	PlayeriPlayerImpl playeriPlayer;

	/**
	 * Test instantiation of playeriPlayer (Player-Class implementing
	 * iPlayer-Interface) tests, if @param playeriPlayer.userName & @param
	 * playeriPlayer.userId are being set properly at instantiation
	 */
	@Test
	public void testPlayeriPlayerInstantition() {
		playeriPlayer = new PlayeriPlayerImpl("testUser", 420);
		Assert.assertEquals("testUser", playeriPlayer.getName());
		Assert.assertEquals(420, playeriPlayer.getUserId());
	}

	/**
	 * Test instantiation of playeriPlayer ( Player-Class implementing
	 * iPlayer-Interface) tests, if @param playeriPlayer.userName & @param
	 * playeriPlayer.userId are being set properly with playeriPlayer.setName() and
	 * .setUserId()
	 */
	@Test
	public void testPlayeriPlayerSetAttributes() {

		playeriPlayer = new PlayeriPlayerImpl("testUser", 420);
		playeriPlayer.setName("Habibi Blocksberg");
		playeriPlayer.setUserId(421);
		Assert.assertEquals("Habibi Blocksberg", playeriPlayer.getName());
		Assert.assertEquals(421, playeriPlayer.getUserId());
	}

	/**
	 * Test retrieval of PlayeriPlayer.handCards() (Player-Class implementing
	 * iPlayer-Interface) tests, if @param playeriPlayer.handCards is being properly
	 * retrieved with playeriPlayer.getHand();
	 */
	@Test
	public void testPlayeriPlayerSetHandTest() {

		playeriPlayer = new PlayeriPlayerImpl("testUser", 420);
		LinkedList<Card> cardList = new LinkedList<Card>();
		cardList.add(new Card(7, "Hearts"));
		cardList.add(new Card(8, "Diamonds"));
		playeriPlayer.handCards = cardList;

		LinkedList<Card> testPlayerHand = playeriPlayer.getHand();

		Assert.assertEquals(7, testPlayerHand.get(0).number);
		Assert.assertEquals("Hearts", testPlayerHand.get(0).symbol);
		Assert.assertEquals(8, testPlayerHand.get(1).number);
		Assert.assertEquals("Diamonds", testPlayerHand.get(1).symbol);

	}

	/**
	 * Test setting of PlayeriPlayer.handCards (Player-Class implementing
	 * iPlayer-Interface) with playeriPlayer.setHand() tests, if @param
	 * playeriPlayer.handCards is being properly set with playeriPlayer.setHand();
	 */
	@Test
	public void testPlayeriPlayerGetHandTest() {

		playeriPlayer = new PlayeriPlayerImpl("testUser", 420);
		LinkedList<Card> cardList = new LinkedList<Card>();
		cardList.add(new Card(7, "Hearts"));
		cardList.add(new Card(8, "Diamonds"));
		playeriPlayer.setHand(cardList);

		LinkedList<Card> testPlayerHand = playeriPlayer.getHand();

		Assert.assertEquals(7, testPlayerHand.get(0).number);
		Assert.assertEquals("Hearts", testPlayerHand.get(0).symbol);
		Assert.assertEquals(8, testPlayerHand.get(1).number);
		Assert.assertEquals("Diamonds", testPlayerHand.get(1).symbol);

	}

}
