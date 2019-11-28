package frontendmgmtTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kbe.frontendmgmt.FrontendService;
import kbe.frontendmgmt.FrontendController;
import kbe.gamemgmt.GameInstance;
import kbe.gamemgmt.GameInstanceService;
import kbe.gamemgmt.GameInstanceServiceImpl;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
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

//    @Test
//    public void testStartRound() {
//        GameInstance gi = service.startGame();
//        Assert.assertTrue(gi != null);
//    }
//
//    @Test
//    public void testEndRound() {
//        GameInstance gi = service.startGame();
//        GameInstanceService giServ = new GameInstanceServiceImpl();
//        Assert.assertTrue(giServ.calculateGameState(gi) == "Finished");
//    }
//
//    @Test
//    public void testInit() {
//        GameInstance gi = service.startGame();
//        Assert.assertTrue(gi != null)
//   }

    // User Input Tests, sobald konkrete Vorgaben für Frontend
    @Test
    public void testGetUserCountInput() {
        Assert.assertTrue(true);
    }

    @Test
    public void testGetUsernameInput() {
        Assert.assertTrue(true);
    }

    @Test
    public void testGetPlayerMoveInput() {
        Assert.assertTrue(true);
    }

}
