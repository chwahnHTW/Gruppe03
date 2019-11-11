package gamemgmt;

import java.util.LinkedList;
import java.util.List;

import cardmgmt.Card;
import playermgmt.Player;


/**
 * 
 *
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler 
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * 
 *
 *
 * Diese Klasse stellt die Instanz eines Spiels dar. 
 * Ein Spiel beinhaltet die Spieler, das momentane Rundenergebnis, die Karten, mit denen gerade gespielt wird und den aktuellen Spieler.
 * Hier läuft das Spiel im Großteil ab.
 *
 */
public class GameInstance {

	public List<Player> players;
	public List<Player> result;
	public List<Card> boardCards;
	public Player currentPlayer;

	/**
	 * Enthält die Informationen, die während eines Spiels vorrangig wichtig sind.
	 * 
	 */
	GameInstance() {
	}

}