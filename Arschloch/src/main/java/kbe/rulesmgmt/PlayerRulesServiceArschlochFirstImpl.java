package kbe.rulesmgmt;

import org.springframework.stereotype.Component;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;

@Component
public class PlayerRulesServiceArschlochFirstImpl implements PlayerRulesService{

	/**RuleService zum Bestimmen des ersten Spielers einer Runde.
	 * Standard Implementierung : Spieler mit player.role = Arschloch spielt als erster.
	 * Falls noch niemand Arschloch ( erste Runde im Spiel -> keine Rollen ) beginnt Karo 7
	 *
	 */

	@Override
	public Player determineArschlochFirst(GameInstance gameInstance) {
		
		Player firstPlayer = null;
		for ( Player p : gameInstance.players ) {
			if(p.getRole().toString() == "ARSCHLOCH") {
				firstPlayer = p;
			}
		}
			
		return firstPlayer;
	}

	@Override
	public Player determinePresidentFirst(GameInstance gameInstance) {
		// TODO Auto-generated method stub
		return null;
	}

}
