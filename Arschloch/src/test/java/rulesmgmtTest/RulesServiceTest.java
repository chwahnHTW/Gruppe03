package rulesmgmtTest;

import org.junit.Before;
import org.junit.Test;

import playermgmt.PlayerServiceImpl;
import rulesmgmt.RulesService;
import rulesmgmt.RulesServiceImpl;

public class RulesServiceTest {
	RulesService service;
	
	@Before
	public void setUp() {
		service = new RulesServiceImpl();
	}
	
	@Test
	public void testCompareStandard() {}
	
	@Test
	public void testCompareFrench() {}

}
