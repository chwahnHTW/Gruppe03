package kbe.frontendmgmt;


import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;
import kbe.gamemgmt.GameInstanceService;
import kbe.gamemgmt.GameInstanceServiceImpl;
import kbe.playermgmt.PlayerService;
import kbe.playermgmt.PlayerServiceImpl;
import kbe.rulesmgmt.CardRulesService;
import kbe.rulesmgmt.CardRulesServiceStandardImpl;
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
	
private GameInstance gameInstance;	
	
//@Autowired	
private GameInstanceService GISI = new GameInstanceServiceImpl();

//@Autowired
private FrontendView frontendView = new FrontendView();

//@Autowired 
private PlayerRulesService playerRuleService = new PlayerRulesServicePresidentFirstImpl();
//@Autowired
private CardRulesService cardRulesService = new CardRulesServiceStandardImpl();

	@Override
	public void init() {
        System.out.println("Initializing.......");
        gameInstance = GISI.startGame();
        frontendView.createFrontendView(gameInstance);
       gameInstance.currentPlayer = playerRuleService.determineInitialPlayer(gameInstance) ;
        
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
   private void endRound(GameInstance game) {
   };
   
   public void validateMove(int[] selectedCards) {
	  
	   List tempCardList = new LinkedList<Card>();
	   
	   // geclickte Kartenfelder( Frontend) auslesen 
	   for ( int i = 0 ; i > selectedCards.length; i++) {
		   if (selectedCards[i] == 1 ) {
			   tempCardList.add(gameInstance.currentPlayer.getHand().get(i));
		   }
	   }
	   
	   // Spielzug validieren
	   
	  for (int validatedCounter = 0 ; validatedCounter < tempCardList.size(); validatedCounter++) {
		  Card check = (Card) tempCardList.get(validatedCounter);
		  
		  // hier findet später die Überprüfung statt, ob der Spielzug richtig ist. Wenn die Liste die von Compare zurückkommt die gleiche ist wie die,
		  // 
		  if (cardRulesService.compareCards(check, check) != null) {
			  
			  
		  }
	  }
	  
   }
   
   
}


