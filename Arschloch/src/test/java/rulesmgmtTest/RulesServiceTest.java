package rulesmgmtTest;

import org.junit.Before;
import org.junit.Test;

import rulesmgmt.CardRulesService;
import rulesmgmt.RulesServiceImpl;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * In dieser Klasse werden alle Methoden aus dem Rules-Management getestet.
 */
public class RulesServiceTest {
    CardRulesService service;

    @Before
    public void setUp() {
        service = new RulesServiceImpl();
    }

    @Test
    public void testCompareStandard() {
    }

    @Test
    public void testCompareFrench() {
    }

}
