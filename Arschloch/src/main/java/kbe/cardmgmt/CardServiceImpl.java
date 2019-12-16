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
	
    // wie karten mit bilder mappen? -> Card klasse mit to string?

	/**
	 * Sortiert eine Liste mit Karten nach ihren Zahlen.
	 * 
	 * @param
	 * @return
	 */
    @Override
    public List<Card> sortCardsByValue(List<Card> cards) {
        Collections.sort(cards, new CardComparator());
        return cards;
        
    }

    @Override
    public List<Card> generateDeck() { // default = 32 //optional
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
		
		// wenn playerlist mehr als 3 spieler hat
		if (gameInstance.getPlayers().size() >= 3) {
			
			Player arschloch1 = gameInstance.getResult().get(0);
			Player arschloch2 = gameInstance.getResult().get(1);
			Player president2 = gameInstance.getResult().get(roleListSize-2);
			Player president1 = gameInstance.getResult().get(roleListSize-1);
			
			//******************** FUER ARSCHLOCH 1 UND PRAESIDENT 1 ********************
		
			//um hier die Karten, die getauscht werden sollen, temporaer abzulegen
			List<Card> temp1 = new ArrayList<Card>();
			
			// die 2 schlechtesten (niedrigste Zahl) karten von arschloch herausfinden und in temp liste
			temp1.add(arschloch1.getHand().get(0)); //index 0
			temp1.add(arschloch1.getHand().get(1)); //index 1
			
			//die 2 besten Karten von praesident herasufinden und in temp liste
			int lastCard = president1.getHand().size()-1;
			int secondLastCard = president1.getHand().size()-2;
			temp1.add(president1.getHand().get(lastCard));
			temp1.add(president1.getHand().get(secondLastCard));
			
			//zu den Handkarten von arschloch und praesident
			president1.setHand(temp1.get(0));
			president1.setHand(temp1.get(1));
			arschloch1.setHand(temp1.get(2));
			arschloch1.setHand(temp1.get(3));
			
			//loeschen der getauschten Karten aus beiden spielern
			arschloch1.getHand().remove(0);
			arschloch1.getHand().remove(0); //weil index wieder nach vorn rutscht
			president1.getHand().remove(secondLastCard);
			president1.getHand().remove(lastCard);
			
			//******************** FUER ARSCHLOCH 2 UND PRAESIDENT 2 ********************
			
			List<Card> temp2 = new ArrayList<Card>();
			
			// die 2 schlechtesten karten von arschloch herausfinden und in temp liste
			temp1.add(arschloch2.getHand().get(0)); //index 0
			temp1.add(arschloch2.getHand().get(1)); //index 1
			
			//die 2 besten Karten von praesident herasufinden und in temp liste
			int lastCard2 = president2.getHand().size()-1;
			int secondLastCard2 = president2.getHand().size()-2;
			temp1.add(president2.getHand().get(lastCard2)); //index 2
			temp1.add(president2.getHand().get(secondLastCard2)); // index 3
			
			//zu den Handkarten von arschloch und praesident
			president2.setHand(temp2.get(0));
			president2.setHand(temp2.get(1));
			arschloch2.setHand(temp2.get(2));
			arschloch2.setHand(temp2.get(3));
			
			//loeschen der getauschten Karten aus beiden spielern
			arschloch2.getHand().remove(0);
			arschloch2.getHand().remove(0); //weil index wieder nach vorn rutscht
			president2.getHand().remove(secondLastCard2);
			president2.getHand().remove(lastCard2);
			
//			for (int i = 0; i < 2; i++) {
//			temp1.add(arschloch1.getHand().get(i));
//			arschloch1.getHand().remove(i);
//		}
		
		} else {
			Player arschloch = gameInstance.getResult().get(0);
			Player president = gameInstance.getResult().get(roleListSize-1);
			List<Card> temp = new ArrayList<Card>();
			
			// die 2 schlechtesten (niedrigste Zahl) karten von arschloch herausfinden und in temp liste
			temp.add(arschloch.getHand().get(0)); //index 0
			temp.add(arschloch.getHand().get(1)); //index 1
			
			//die 2 besten Karten von praesident herasufinden und in temp liste
			int lastCard = president.getHand().size()-1;
			int secondLastCard = president.getHand().size()-2;
			temp.add(president.getHand().get(lastCard));
			temp.add(president.getHand().get(secondLastCard));
			
			//zu den Handkarten von arschloch und praesident
			president.setHand(temp.get(0));
			president.setHand(temp.get(1));
			arschloch.setHand(temp.get(2));
			arschloch.setHand(temp.get(3));
			
			//loeschen der getauschten Karten aus beiden spielern
			arschloch.getHand().remove(0);
			arschloch.getHand().remove(0); //weil index wieder nach vorn rutscht
			president.getHand().remove(secondLastCard);
			president.getHand().remove(lastCard);
		}
	}

	@Override
	public void dealCardsToPlayers(GameInstance gameInstance) {
		// TODO Auto-generated method stub
		/**
		 * anzahl der spieler herausfinden
		 * austeilender spieler übergeben
		 * der nächste spieler von dem beginnt
		 * so lange karten im deck: 
		 * durch spieler iterieren, in handcards add,
		 * hauptdeck muss eigentlich nicht geloescht werden
		 * modulo 
		 */
		
		//gemischtes deck erstellen
		List<Card> deck = shuffleDeck(generateDeck());
		
		//anzahl spieler ermittenln
		int anzahlPlayer = gameInstance.players.size();
		
		for (int i = 0; i < deck.size(); i++) {
			// i%anzahlPlayer, damit durhc jeden Player iteriert wird
			gameInstance.players.get(i % anzahlPlayer).setHand(deck.get(i));
		}
		
	}


}
