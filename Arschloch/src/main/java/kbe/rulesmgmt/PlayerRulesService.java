package kbe.rulesmgmt;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;

/**
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Hier wird der Spieler festgelegt, der in der nächsten Runde anfängt zu legen.
 * Wenn ein Spiel vom neuen beginnt, fängt der Spieler an, der die Karte HERZ Sieben hat.
 * 
 * Anfangen kann Arschloch oder Präsident.
 */
public interface PlayerRulesService {
	
	/**
	 * Hier wird der Spieler bestimmt, der in der nächsten Runde anfangen soll.
	 * Das kann entweder der Verlierer oder Gewinner sein.
	 * 
	 * @param	gameInstance - die GameInstance, die eine Liste von Spielern hält
	 * @return	Ein Player der in der nächsten Runde anfangen soll
	 */
    Player determineInitialPlayer(GameInstance gameInstance);

}
