package Model;

import java.awt.List;
import java.lang.reflect.Array;

/**
 * 
 *
 * @author Kaya Löher, Kim Anh Ngyuen, Christian Wahnsiedler Email-Adresse:
 *         s0564784@htw-berlin.de, ...
 * 
 *         Der Spieler
 *
 */
public class Player {

	int userId;
	String name;
	List hand;
	Card[] handCards;
	
	
	/**
	 * Der Spieler
	 * 
	 * @param name
	 */
	Player(String name, int userid, List hand) {
		this.name = name;
		this.userId = userid;
		this.hand = hand;
	}
	
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * Gibt den Namen eines Spielers zurück
	 * 
	 * @return: der Name des Spielers
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Namen eines Spielers
	 * 
	 * @param name: Der Name des Spielers
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * gibt das Array der Karten, die der Spieler hat, zurück
	 * 
	 * @return: Die Karten des Spielers
	 */
	public List getHand() {
		return hand;
	}

	/**
	 * setzt die Karten, die der Spieler bekommen soll
	 * 
	 * @param cards: Die Karten des Spielers
	 */
	public void setHand(List hand) {
		this.hand = hand;
	}

}
