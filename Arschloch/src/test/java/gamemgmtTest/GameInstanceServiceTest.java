package gamemgmtTest;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cardmgmt.Card;
import cardmgmt.Card.Symbol;
import cardmgmt.Card.Zahl;
import gamemgmt.GameInstance;
import gamemgmt.GameInstanceService;
import gamemgmt.GameInstanceServiceImpl;
import playermgmt.Player;
import playermgmt.PlayerService;
import playermgmt.PlayerServiceImpl;


public class GameInstanceServiceTest {

	private GameInstanceService service;
	@Before
	public void setUp() {
		service = new GameInstanceServiceImpl();
	}
	
	@Test
	public void testDeterminePlayercount() {
	service.determinePlayercount();
	Assert.assertEquals(service.determinePlayercount(),4);
	}
	
	
	@Test
	public void testCreatePlayers() {
		List players = service.createPlayers(4);
		Assert.assertEquals(4,players.size());
	}
	
	@Test
	public void testSelectCards() {
	List<Card> testList = new LinkedList<Card>();
	testList.add(new Card(null, null));
	Player player = service.createPlayers(1).get(0);
	player.setHand(testList);
	List selectedCards = service.selectCards(player);
	Assert.assertEquals(selectedCards, testList);	
	}
	
	@Test
	public void testPlayCards() {
		List<Card> testList = new LinkedList<Card>();
		testList.add(new Card(null, null));
		Player player  = new Player(null, 0, testList, null);
		service.playCards(player,testList);
		Assert.assertEquals(player.getHand().size() , 0);	
	}
	
	@Test
	public void testSwapCards() {
		GameInstance gi = new GameInstance();
		List<Player> players = gi.players;
		List<Card> player0Cards = gi.players.get(0).getHand();
		List<Card> player1Cards = gi.players.get(0).getHand();
		service.swapCards(players);
		Assert.assertEquals(player0Cards, gi.players.get(1).getHand() );
		Assert.assertEquals(player1Cards, gi.players.get(0).getHand() );
	}
	
	@Test 
	public void testGetNextPlayer() {
		GameInstance gi = new GameInstance();
		List<Card> testList = new LinkedList<Card>();
		Player player  = new Player(null, 0, testList, null);
		Player player2  = new Player(null, 0, testList, null);
		gi.players.add(player);
		gi.players.add(player2);
		Player currentPlayer = gi.currentPlayer;
		Player nextPlayer = service.getNextPlayer();
		Assert.assertTrue(currentPlayer!=nextPlayer);	
	}
	
	@Test
	public void testCalculateGameState(){
		GameInstance gi = new GameInstance();
		List<Card> testList = new LinkedList<Card>();
		Player player  = new Player(null, 0, testList, null);
		Player player2  = new Player(null, 0, testList, null);
		gi.players.add(player);
		gi.players.add(player2);
		Assert.assertEquals(service.calculateGameState(gi) == "Running", true );
	}
	
	@Test 
	public void testCalculateInitialPlayer() {
		GameInstance gi = new GameInstance();
		gi.players = service.createPlayers(4);
		Player initialPlayer = service.calculateInitialPlayer(gi);
		Assert.assertEquals(initialPlayer, gi.getPlayers().get(0));
	}
	
	@Test 
	public void testGetPlayerMove() {
		GameInstance gi = new GameInstance();
		gi.players = service.createPlayers(1);
		List cards = service.getPlayerMove(gi.players.get(0));
		Assert.assertEquals(cards, null);
	}
}