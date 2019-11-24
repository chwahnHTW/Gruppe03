package frontendmgmt;

import java.util.List;

import org.picocontainer.annotations.Inject;

import cardmgmt.Card;
import gamemgmt.GameInstance;
import gamemgmt.GameInstanceServiceImpl;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */
public class FrontendServiceImpl implements FrontendService {

	//@Inject
	private GameInstanceServiceImpl GISI = new GameInstanceServiceImpl();
	
    @Override
    public void init() {
        System.out.println("Initializing.......");
        Frontend gui =  new Frontend();
        gui.createFrame(GISI.startGame());   
    }


}
