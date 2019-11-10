package playermgmt;

import java.util.LinkedList;
import java.util.List;

import cardmgmt.Card;



/**
 * 
 *
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler 
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * 
 * 
 * Stellt einen Spieler dar. Ein Spieler besteht aus einem Namen, einer Id und den Karten, die er während eines Spiels besitzt.
 *
 */
public class Player {

	int userId;
	String name;
	List handCards;

	/**
	 * Generiert einen Spieler.
	 * 
	 * @param name: Der Name des Spielers
	 * @param userid: Die Id des Spielers
	 * @param hand: Die Karten, die der Spieler besitzt
	 */
	Player(String name, int userid) {
		this.name = name;
		this.userId = userid;
	}

}
