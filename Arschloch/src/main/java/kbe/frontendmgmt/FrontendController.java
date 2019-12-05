package kbe.frontendmgmt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kbe.gamemgmt.GameInstance;
import kbe.gamemgmt.GameInstanceService;
import kbe.gamemgmt.GameInstanceServiceImpl;
import kbe.playermgmt.PlayerService;
import kbe.playermgmt.PlayerServiceImpl;
import kbe.rulesmgmt.PlayerRulesService;
import kbe.rulesmgmt.PlayerRulesServicePresidentFirstImpl;
import kbe.rulesmgmt.Rules;

/**
 * Daten hier speichern
 * @author kimanhnguyen
 *
 */

@Controller
public class FrontendController implements FrontendService {
	
//@Autowired	
private GameInstanceService GISI = new GameInstanceServiceImpl();

//@Autowired
private FrontendView frontendView = new FrontendView();

//@Autowired 
private PlayerRulesService playerRuleService = new PlayerRulesServicePresidentFirstImpl();

	@Override
	public void init() {
        System.out.println("Initializing.......");
        frontendView.createFrontendView(GISI.startGame());
        System.out.println("help");
	}

	
    /**
     * Eine Spielinstanz wird erstellt und zurückgegeben.
     * GUI wird mit Spielinstanz bestückt
     *
     * @return : eine Spielinstanz
     */
    private GameInstance startGame() {
		return new GameInstance();}
    
    
    /**
     * Das Spiel wird beendet.
     *
     * @param game: Eine Spielinstanz
     */
   private void endRound(GameInstance game) {};
}
