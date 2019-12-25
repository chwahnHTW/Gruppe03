package rulesmgmtTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import kbe.rulesmgmt.PlayerRulesService;
import kbe.rulesmgmt.PlayerRulesServiceArschlochFirstImpl;

public class PlayerRulesServiceArschlochFirstTest {
	private PlayerRulesService service;
	
	@Before
	public void setUp() {
		service = new PlayerRulesServiceArschlochFirstImpl();
	}
	
	@Test
	public void testDetermineArschlochFirst() {
		GameInstance gi = new GameInstance();
		Player player1 = new Player("Player1", null, Player.Role.PRAESIDENT1);
		Player player2 = new Player("Player2", null, Player.Role.MITTELKIND);
		Player player3 = new Player("Player3", null, Player.Role.ARSCHLOCH1);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		gi.setPlayers(players);
		
		gi.setCurrentPlayer(service.determineArschlochFirst(gi));
		Assert.assertEquals(service.determineArschlochFirst(gi), player3);
	}
}
