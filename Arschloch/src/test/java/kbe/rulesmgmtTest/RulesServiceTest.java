package kbe.rulesmgmtTest;

import junit.framework.Assert;
import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import kbe.playermgmt.Player.Role;
import kbe.rulesmgmt.PlayerRulesService;
import kbe.rulesmgmt.PlayerRulesServiceArschlochImpl;
import kbe.rulesmgmt.PlayerRulesServicePresidentImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * In dieser Klasse werden alle Methoden aus dem Rules-Management getestet.
 */
public class RulesServiceTest {

    private PlayerRulesService arschlochRuleService;
    private PlayerRulesService presidentRuleService;


    @Before
    public void setUp() {
        arschlochRuleService = new PlayerRulesServiceArschlochImpl();
        presidentRuleService = new PlayerRulesServicePresidentImpl();
    }

    @Test
    public void testDetermineArschloch() {
        GameInstance gi = new GameInstance();
        Player arschloch1 = new Player("Arsch1Player", null, null);
        arschloch1.setRole(Role.ARSCHLOCH1);
        Player mittelkind = new Player("MittelPlayer", null, null);
        Player president1 = new Player("Presi1Player", null, null);
        president1.setRole(Role.PRAESIDENT1);
        List<Player> players = new LinkedList<Player>();
        players.add(arschloch1);
        players.add(mittelkind);
        players.add(president1);
        gi.setPlayers(players);

        arschlochRuleService.determineInitialPlayer(gi);

        Assert.assertEquals(gi.getCurrentPlayer(), arschloch1);
    }

    @Test
    public void testDeterminePresident() {
        GameInstance gi = new GameInstance();
        Player arschloch1 = new Player("Arsch1Player", null, null);
        arschloch1.setRole(Role.ARSCHLOCH1);
        Player mittelkind = new Player("MittelPlayer", null, null);
        Player president1 = new Player("Presi1Player", null, null);
        president1.setRole(Role.PRAESIDENT1);
        List<Player> players = new LinkedList<Player>();
        players.add(arschloch1);
        players.add(mittelkind);
        players.add(president1);
        gi.setPlayers(players);

        presidentRuleService.determineInitialPlayer(gi);

        Assert.assertEquals(gi.getCurrentPlayer(), president1);
    }

}
