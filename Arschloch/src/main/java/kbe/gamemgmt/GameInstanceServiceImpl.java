
package kbe.gamemgmt;

import org.springframework.stereotype.Component;

@Component
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