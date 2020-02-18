package kbe.frontendmgmtTest;

import kbe.frontendmgmt.FrontendController;
import kbe.frontendmgmt.FrontendService;
import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * In dieser Klasse werden alle Methoden aus dem Frontend-Management getestet.
 */
public class FrontendServiceTest {

    private FrontendService service;

    @Before
    public void setUp() {
        service = new FrontendController();
    }

    @Test
    public void testSetPlayerRoles() {
        GameInstance gi = new GameInstance();

        Player player1 = new Player("Player1", null, null);
        Player player2 = new Player("Player2", null, null);
        Player player3 = new Player("Player3", null, null);
        List<Player> players = new LinkedList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        for (Player player : players) {
            gi.setResult(player);
        }

        service.setPlayerRoles(gi);
        Assert.assertTrue(player1.getRole().equals(Player.Role.PRAESIDENT1));
    }

    @Test
    public void testAddCurrentPlayerToResult() {
        GameInstance gi = new GameInstance();

        Player player1 = new Player("Player1", null, null);
        List<Player> players = new LinkedList<>();
        players.add(player1);

        gi.setCurrentPlayer(player1);

        service.addCurrentPlayerToResult(gi);

        Assert.assertTrue(gi.getResult().contains(player1));
    }

    @Test
    public void testStartSavedGame() {

    }

}
