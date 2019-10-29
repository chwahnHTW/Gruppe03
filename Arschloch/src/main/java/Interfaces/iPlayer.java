/**
 * 
 */
package Interfaces;

import java.util.LinkedList;

import Model.Card;
import Model.Player;

/**
 *
 * @authors Kaya Löher | Kim Anh Nguyen | Christian Wahnsiedler Email-Adresse:
 *          s0564784@htw-berlin.de | s0563958@htw-berlin.de|
 *          s0557193@htw-berlin.de
 * 
 *          Der Spieler muss vor dem Spiel erstellt werden. Der Spieler bekommt
 *          eine Hand an Karten zugewiesen
 *
 */
public interface iPlayer {

	/**
	 * --> IPlayer
	 * 
	 * Ein Spieler wird erstellt.
	 * 
	 * @return ein Spieler
	 */
	public Player createPlayer(int userid, String name);

	/**
	 * Gibt das Array der Karten, die der Spieler hat, zurück.
	 * 
	 * @return: Die Karten des Spielers
	 */
	public LinkedList<Card> getHand();

	/**
	 * setzt die Karten, die der Spieler bekommen soll.
	 * 
	 * @param cards: Die Karten des Spielers
	 */
	public void setHand(LinkedList<Card> handCards);

}
