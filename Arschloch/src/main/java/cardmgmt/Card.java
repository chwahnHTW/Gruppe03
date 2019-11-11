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
	
	public enum Zahl {

		SIEBEN, ACHT, NEUN, ZEHN, BUBE , DAMEN, KOENIG, ASS;

	}

	public enum Symbol {

		KARO, HERZ, PIK, KREUZ;

	}

	final Zahl zahl;
	final Symbol symbol;
	
	public Card (Zahl zahl, Symbol symbol) {
		this.zahl = zahl;
		this.symbol = symbol;

	}
	
	public Zahl getZahl() {
		return zahl;
	}
	
	public Symbol getSymbol() {
		return symbol;
	}
	
	
	
//	int number;
//	String symbol;
//
//	/**
//	 * Der Konstruktor bildet eine Karte bestehend aus Zahl und Farbe der Karte.
//	 * 
//	 * @param number: Zahl einer Karte
//	 * @param symbol: Farbe einer Karte
//	 * @return
//	 */
//	Card(int number, String symbol) {
//		this.number = number;
//		this.symbol = symbol;
//	}
//	
//	/**
//	 * Gibt die Zahl einer Karte zurück.
//	 * 
//	 * @return: Zahl der Karte
//	 */
//	public int getNumber() {
//		return number;
//	};
//	
//	
//	/**
//	 * Gibt die Farbe einer Karte zurück.
//	 * 
//	 * @return: Farbe der Karte
//	 */
//	public String getSymbol() {
//		return symbol;
//	}

	
}
