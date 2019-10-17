package Model;

/**
 * 
 *
 * @author Kaya Löher, Kim Anh Ngyuen, Christian Wahnsiedler Email-Adresse:
 *         s0564784@htw-berlin.de, ...
 * 
 * 
 * 
 *         Stellt eine Karte dar.
 *
 */
public class Card {
	int number;
	String symbol;

	/**
	 * Bildet eine Karte
	 * 
	 * @param number: Zahl einer Karte
	 * @param symbol: Farbe/Symbol einer Karte
	 */
	Card(int number, String symbol) {
		this.number = number;
		this.symbol = symbol;
	}

	/**
	 * gibt die Zahl einer Karte zurück
	 * 
	 * @return: Zahl der Karte
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * setzt die Zahl einer Karte
	 * 
	 * @param number: Zahl der Karte
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * gibt die Farbe (Herz, Pik, Kreuz, Karo) einer Karte zurück
	 * 
	 * @return: Farbe der Karte
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * setzt die Farbe einer Karte
	 * 
	 * @param symbol: Farbe einer Karte
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
