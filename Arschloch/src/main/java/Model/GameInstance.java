package Model;

import java.awt.List;
import java.util.LinkedList;

/**
 * 
 *
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler 
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * 
 *
 *
 * Diese Klasse stellt die Instanz eines Spiels dar. 
 * Hier läuft das Spiel im Großteil ab.
 *
 */
public class GameInstance {

	LinkedList<Player> players;
	LinkedList<Integer>result;
	LinkedList<Card> boardCards;
	Player currentPlayer;

	/**
	 * Enthält die Informationen, die während eines Spiels vorrangig wichtig sind.
	 * 
	 * @param players ist eine LinkedList<Player> aus den Spielern
	 */
	GameInstance() {
	}


}