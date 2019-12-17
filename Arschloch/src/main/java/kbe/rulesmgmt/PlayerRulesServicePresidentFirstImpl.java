package kbe.rulesmgmt;

import org.springframework.stereotype.Component;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import org.springframework.stereotype.Service;

/**
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Hier wird die Regel implementiert, dass der Präsident in der nächsten Runde anfängt zu spielen.
 * Wenn ein Spiel vom neuen beginnt, fängt der Spieler an, der die Karte HERZ Sieben hat.
 */
@Service
public class PlayerRulesServicePresidentFirstImpl implements PlayerRulesService {

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
