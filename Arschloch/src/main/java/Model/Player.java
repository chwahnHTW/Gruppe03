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
 * Stellt einen Spieler dar. Ein Spieler besteht aus einem Namen, einer Id und den Karten, die er während eines Spiels besitzt.
 *
 */
public class Player {

	int userId;
	String name;
	LinkedList<Card> handCards;

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

	/**
	 * Gibt die Userid zurück
	 * 
	 * @return die userId ces Spielers
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Setzt die Userid.
	 * 
	 * @param userId: die userId des Spielers
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gibt den Namen eines Spielers zurück.
	 * 
	 * @return: der Name des Spielers
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Namen eines Spielers.
	 * 
	 * @param name: Der Name des Spielers
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gibt das Array der Karten, die der Spieler hat, zurück.
	 * 
	 * @return: Die Karten des Spielers
	 */
	public LinkedList<Card> getHand() {
		return handCards;
	}

	/**
	 * setzt die Karten, die der Spieler bekommen soll.
	 * 
	 * @param cards: Die Karten des Spielers
	 */
	public void setHand(LinkedList<Card> handCards) {
		this.handCards = handCards;
	}

}
