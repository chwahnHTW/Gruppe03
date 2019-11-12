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
		GameInstance gi = service.startRound();
		Assert.assertTrue(gi!=null);
	}
	
	@Test
	public void testEndRound() {
		GameInstanceService giService = new GameInstanceServiceImpl();
		String gameState = giService.calculateGameState(giService.startGame());
		Assert.assertTrue(gameState.equals("Finished"));
	}
	
	@Test
	public void testInit() {}
	
	@Test
	public void testGetUserCountInput() {}
	
	@Test
	public void testGetUsernameInput() {}
	
	@Test
	public void testGetPlayerMoveInput() {}

}
