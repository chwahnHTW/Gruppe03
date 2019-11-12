
package gamemgmt;

import java.util.List;

import cardmgmt.Card;
import playermgmt.Player;
import playermgmt.PlayerService;

public class GameInstanceServiceImpl implements GameInstanceService{
	

	@Override
	public GameInstance startGame() {
		// TODO Auto-generated method stub
		return null;
	}


	public int determinePlayercount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Player> createPlayers(int playerCount) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Card> selectCards(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	public void playCards(Player player, List<Card> selectedCards) {
		// TODO Auto-generated method stub
		
	}

	public void swapCards(List<Player> players) {
		// TODO Auto-generated method stub
		
	}

	public Player getNextPlayer() throws NullPointerException {
		// TODO Auto-generated method stub
		return null;
	}

	public String calculateGameState(GameInstance game) {
		// TODO Auto-generated method stub
		return null;
	}

	public Player calculateInitialPlayer(GameInstance gameInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Card> getPlayerMove(Player player) {
		// TODO Auto-generated method stub
		return null;
	}
}