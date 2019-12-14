package kbe.frontendmgmt;


import java.util.LinkedList;
import java.util.List;

import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardService;
import kbe.cardmgmt.CardServiceImpl;
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

private PlayerService PLAYSI = new PlayerServiceImpl();

//@Autowired 
private PlayerRulesService playerRuleService = new PlayerRulesServicePresidentFirstImpl();
//@Autowired
private CardRulesService cardRulesService = new CardRulesServiceStandardImpl();

private CardService cardService = new CardServiceImpl();


	@Override
	public void init() {
        System.out.println("Initializing.......");
        gameInstance = GISI.startGame();
        frontendView.createFrontendView(gameInstance);
        cardService.dealCardsToPlayers(gameInstance);
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
		  // wenn ja, Karten von Hand des CurrentPlayers abziehen und mit getNextPlayer das Spiel weiterlaufen lassen.
		  // wenn nicht, Auffoderung, erneut Karten auszuwählen
		  if (cardRulesService.compareCards(check, check) != null) {
			  PLAYSI.removeFromHand(gameInstance.currentPlayer, tempCardList);
			  PLAYSI.getNextPlayer(gameInstance);
			  gameInstance.boardCards = tempCardList;
			  if (gameInstance.boardCards.get(0).getZahl().toString() == "Ass") {
				  gameInstance.boardCards = null;				  
			  }
			  
			  SwingUtilities.updateComponentTreeUI(frontendView);
			  
			  
			  
		  }
	  }
	  
   }
   
   
}


