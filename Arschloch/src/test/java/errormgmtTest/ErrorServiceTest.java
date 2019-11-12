package errormgmtTest;

import org.junit.Before;
import org.junit.Test;

import errormgmt.ErrorService;
import errormgmt.ErrorServiceImpl;
import playermgmt.PlayerServiceImpl;

public class ErrorServiceTest {
	ErrorService service;
	
	@Before
	public void setUp() {
		service = new ErrorServiceImpl();
	}
	
	@Test
	public void testHandleError() {}
	

}
