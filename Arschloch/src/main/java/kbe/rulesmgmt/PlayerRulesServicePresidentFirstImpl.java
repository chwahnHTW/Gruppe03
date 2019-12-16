package kbe.rulesmgmt;

import org.springframework.stereotype.Component;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;

@Component
public class PlayerRulesServicePresidentFirstImpl implements PlayerRulesService {

	/**RuleService zum Bestimmen des ersten Spielers einer Runde. 
	 * PresidentFirst Implementierung : Spieler mit player.role = President spielt als erster.
	 * Falls noch niemand palyer.role = President ( erste Runde im Spiel -> keine Rollen ) beginnt Karo 7 
	 *
	 */
	@Override
	public Player determineInitialPlayer(GameInstance gameInstance) {
		
		Player initialPlayer = null;
		for ( Player p : gameInstance.players ) {
			
			for (Card c : p.handCards){
				if ( c.getSymbol().toString() == "HERZ" && c.getZahl().toString() == "SIEBEN")
					
				{
					initialPlayer = p;
				}
			}
		}
			
		return initialPlayer;
	}

}
