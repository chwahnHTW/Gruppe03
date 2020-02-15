package kbe.playermgmt;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardService;
import kbe.cardmgmt.CardServiceImpl;
import kbe.frontendmgmt.FrontendController;
import kbe.frontendmgmt.FrontendView;
import kbe.gamemgmt.GameInstance;
import kbe.gamemgmt.GameInstanceService;

@Service
public class BotPlayerServiceImpl {
	
	private GameInstance gameInstance;
	@Autowired
    private FrontendView frontendView;
	@Autowired
	private FrontendController frontendController;
    @Autowired
    private GameInstanceService GISI;
	@Autowired
    private PlayerService PLAYSI;
	private CardService cardService = new CardServiceImpl();
	
	public void validateBotMove(GameInstance gameInstance) {
		System.out.println("*****BOT MOVE*****");
		
        List<Card> cardsToPlay = new LinkedList<Card>();
        List<Card> botHandCards = new LinkedList<Card>();
        botHandCards = cardService.sortCardsByValue(gameInstance.getCurrentPlayer().getHand());
        
        List<Card> higherCards = new LinkedList<Card>();
        higherCards = findHigherCards(botHandCards);
        
//      boolean twoCardsEqual = true;
       
        if(gameInstance.getBoardCards() == null) {
           System.out.println("BOT MOVE; BOARD NULL");
           //Methode prüft, ob die ersten zwei karten gleichwertig sind
           //und setzt dann die eine oder zwei karten als tempCardList
           cardsToPlay = setTwoEqualCards(botHandCards);
        }
        else if (gameInstance.getBoardCards().size() == 1) {
           System.out.println("BOT MOVE; BOARD 1");
           
           cardsToPlay.add(higherCards.get(0));
        }
        else if (gameInstance.getBoardCards().size() == 2) {
        	System.out.println("BOT MOVE; BOARD 2");
            List<Card> temp = new LinkedList<Card>();
            
            // und die gleiche zahl haben
            for (int i = 1; i < higherCards.size(); i++) {
    			if(higherCards.get(i-1).equals(higherCards.get(i))){
    				temp.add(cardsToPlay.get(i-1));
    				temp.add(cardsToPlay.get(i));
    			}
    		}
            cardsToPlay.add(temp.get(0));
            cardsToPlay.add(temp.get(1));
        }
        
        gameInstance.setBoardCards(cardsToPlay);
        PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), cardsToPlay);
        frontendView.addCurrentPlayerToResult();
        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
        frontendView.updateCurrentBoardCardPanels(gameInstance);
        frontendView.updateCardButtons(gameInstance);
        frontendView.updateCurrentPlayerLabel();
       
        try {
        	if (gameInstance.getBoardCards().get(0).getZahl().toString() == "ASS") {

				// Setzen der boardCards auf null, Update Frontend
				gameInstance.setBoardCards(null);
				frontendView.updateCurrentBoardCardPanels(gameInstance);

        	}
        } catch (NullPointerException e) {
               gameInstance.setBoardCards(null);
               frontendView.updateCurrentBoardCardPanels(gameInstance);
        } catch (IndexOutOfBoundsException IOOB) {
               gameInstance.setBoardCards(null);
               frontendView.updateCurrentBoardCardPanels(gameInstance);
        }
        
        // Reset des PAssspielzug-Counters nach jedem validen Spielzug
        //passCounter = 0;
	
	}
	
	/**
	 * Setzt Karte(n), die gleichwertig sind zueinander.
	 * Funktioniert nur, wenn Boardkarte leer ist.
	 * 
	 * @param cardList
	 * @return
	 */
	public List<Card> setTwoEqualCards(List<Card> cardList) {
       
        List<Card> twoEqualCards = new LinkedList<Card>();
        boolean isEqual = true;
       
        Card x = (Card) cardList.get(0);
        Card y = (Card) cardList.get(1);
       
        int c = x.compareTo(y);
        if (c == 0) {
               isEqual = true;
        } else {
               isEqual = false;
        }
       
        if(isEqual) {
               twoEqualCards.add(x);
               twoEqualCards.add(y);
               return twoEqualCards;
        } else {
               twoEqualCards.add(x);
               return twoEqualCards;
        }
	}
	
	/**
	 * Sucht nach Karten, die höher sind als Boardkarten.
	 * 
	 * @param botHandCards
	 * @return
	 */
	public List<Card> findHigherCards (List<Card> botHandCards){
		List<Card> higherCards = new LinkedList<Card>();
        Card b = gameInstance.getBoardCards().get(0);
        
      //suche in bothandcards nach der näcshtgrößten karte
        for(Card card : botHandCards) {
     	   if(card.getZahl().compareTo(b.getZahl()) > 0) {
     		   higherCards.add(card);
     	   }
        }
        System.out.println("Boardkarte: " + gameInstance.getBoardCards().get(0).toString());
        System.out.println("higherCards: " + higherCards.toString());
        return higherCards;
	}
	
