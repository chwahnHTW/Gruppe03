package kbe.rulesmgmt;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;

@Service
@Qualifier("playerRulesServiceArschlochImpl")
public class PlayerRulesServiceArschlochImpl implements PlayerRulesService{

	/**RuleService zum Bestimmen des ersten Spielers einer Runde.
	 * Standard Implementierung : Spieler mit player.role = Arschloch spielt als erster.
	 * Falls noch niemand Arschloch ( erste Runde im Spiel -> keine Rollen ) beginnt Karo 7
	 *
	 */
	public void determineInitialPlayer(GameInstance gameInstance) {
		for (int i = 0; i < gameInstance.getPlayers().size(); i++) {
            try {
                if (gameInstance.getPlayers().get(i).getRole().equals(Player.Role.ARSCHLOCH1)) {
                    gameInstance.setCurrentPlayer(gameInstance.getPlayers().get(i)); // current player setzen mit arschloch
                    System.out.println("ARSCHLOCH1 faengt an");
                }
            } catch (Exception e) {

            }
        }
	}

}