package playermgmtTest;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cardmgmt.Card;
import playermgmt.Player;
import playermgmt.PlayerService;
import playermgmt.PlayerServiceImpl;





public class PlayerServiceTest {

	
private PlayerService service;	

@Before
public void setUp() {
	service = new PlayerServiceImpl();
}

@Test
public void testGenerateId() {
	int x = (int) ((Math.random()*((1000000-1)+1))+1);
	System.out.println(x);
	Assert.assertEquals(x>0,true);
}

@Test
public void  testHasCards(Player player){
	player.setHand(null);
	Assert.assertEquals(service.hasCards(player), false);
}

@Test 
public void testRemoveFromHand(Player player, List<Card> cardsToBeRemoved) {
	List cardsOnHand= new LinkedList<Card>();
	Card card1 = new Card(null, null);
	player.setHand(cardsOnHand);
	service.removeFromHand(player, cardsToBeRemoved);
	Assert.assertTrue(player.getHand().size() == 0);
}

@Test 
public void testAddToHand(Player player, List<Card> cards){
Card card1 = new Card(null, null);
cards.add(card1);
Assert.assertTrue(player.getHand().size() ==  cards.size() + player.getHand().size()-cards.size());
}


}
