package kbe.rulesmgmt;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Die Implementierung der Regeln, falls der Präsident beginnen soll.
 */
@Service
@Qualifier("playerRulesServicePresidentImpl")
public class PlayerRulesServicePresidentImpl implements PlayerRulesService {

    @Override
    public void determineInitialPlayer(GameInstance gameInstance) {
        for (int i = 0; i < gameInstance.getPlayers().size(); i++) {
            try {
                if (gameInstance.getPlayers().get(i).getRole().equals(Player.Role.PRAESIDENT1)) {
                    gameInstance.setCurrentPlayer(gameInstance.getPlayers().get(i));
                }
            } catch (Exception e) {

            }
        }
    }

}