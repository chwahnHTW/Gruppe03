package rulesmgmtTest;

import org.junit.Before;
import org.junit.Test;

import kbe.rulesmgmt.CardRulesService;
import kbe.rulesmgmt.CardRulesServiceStandardImpl;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * In dieser Klasse werden alle Methoden aus dem Rules-Management getestet.
 */
public class RulesServiceTest {
    private CardRulesService service;

    @Before
    public void setUp() {
        service = new CardRulesServiceStandardImpl();
    }

    @Test
    public void testCompareCards() {
    	
    }

    @Test
    public void testCompareFrench() {
    	
    }

}
