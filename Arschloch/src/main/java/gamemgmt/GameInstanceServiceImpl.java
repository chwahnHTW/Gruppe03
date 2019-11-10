package gamemgmt;

import java.util.LinkedList;
import java.util.List;

import cardmgmt.Card;
import cardmgmt.CardService;
import historymgmt.HistoryService;
import playermgmt.Player;
import playermgmt.PlayerService;

public class GameInstanceServiceImpl implements GameInstanceService {

	GameInstance gameInstance;
	PlayerService playerService;
	CardService cardService;
	HistoryService historyService;
	
	public int determinePlayercount() {
		int userInput = playerService.getPlayerCountInput();
		return userInput;		
	}

	public void setPlayercount(int playerCount) {
	for ( int i = 0; i<playerCount;i++) {
	playerService.createPlayer();
	}
	}

	public List selectCards(PlayerService player) {
		return player.selectCards(gameInstance.boardCards);
	}
	

	public void playCards(PlayerService player, List selectedCards) {
	cardService.removeFromHand(player, selectedCards);
	gameInstance.boardCards = selectedCards;
	}

	
	/*
	Method to swap 2 players cards. @param palyers = List with 2 items. List(0) = president / vice president , List(1) = asshole / vice-asshole
	 */
	public void swapCards(List players) {
		List cardsForPresident = new LinkedList<CardService>();
		List cardsForAsshole =  new LinkedList<CardService>();;
		for (int i = 0; i < players.size(); i++) {
		cardService.orderCardsByValue(((PlayerService)players.get(i)).getHand());
		}
		
		cardsForPresident.add(((PlayerService)players.get(0)).getHand().get(((PlayerService)players.get(0)).getHand().size()));
		cardsForPresident.add(((PlayerService)players.get(0)).getHand().get(((PlayerService)players.get(0)).getHand().size()-1));
		cardsForAsshole.add(((PlayerService)players.get(1)).getHand().get(0));
		cardsForAsshole.add(((PlayerService)players.get(1)).getHand().get(1));
		
		cardService.removeFromHand(((PlayerService)players.get(0)), cardsForAsshole);
		cardService.removeFromHand(((PlayerService)players.get(1)), cardsForPresident);
		
		cardService.addToHand(((PlayerService)players.get(0)), cardsForPresident);
		cardService.addToHand(((PlayerService)players.get(1)), cardsForAsshole);
	}
	
	public Player getCurrentPlayer(GameInstance game) {
		return gameInstance.currentPlayer;
	}

	public PlayerService getNextPlayer() throws NullPointerException{
		PlayerService nextPlayer = null;
		try {
		
		for ( int i = 0 ; i < gameInstance.players.size();i++) {
		if(gameInstance.currentPlayer.equals(gameInstance.players.get(i))) {
			nextPlayer = (PlayerService) gameInstance.players.get(i+1);
		}
		}
		}catch(NullPointerException E) {
			// no next Player in list -> next player is player1
		}
		return nextPlayer;
	}

	public void setResult(GameInstanceService game) {
	historyService.persist(game);	
	}

	public List getResult(GameInstanceService game) {
		return historyService.getResult(game);
	}

	public String getGameState(GameInstanceService game) {
		int finishedPlayers = 0 ;
		
		for (int i = 0; i < gameInstance.players.size();i++) {
			
			if ( ((PlayerService) gameInstance.players.get(i)).hasCards() == false   ) 
			{
			finishedPlayers = finishedPlayers+1;
			}	
		}
		if (finishedPlayers == gameInstance.players.size()-1) {
			return "Finished";
		} else {return "Running";}
	}

	public PlayerService getInitialPlayer() {
		PlayerService initialPlayer = null;
		
		for (int i = 0; i < gameInstance.players.size();i++)
		{
		PlayerService player = (PlayerService) gameInstance.players.get(i);	
		
				for (int x = 0; x < player.getHand().size();x++)
				{
							if ( ((CardService) player.getHand().get(x)).getSymbol() == "Heart"  && ((CardService) player.getHand().get(x)).getNumber() == 7) 
									{
									initialPlayer = (PlayerService) gameInstance.players.get(i);
									}
				}
	
		}
		
		return initialPlayer;
	}

	public String getPlayerInputName(PlayerService player) {
		return playerService.getPlayerNameInput();
	}


	public List getPlayerMove(PlayerService player) {
		return player.getPlayerMove();
	}







}
