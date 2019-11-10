package cardmgmt;


/**
 * 
 *
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler 
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * 
 * Diese Klasse stellt eine Karte dar.
 * Eine Karte besteht aus einer Zahl und einer Farbe.
 *
 */
public class Card{
	int number;
	String symbol;

	/**
	 * Der Konstruktor bildet eine Karte bestehend aus Zahl und Farbe der Karte.
	 * 
	 * @param number: Zahl einer Karte
	 * @param symbol: Farbe einer Karte
	 * @return
	 */
	Card(int number, String symbol) {
		this.number = number;
		this.symbol = symbol;
	}

	
}
