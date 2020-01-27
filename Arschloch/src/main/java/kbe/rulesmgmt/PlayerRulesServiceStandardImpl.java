package kbe.rulesmgmt;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;

@Component
@Qualifier("playerRulesServiceStandardImpl")
public class PlayerRulesServiceStandardImpl implements PlayerRulesService{

	/**RuleService zum Bestimmen des ersten Spielers einer Runde.
	 * Standard Implementierung : Spieler mit player.role = Arschloch spielt als erster.
	 * Falls noch niemand Arschloch ( erste Runde im Spiel -> keine Rollen ) beginnt Karo 7
	 *
	 */

	@Override
	public Player determineInitialPlayer(GameInstance gameInstance) {
		// TODO Auto-generated method stub
		return null;
	}

}
