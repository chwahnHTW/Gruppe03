package cardmgmt;

import java.util.List;

import playermgmt.PlayerService;

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
public interface CardService {
	
	public List<Card> orderCardsByValue(List<Card> cards);
	
	public List<Card> generateDeck(int anzahlKarten);

}
