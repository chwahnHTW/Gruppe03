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
public class BotPlayerServiceImpl implements BotPlayerService{
	
//	private GameInstance gameInstance;
	@Autowired
    private FrontendView frontendView;
	@Autowired
	private FrontendController frontendController;
    @Autowired
    private GameInstanceService GISI;
	@Autowired
    private PlayerService PLAYSI;
	private CardService cardService = new CardServiceImpl();
	
	/**
	 * BOT MOVE
	 * 
	 * @param gameInstance
	 */
	public void validateBotMove(GameInstance gameInstance) {
		System.out.println("*****BOT MOVE aus Service*****");
        List<Card> cardsToPlay = new LinkedList<Card>();
        List<Card> botHandCards = new LinkedList<Card>();
        botHandCards = cardService.sortCardsByValue(gameInstance.getCurrentPlayer().getHand());
        List<Card> higherCards = new LinkedList<Card>();
        
        if(gameInstance.getBoardCards() == null) {
           if(botHandCards.size() < 2) {
        	   cardsToPlay = botHandCards;
           } else if (botHandCards.size() >= 2){
        	   //Methode prüft, ob die ersten zwei karten gleichwertig sind
               //und setzt dann die zwei karten als tempCardList
               cardsToPlay = setTwoEqualCards(botHandCards);
           }
           
           updateAll(cardsToPlay, gameInstance);
        }
        else if (gameInstance.getBoardCards().size() == 1) {
           higherCards = findHigherCards(botHandCards, gameInstance);
           if(higherCards.isEmpty()) {
        	   botPass(gameInstance);
           } else {
        	   cardsToPlay.add(higherCards.get(0));
        	   updateAll(cardsToPlay, gameInstance);
           }
        }
        else if (gameInstance.getBoardCards().size() == 2) {
            List<Card> temp = new LinkedList<Card>();
            higherCards = findHigherCards(botHandCards, gameInstance);
            if(higherCards.isEmpty()) {
            	botPass(gameInstance);
            }
            else {
            	// und die gleiche zahl haben
                for (int i = 1; i < higherCards.size(); i++) {
        			if(higherCards.get(i-1).getZahl().equals(higherCards.get(i).getZahl())){
        				temp.add(higherCards.get(i-1));
        				temp.add(higherCards.get(i));
        			}
        		}
                if(temp.size()<2) {
                	botPass(gameInstance);
                } else {
                	cardsToPlay.add(temp.get(0));
                	cardsToPlay.add(temp.get(1));
                    updateAll(cardsToPlay, gameInstance);
                }
            }
        }
        assOnBoard(gameInstance);
	}
	
	/**
	 * Nach validen Spielzug alles updaten.
	 * 
	 * @param cardsToPlay
	 */
	void updateAll(List<Card> cardsToPlay, GameInstance gameInstance) {
		gameInstance.setBoardCards(cardsToPlay);
        PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), cardsToPlay);
        frontendView.addCurrentPlayerToResult();
        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
        frontendView.updateCurrentBoardCardPanels(gameInstance);
        frontendView.updateCardButtons(gameInstance);
        frontendView.updateCurrentPlayerLabel();
        
     // Reset des PAssspielzug-Counters nach jedem validen Spielzug
//        passCounter = 0;
        frontendController.setPassCounter(0);
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
	public List<Card> findHigherCards (List<Card> botHandCards, GameInstance gameInstance){
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

	/**
	 * Wenn der Bot keine Karte hat zum Legen, wird gepasst.
	 */
	void botPass(GameInstance gameInstance) {
		int passCounter = frontendController.getPassCounter();
		System.out.println("BOT MUSS PASSEN");
		passCounter++;
		// setzen des naechsten Spielers
		gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
		// Frontend Update
		frontendView.updateCurrentPlayerLabel();
		frontendView.updateCardButtons(gameInstance);
		//reset der Current BoardCard, da jeder Spieler 1x gepasst hat
		
		int playersWithCardsCounter = 0;
		for (Player player : gameInstance.getPlayers()){
			if(PLAYSI.hasCards(player)){
				playersWithCardsCounter++;
			}
		}
		
		if (passCounter == playersWithCardsCounter-1) {
			gameInstance.setBoardCards(null);
			System.out.println("Pass-Counter = Anzahl Spieler mit Karten - boardCards resettet");
		// Frontend Update
			frontendView.updateCardButtons(gameInstance);
			frontendView.updateCurrentBoardCardPanels(gameInstance);
		}
		
		frontendController.setPassCounter(passCounter);
	}
	
	/**
	 * Wenn ein Ass gelegt wurde,
	 * werden die Boardcards abgeräumt und neuer Zug beginnt.
	 */
	void assOnBoard(GameInstance gameInstance) {
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
	}

}
