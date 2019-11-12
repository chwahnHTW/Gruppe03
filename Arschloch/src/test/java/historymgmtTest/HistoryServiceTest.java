package historymgmtTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cardmgmt.CardServiceImpl;
import gamemgmt.GameInstance;
import historymgmt.HistoryService;
import historymgmt.HistoryServiceImpl;

public class HistoryServiceTest {

	HistoryService service;
	
	@Before
	public void setUp() {
		service = new HistoryServiceImpl();
	}
	
	
	@Test
	public void testPersist() {
		GameInstance gi = new GameInstance();
		service.persist(gi);
		Assert.assertTrue(true);
	}
	
	@Test
	public void testGetLastPlayedGame() {}
}
