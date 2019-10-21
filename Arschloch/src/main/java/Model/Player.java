package Model;

import java.awt.List;

/**
 * 
 *
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler 
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * 
 * 
 * Stellt einen Spieler dar.
 *
 */
public class Player {

	int userId;
	String name;
	List hand;
	Card[] handCards;
	
	
	/**
	 * Generiert einen Spieler.
	 * 
	 * @param name: Der Name des Spielers
	 * @param userid: Die Id des Spielers
	 * @param hand: Die Karten, die der Spieler besitzt
	 */
	Player(String name, int userid, List hand) {
		this.name = name;
		this.userId = userid;
		this.hand = hand;
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
	public List getHand() {
		return hand;
	}

	/**
	 * setzt die Karten, die der Spieler bekommen soll.
	 * 
	 * @param cards: Die Karten des Spielers
	 */
	public void setHand(List hand) {
		this.hand = hand;
	}

}
