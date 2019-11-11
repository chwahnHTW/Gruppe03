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
	List<Card> handCards;

	/**
	 * Ein Enum, welches die Rolle darstellt, welche ein Spieler haben kann.
	 */
	public enum Rolle {

		PRAESIDENT1, PRAESIDENT2, MITTELKIND, ARSCHLOCH1, ARSCHLOCH2;

	}

	/**
	 * Generiert einen Spieler.
	 * 
	 * @param name: Der Name des Spielers
	 * @param userid: Die Id des Spielers
	 * @param handCards: Die Karten, die der Spieler besitzt
	 */
	Player(String name, int userid, List<Card> handCards, Rolle role ) {
		this.name = name;
		this.userId = userid;
		this.handCards = handCards;
		this.role = role;
	}
	
	/**
	 * Gibt die Userid zurück
	 * 
	 * @return die userId ces Spielers
	 */
	public int getUserId(Player player) {
		return userId;
	}
	
	public void setUserId() {
		
	}
	
	public String getName(Player player) {
		return name;
	}
	
	public void setName() {
		
	}
	
	/**
	 * Gibt das Array der Karten, die der Spieler hat, zurück.
	 * 
	 * @return: Die Karten des Spielers
	 */
	public List<Card> getHand(Player player){
		return handCards;
	}

	/**
	 * setzt die Karten, die der Spieler bekommen soll.
	 * 
	 * @param cards: Die Karten des Spielers
	 */
	
	public void setHand(List<Card> cards, Player player) {
		
	}


}
