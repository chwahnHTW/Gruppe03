package kbe.gamemgmt;

import kbe.playermgmt.Player;
import org.springframework.stereotype.Service;


@Service
public class GameInstanceServiceImpl implements GameInstanceService {

    @Override
    public GameInstance startGame() {
        return new GameInstance();
    }

    @Override
    public String calculateGameState(GameInstance gameInstance) {
        String gameState = null;
        int counter = 1;
        for (Player player : gameInstance.getPlayers()) {
            try {
                if (player.getHand().isEmpty()) {
                    counter++;
                }
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        if (counter == gameInstance.getPlayers().size()) {
            gameState = "Finished";
        } else {
            gameState = "Running";
        }
        System.out.println(counter + " " + gameState);
        return gameState;
    }

}