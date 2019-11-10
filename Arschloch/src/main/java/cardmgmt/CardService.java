package cardmgmt;

import java.util.List;

import playermgmt.PlayerService;

public interface CardService {

	
	/**
	 * Gibt die Zahl einer Karte zurück.
	 * 
	 * @return: Zahl der Karte
	 */
	public int getNumber();
	
	
	/**
	 * Gibt die Farbe einer Karte zurück.
	 * 
	 * @return: Farbe der Karte
	 */
	public String getSymbol();
	
	
	public List orderCardsByValue(List cards);


	public void removeFromHand(PlayerService player, List selectedCards);
	
	public void addToHand(PlayerService player, List selectedCards);
	
}
