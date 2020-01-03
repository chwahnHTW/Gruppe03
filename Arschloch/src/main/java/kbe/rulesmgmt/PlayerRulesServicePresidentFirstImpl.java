package kbe.rulesmgmt;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import org.springframework.stereotype.Service;

@Service
//@Qualifier("PlayerRulesServicePresidentFirstImpl")
public class PlayerRulesServicePresidentFirstImpl implements PlayerRulesService {

	/**RuleService zum Bestimmen des ersten Spielers einer Runde. 
	 * PresidentFirst Implementierung : Spieler mit player.role = President spielt als erster.
	 * Falls noch niemand palyer.role = President ( erste Runde im Spiel -> keine Rollen ) beginnt Karo 7 
	 *
	 */
	@Override
	public Player determinePresidentFirst(GameInstance gameInstance) {
		
		Player firstPlayer = null;
		for ( Player p : gameInstance.players ) {
			if(p.getRole().toString() == "PRAESIDENT") {
				firstPlayer = p;
			}
		}
			
		return firstPlayer;
	}

	@Override
	public Player determineArschlochFirst(GameInstance gameInstance) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@Override
//	public Player determineInitialPlayer(GameInstance gameInstance) {
//		
//		Player initialPlayer = null;
//		for ( Player p : gameInstance.players ) {
//			
//			for (Card c : p.handCards){
//				if ( c.getSymbol().toString() == "HERZ" && c.getZahl().toString() == "SIEBEN")
//					
//				{
//					initialPlayer = p;
//				}
//			}
//		}
//			
//		return initialPlayer;
//	}

}
