package historymgmtTest;

import java.io.File;

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
		File file=new File("example.csv");
		service.persist(gi);
		File file2=new File("example.csv");
		Assert.assertTrue(file.getTotalSpace() < file2.getTotalSpace());
	}
	
	@Test
	public void testGetLastPlayedGame() {
	
		GameInstance gi = new GameInstance();
		service.persist(gi);
		GameInstance testGi = service.getLastPlayedGame();
		Assert.assertEquals(testGi, gi);
	}
}
