package Model;

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

	String name;
	Card[] cards;

	/**
	 * Der Spieler
	 * 
	 * @param name
	 */
	Player(String name) {
		this.name = name;
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
	public Card[] getCards() {
		return cards;
	}

	/**
	 * setzt die Karten, die der Spieler bekommen soll
	 * 
	 * @param cards: Die Karten des Spielers
	 */
	public void setCards(Card[] cards) {
		this.cards = cards;
	}

}
