package Model;

import java.util.LinkedList;

import Interfaces.iPlayer;

public class PlayeriPlayerImpl extends Player implements iPlayer {

	PlayeriPlayerImpl(String name, int userid) {
		super(name, userid);
	}

	public Player createPlayer(int userid, String name) {
		return new Player(name, userid);
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
	 * @param userId:
	 *            die userId des Spielers
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
	 * @param name:
	 *            Der Name des Spielers
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
	 * @param cards:
	 *            Die Karten des Spielers
	 */
	public void setHand(LinkedList<Card> handCards) {
		this.handCards = handCards;
	}

}
