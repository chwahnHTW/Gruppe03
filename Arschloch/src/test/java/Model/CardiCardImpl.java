package Model;

import Interfaces.iCard;
import cardmgmt.Card;

public class CardiCardImpl extends Card implements iCard {

	CardiCardImpl(int number, String symbol) {
		super(number, symbol);
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
	 * Gibt die Farbe einer Karte zurück.
	 * 
	 * @return: Farbe der Karte
	 */
	public String getSymbol() {
		return symbol;
	}

}