//	System.out.println("*****BOT MOVE*****");
//	List<Card> tempCardList = new LinkedList<Card>();
//	List<Card> botHandCards = new LinkedList<Card>();
//	botHandCards = cardService.sortCardsByValue(gameInstance.getCurrentPlayer().getHand());
//	boolean twoCardsEqual = true;
//	
//	try {
//		if(gameInstance.getBoardCards() == null) {
//			System.out.println("BOT MOVE; BOARD NULL");
//			Card x = (Card) botHandCards.get(0);
//			Card y = (Card) botHandCards.get(1);
//			
//			int c = x.compareTo(y);
//			if (c == 0) {
//				twoCardsEqual = true;
//			} else {
//				twoCardsEqual = false;
//			}
//			if(twoCardsEqual) {
//				tempCardList.add(x);
//				tempCardList.add(y);
//				
//			} else {
//				tempCardList.add(x);
//			}
//			
//			frontendController.setGameInstance(gameInstance);
//		}
//		else {
//			System.out.println("BOT MOVE; BOARD HAT KARTEN");
//			System.out.println("Anzahl boardcards: " + gameInstance.getBoardCards().size());
//			Card b = gameInstance.getBoardCards().get(0);
////			tempCardList.add(botHandCards.get(0)); //testen 
//			
//			for(Card card : botHandCards) {//durch alle karten des bot
//				if(card.getZahl().equals(b.getZahl())) { //die karten, die mit board stimmen
//					for(int i = 0; i < gameInstance.getBoardCards().size(); i++) { //aber nur so lange wie board groß ist
//						tempCardList.add(card);
//					}
//				}
////				if(card.getZahl().compareTo(b.getZahl())==1) { //die karten, die mit board stimmen
////					//card.getZahl().equals(b.getZahl())
////					for(int i = 0; i < gameInstance.getBoardCards().size(); i++) { //aber nur so lange wie board groß ist
////						tempCardList.add(card);
////					}
////				}
//			}
//			System.out.println(tempCardList.toString());
//			frontendController.setGameInstance(gameInstance);
//		}
//	} catch (Exception e) {
//		System.out.println("bot move catch");
//	}
//	 
//	System.out.println(tempCardList.toString());
//	System.out.println(gameInstance.getCurrentPlayer().handCards.toString());
//	
//	gameInstance.setBoardCards(tempCardList);
//	System.out.println("boardcards gesetzt");
//	PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);
//	frontendView.addCurrentPlayerToResult();
//	gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
//	frontendView.updateCurrentBoardCardPanels(gameInstance);
//	frontendView.updateCardButtons(gameInstance);
//	frontendView.updateCurrentPlayerLabel();
//	
//	try {
//		gameInstance.setBoardCards(tempCardList);
//		PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);
//		frontendView.addCurrentPlayerToResult();
//		gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
//		frontendView.updateCurrentBoardCardPanels(gameInstance);
//		frontendView.updateCardButtons(gameInstance);
//		frontendView.updateCurrentPlayerLabel();
//	} catch (Exception e){
//		gameInstance.setBoardCards(tempCardList);
////		PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);
////		frontendView.addCurrentPlayerToResult();
////		gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
//		frontendView.updateCurrentBoardCardPanels(gameInstance);
//		frontendView.updateCardButtons(gameInstance);
//		frontendView.updateCurrentPlayerLabel();
//	}
	
	
//	try {
//		if (gameInstance.getBoardCards().get(0).getZahl().toString() == "ASS") {
//
//			// Setzen der boardCards auf null, Update Frontend
//			gameInstance.setBoardCards(null);
//			frontendView.updateCurrentBoardCardPanels(gameInstance);
//
//		}
//	} catch (Exception e) {
//		gameInstance.setBoardCards(null);
//		frontendView.updateCurrentBoardCardPanels(gameInstance);
//	} 
//	catch (IndexOutOfBoundsException IOOB) {
//		gameInstance.setBoardCards(null);
//		frontendView.updateCurrentBoardCardPanels(gameInstance);
//	}
	// Reset des PAssspielzug-Counters nach jedem validen Spielzug
	//passCounter = 0;

}
