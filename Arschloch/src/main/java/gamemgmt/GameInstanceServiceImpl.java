
package gamemgmt;

import java.util.List;

import cardmgmt.Card;
import playermgmt.Player;
import playermgmt.PlayerService;

public class GameInstanceServiceImpl implements GameInstanceService{

	@Override
	public GameInstance startGame() {
		return new GameInstance();
	}

	@Override
	public String calculateGameState(GameInstance gameInstance) {
		// TODO Auto-generated method stub
		return null;
	}
	

}