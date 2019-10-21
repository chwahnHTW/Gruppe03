package Model;

import java.util.LinkedList;

public class Card {
	int number;
	String symbol;

	enum color_val {
//		enums lassen sich am besten in eigenen Klassen generieren, auf die dann nur zugegriffen wird.
		HERZ, KARO, PIK, KREUZ
	}

//	enum numb_val { 
//	In einem Enum lassen sich keine Zahlen abbilden, steht zur Diskussion, ob es hier sinnvoll ist.
//		BUBE, DAME, KÖNIG, ASS
//	};

	
	/**
	 * Bildet eine Karte bestehend aus Zahl und Farbe der Karte.
	 * 
	 * @param number: Zahl einer Karte
	 * @param symbol: Farbe/Symbol einer Karte
	 * @return 
	 */
	Card(int number, String symbol) {
		this.number = number;
		this.symbol = symbol;
		
	}

	/**
	 * Gibt die Zahl einer Karte zurück.
	 * 
	 * @return: Zahl der Karte
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Setzt die Zahl einer Karte.
	 * 
	 * @param number: Zahl der Karte
	 */
	public void setNumber(int number) {
		this.number = number;
	} 

	/**
	 * Gibt die Farbe/Symbol einer Karte zurück
	 * 
	 * @return: Farbe/Symbol der Karte
	 */
	public String getSymbol() {
		return symbol;
	} 

	/**
	 * Setzt die Farbe/Symbol einer Karte
	 * 
	 * @param symbol: Farbe/Symbol einer Karte
	 */
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

}
