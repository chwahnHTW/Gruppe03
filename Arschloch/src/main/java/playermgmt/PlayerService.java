/**
 * 
 */
package playermgmt;

import java.util.LinkedList;
import java.util.List;

import cardmgmt.Card;
import playermgmt.Player;

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
public interface PlayerService {

	/**
	 * --> IPlayer
	 * 
	 * Ein Spieler wird erstellt.
	 * 
	 * @return ein Spieler
	 */
	public Player createPlayer();

	/**
	 * Gibt das Array der Karten, die der Spieler hat, zurück.
	 * 
	 * @return: Die Karten des Spielers
	 */
	public List getHand(Player player);

	/**
	 * setzt die Karten, die der Spieler bekommen soll.
	 * 
	 * @param cards: Die Karten des Spielers
	 */
	
	public void setHand(List<Card> cards, Player player);

	
	
	/**
	 * Gibt die Userid zurück
	 * 
	 * @return die userId ces Spielers
	 */
	public int getUserId(Player player);
	
	
	
	/**
	 * Setzt die Userid.
	 * 
	 * @param userId: die userId des Spielers
	 */
	
	
	/**
	 * Gibt den Namen eines Spielers zurück.
	 * 
	 * @return: der Name des Spielers
	 */
	public String getName(Player player);
	
	
	/**
	 * Setzt den Namen eines Spielers.
	 * 
	 * @param name: Der Name des Spielers
	 */
	public void setName(String name);

	/**
	 * In einer Runde kann jeder Spieler Karten spielen. Hier wählt der Spieler aus,
	 * welche Karte(n) von seiner Hand er spielen möchte.
	 * 
	 * @return Liste mit Karten, die der Spieler ausgewaehlt hat
	 */
	public List selectCards(List boardCards);
	
	/*
	 * Method to check if a player still has cards or not
	 */
	public Boolean hasCards(Player player);

	public String getPlayerNameInput();
	
	public List getPlayerMove();
	
	public int getPlayerCountInput();

	public int generateId();
	
	public void removeFromHand(PlayerService player, List selectedCards);
	
	public void addToHand(PlayerService player, List selectedCards);
	
	
	
}
