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

    @Override
    public List<Card> sortCardsByValue(List<Card> cards) {
        // TODO Auto-generated method stub
    	/**
    	 * Karten nach Ihrem wert sortieren
    	 * Wert ist die zahl der karte: sieben am niedrigsten
    	 */
        return null;
        
        //cards.
        
        /**
         * sort mit neuer klasse implements comparator
         * card zahl value festlegen
         */
        
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
			
			// die 2 schlechtesten karten von arschloch herausfinden und in temp liste
			temp1.add(arschloch1.getHand().get(0)); //index 0
			temp1.add(arschloch1.getHand().get(1)); //index 1
			
			//die 2 besten Karten von praesident herasufinden und in temp liste
			int lastCard = president1.getHand().size()-1;
			int secondLastCard = president1.getHand().size()-2;
			
			temp1.add(president1.getHand().get(lastCard)); //index 2
			temp1.add(president1.getHand().get(secondLastCard)); // index 3
			
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
		
		}
		
		
		
	}

	@Override
	public void dealCardsToPlayers(GameInstance gameInstance) {
		// TODO Auto-generated method stub
		/**
		 * anzahl der spieler herausfinden
		 * 
		 * austeilender spieler übergeben
		 * der nächste spieler von dem beginnt
		 * so lange karten im deck: 
		 * durch spieler iterieren, in handcards add,
		 * hauptdeck muss eigentlich nicht geloescht werden
		 * modulo 
		 */
		
		//gemischtes deck erstellen mit 32 Karten
		List<Card> deck = shuffleDeck(generateDeck(32));
		//playerList fuer die spieler
		List <Player> playerList = gameInstance.getPlayers();
		//anzahl spieler ermittenln
		int anzahlPlayer = playerList.size();
		
		for (int i = 0; i < deck.size(); i++) {
			// i%anzahlPlayer, damit durhc jeden Player iteriert wird
			playerList.get(i % anzahlPlayer).setHand(deck.get(i));
		}
		
	}


}
