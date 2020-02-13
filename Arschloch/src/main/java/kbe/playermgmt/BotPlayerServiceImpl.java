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
import kbe.rulesmgmt.CardRulesService;

@Service
public class BotPlayerServiceImpl implements BotPlayerService {
	
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
	
	
	public void validateBotMove() {
		this.gameInstance = frontendController.getGameInstance();
		System.out.println("*****BOT MOVE*****");
		List<Card> tempCardList = new LinkedList<Card>();
		List<Card> botHandCards = new LinkedList<Card>();
		botHandCards = cardService.sortCardsByValue(gameInstance.getCurrentPlayer().getHand());
		boolean twoCardsEqual = true;
		
		if(gameInstance.getBoardCards() == null) {
			System.out.println("BOT MOVE; BOARD NULL");
			Card x = (Card) botHandCards.get(0);
			Card y = (Card) botHandCards.get(1);
			
			int c = x.compareTo(y);
			if (c == 0) {
				twoCardsEqual = true;
			} else {
				twoCardsEqual = false;
			}
			if(twoCardsEqual) {
				tempCardList.add(x);
				tempCardList.add(y);
				
			} else {
				tempCardList.add(x);
			}
		} else {
			System.out.println("BOT MOVE; BOARD HAT KARTEN");
			Card b = gameInstance.getBoardCards().get(0);
			
			for(Card card : botHandCards) {//durch alle karten des bot
				if(card.getZahl().equals(b.getZahl())) { //die karten, die mit board stimmen
					for(int i = 0; i < gameInstance.getBoardCards().size(); i++) { //aber nur so lange wie board groß ist
						tempCardList.add(card);
					}
				}
//				if(card.getZahl().compareTo(b.getZahl())==1) { //die karten, die mit board stimmen
//					//card.getZahl().equals(b.getZahl())
//					for(int i = 0; i < gameInstance.getBoardCards().size(); i++) { //aber nur so lange wie board groß ist
//						tempCardList.add(card);
//					}
//				}
			}
		}
		gameInstance.setBoardCards(tempCardList);
		PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), tempCardList);
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

}
