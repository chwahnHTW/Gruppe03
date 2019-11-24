package frontendmgmt;

import java.util.List;

import cardmgmt.Card;
import gamemgmt.GameInstance;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */
public class FrontendServiceImpl implements FrontendService {

    @Override
    public void init() {
        System.out.println("Initializing.......");

        Frontend gui =  new Frontend();
    }


}
