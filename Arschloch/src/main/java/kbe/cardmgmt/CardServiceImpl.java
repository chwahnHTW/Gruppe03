package kbe.cardmgmt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */

@Component
public class CardServiceImpl implements CardService {

    @Override
    public List<Card> orderCardsByValue(List<Card> cards) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Card> generateDeck(int anzahlKarten) { // default = 32 //optional
    	List<Card> deck = new ArrayList<Card>();
    	
    	for (Card.Symbol symbol : Card.Symbol.values()) {
    		for (Card.Zahl zahl : Card.Zahl.values()) {
    			Card card = new Card();
    			card.zahl = zahl;
    			card.symbol = symbol;
    			deck.add(card);
    		}
    	}
        return deck;
    }

    @Override
    public List<Card> shuffleDeck(List<Card> deck) {
    	/**
    	 * liste deck rein
    	 * inhalt mischen
    	 */
        Collections.shuffle(deck);
    	
        return deck;
    }

	@Override
	public void swapCards(GameInstance gameInstance) {
		// TODO Auto-generated method stub
		/**
		 * beste und schlechtester Spieler ermitteln
		 * 
		 * ab 4 spielern: die schlechtesten und zweitschlechtesten
		 * 
		 * von denen die schlechteste/beste karte ermitteln (x2)
		 * karten tauschen
		 */
		
		int roleListSize = gameInstance.getResult().size();
		
		// wenn playerlist mehr als 4 spieler hat
		if (gameInstance.getPlayers().size() >= 3) {
			
			Player arschloch1 = gameInstance.getResult().get(0);
			Player arschloch2 = gameInstance.getResult().get(1);
			Player president2 = gameInstance.getResult().get(roleListSize-1);
			Player president1 = gameInstance.getResult().get(roleListSize-2);
			
			List<Card> temp1 = new ArrayList<Card>();
//			for (int i = 0; i < 2; i++) {
//				temp1.add(arschloch1.getHand().get(i));
//				arschloch1.getHand().remove(i);
//			}
			
			/**
			 * karten von arschloch löschen und zur temp liste
			 */
			temp1.add(arschloch1.getHand().get(0));
			temp1.add(arschloch1.getHand().get(1));
			arschloch1.getHand().remove(0);
			arschloch1.getHand().remove(0); //weil index wieder nach vorn rutscht
			
			
			
			
			
			gameInstance.getResult().get(roleListSize-1); // ueber die result liste den 1. praesident
			gameInstance.getResult().get(roleListSize-2); // ueber die result liste den 2. praesident
			
		
		}
		
		
		
	}

	@Override
	public void dealCardsToPlayers(GameInstance gameInstance) {
		// TODO Auto-generated method stub
		/**
		 * austeilender spieler übergeben
		 * der näcshte spieler von dem beginnt
		 * so lange karten im deck: 
		 * durch spieler iterieren, in handcards add, hauptdeck löschen
		 * modulo 
		 */
		
	}


}
