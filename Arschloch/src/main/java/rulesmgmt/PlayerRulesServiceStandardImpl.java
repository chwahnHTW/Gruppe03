package rulesmgmt;

import java.util.List;

import org.springframework.stereotype.Component;

import cardmgmt.Card;
import gamemgmt.GameInstance;
import playermgmt.Player;

@Component
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
