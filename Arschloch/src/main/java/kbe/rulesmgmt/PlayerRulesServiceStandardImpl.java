package kbe.rulesmgmt;

import org.springframework.stereotype.Component;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;

/**
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Hier wird die Standardregel implementiert, dass der Arschloch in der nächsten Runde anfängt.
 * Wenn ein Spiel vom neuen beginnt, fängt der Spieler an, der die Karte HERZ Sieben hat.
 */
@Component
public class PlayerRulesServiceStandardImpl implements PlayerRulesService{

	@Override
	public Player determineInitialPlayer(GameInstance gameInstance) {
		// TODO Auto-generated method stub
		return null;
	}

}
