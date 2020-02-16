package kbe.rulesmgmt;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import org.springframework.stereotype.Service;

@Service("playerRulesServicePresidentImpl")
@Qualifier("playerRulesServicePresidentImpl")
public class PlayerRulesServicePresidentImpl implements PlayerRulesService {

	/**RuleService zum Bestimmen des ersten Spielers einer Runde. 
	 * PresidentFirst Implementierung : Spieler mit player.role = President spielt als erster.
	 * Falls noch niemand palyer.role = President ( erste Runde im Spiel -> keine Rollen ) beginnt Karo 7 
	 *
	 */
	public void determineInitialPlayer(GameInstance gameInstance) {
		for (int i = 0; i < gameInstance.getPlayers().size(); i++) {
            try {
                if (gameInstance.getPlayers().get(i).getRole().equals(Player.Role.PRAESIDENT1)) {
                    gameInstance.setCurrentPlayer(gameInstance.getPlayers().get(i)); // current player setzen mit praesident
                    System.out.println("PRAESIDENT1 faengt an");
                }
            } catch (Exception e) {

            }
        }
	}

}
