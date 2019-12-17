
package kbe.gamemgmt;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kbe.playermgmt.Player;

@Service

public class GameInstanceServiceImpl implements GameInstanceService {

    @Override
    public GameInstance startGame() {
        return new GameInstance();
    }

    @Override
    public String calculateGameState(GameInstance gameInstance) {
        // TODO Auto-generated method stub

        String gameState = null;
        int counter = 1;
        for (Player player : gameInstance.getPlayers())

            try {
                if (player.getHand() == null) {
                    counter++;
                }
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                e.printStackTrace();
            }

        if (counter == gameInstance.getPlayers().size() - 1) {
            gameState = "Finished";
        } else {
            gameState = "Running";
        }
        return gameState;
    }

}