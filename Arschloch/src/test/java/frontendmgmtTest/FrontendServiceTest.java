package frontendmgmtTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import frontendmgmt.FrontendService;
import frontendmgmt.FrontendServiceImpl;
import gamemgmt.GameInstance;
import gamemgmt.GameInstanceService;
import gamemgmt.GameInstanceServiceImpl;


public class FrontendServiceTest {
	private FrontendService service;
	

	@Before
	public void setUp() {
		service = new FrontendServiceImpl();
	}
	
	@Test
	public void testStartRound() {
		GameInstance gi = service.startGame();
		Assert.assertTrue(gi!=null);
	}
	
	@Test
	public void testEndRound() {
		GameInstance gi = service.startGame();
		GameInstanceService giServ = new GameInstanceServiceImpl();
		Assert.assertTrue(giServ.calculateGameState(gi) == "Finished");
	}
	
	@Test
	public void testInit() {
		GameInstance gi = service.startGame();
		Assert.assertTrue(gi!=null);	
	}
	
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
