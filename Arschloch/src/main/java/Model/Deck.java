package Model;

/**
 * 
 * @author Kaya Löher, Kim Anh Ngyuen, Christian Wahnsiedler Email-Adresse:
 *         s0564784@htw-berlin.de, ...
 * 
 * 
 *         Stellt ein Kartendeck dar (4x8 Karten, 4 verschiedene Farben, 8
 *         verschiedene Zahlen)
 * 
 *         Farben: Karo, Herz, Kreuz, Pik Zahlen: 7, 8, 9, 10, Bube, Dame,
 *         König, Ass
 *
 */
public class Deck {

//  spades, clubs, hearts, and diamonds

	Card[] cards;

	/**
	 * Das Deck
	 */
	public Deck() {
	};

	/**
	 * generiert das Deck
	 * 
	 * @return das Deck
	 */
	public Deck createDeck() {
		// Füllen aller Arrays mit Zahlen 7-13 & Setzen der zu den Karten passenden
		// Bildern
		return new Deck();
	}

}
