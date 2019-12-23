package rulesmgmtTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import kbe.rulesmgmt.PlayerRulesService;
import kbe.rulesmgmt.PlayerRulesServicePresidentFirstImpl;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * In dieser Klasse werden alle Methoden aus dem Rules-Management getestet.
 */
public class PlayerRulesServicePresidentFirstTest {
	
	private PlayerRulesService service;
	
	@Before
	public void setUp() {
		service = new PlayerRulesServicePresidentFirstImpl();
	}
	
	@Test
	public void testDeterminePresidentFirst() {
		GameInstance gi = new GameInstance();
		Player player1 = new Player("Player1", null, Player.Role.PRAESIDENT);
		Player player2 = new Player("Player2", null, Player.Role.MITTELKIND);
		Player player3 = new Player("Player3", null, Player.Role.ARSCHLOCH);
		List<Player> players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);
		gi.setPlayers(players);
		
		gi.setCurrentPlayer(service.determinePresidentFirst(gi));
		Assert.assertEquals(service.determinePresidentFirst(gi), player1);
	}

}
