package errormgmtTest;

import org.junit.Before;
import org.junit.Test;

import errormgmt.ErrorService;
import errormgmt.ErrorServiceImpl;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * In dieser Klasse werden alle Methoden aus dem Error-Management getestet.
 */
public class ErrorServiceTest {
    ErrorService service;

    @Before
    public void setUp() {
        service = new ErrorServiceImpl();
    }

    @Test
    public void testHandleError() {
    }


}
