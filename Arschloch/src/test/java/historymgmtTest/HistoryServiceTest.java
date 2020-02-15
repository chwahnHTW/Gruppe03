package historymgmtTest;

import kbe.gamemgmt.GameInstance;
import kbe.historymgmt.HistoryService;
import kbe.historymgmt.HistoryServiceImpl;
import kbe.playermgmt.Player;
import kbe.repositories.GameInstanceRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
//@EnableAutoConfiguration
@SpringBootTest(classes = kbe.historymgmt.HistoryService.class)
public class HistoryServiceTest {

	@TestConfiguration
	static class HistoryServiceImplTestContextConfiguration {

		@Bean
		public HistoryService service() {
			return new HistoryServiceImpl();
		}
	}

	@Autowired
	HistoryService service;

	@MockBean
	GameInstanceRepository gameInstanceRepository;

//	@Before
//	public void setUp() {
//		service = new HistoryServiceImpl();
//	}

//	@Test
//	public void testPersist() {
//		GameInstance gi = new GameInstance();
//		File file=new File("example.csv");
//		service.persist(gi);
//		File file2=new File("example.csv");
//		Assert.assertTrue(file.getTotalSpace() < file2.getTotalSpace());
//	}
//
//	@Test
//	public void testGetLastPlayedGame() {
//
//		GameInstance gi = new GameInstance();
//		service.persist(gi);
//		GameInstance testGi = service.getLastPlayedGame();
//		Assert.assertEquals(testGi, gi);
//	}


	@Test
	public void testGetLastPlayedGame(){

	}


//	@Test
//	public void testSaveCurrentGame(){
//		Player player1 = new Player("Kaya", null, Player.Role.PRAESIDENT1);
//		Player player2 = new Player("Kim", null, Player.Role.MITTELKIND);
//		Player player3 = new Player("Chris", null, Player.Role.ARSCHLOCH1);
//
//		GameInstance instance = new GameInstance();
//
//		List<Player> players = new LinkedList<>();
//		players.add(player1);
//		players.add(player2);
//		players.add(player3);
//
//		instance.setPlayers(players);
//
//		service.saveCurrentGame(instance);
//
//		Assert.assertNotNull(gameInstanceRepository.findAll());
//	}

	@Test
	public void testSaveToCSV(){

		Player player1 = new Player("Kaya", null, Player.Role.PRAESIDENT1);
		Player player2 = new Player("Kim", null, Player.Role.MITTELKIND);
		Player player3 = new Player("Chris", null, Player.Role.ARSCHLOCH1);

		GameInstance instance = new GameInstance();

		List<Player> players = new LinkedList<>();
		players.add(player1);
		players.add(player2);
		players.add(player3);

		instance.setPlayers(players);

		service.saveToCSV(instance);
	}
}
