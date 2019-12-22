
package kbe.gamemgmt;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kbe.playermgmt.Player;

@Service

public class GameInstanceServiceImpl implements GameInstanceService {

	/*
	 * Methode, um Spielinstanz zu erzeugen
	 * */
    @Override
    public GameInstance startGame() {
        return new GameInstance();
    }

    /*
     * 
     * Methode, um den Spielstatus zu erfassen. 
     * return String - Spielstatus - running, wenn spiel noch lauft
     * finished, wenn Spiel vorbei
     * 
     * */
    
//    @Override
//    public String calculateGameState(GameInstance gameInstance) {
//        String gameState = null;
//        int counter = 1;
//        for (Player player : gameInstance.getPlayers()){
//            try {
//                if (player.getHand().isEmpty()) {
//                    counter++;
//                }
//            } catch (NullPointerException | IndexOutOfBoundsException e) {
//                e.printStackTrace();
//            }
//        }
//        if (counter == gameInstance.getPlayers().size()) {
//            gameState = "Finished";
//        } else {
//            gameState = "Running";
//        }
//        System.out.println(counter + " " + gameState);
//        return gameState;
//    }
    
    @Override
    public String calculateGameState(GameInstance gameInstance) {
        String gameState = null;
        int counter = 0;
        for (Player player : gameInstance.getPlayers()){
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