package kbe.cardmgmt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import org.springframework.stereotype.Service;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */

@Service
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
		
		System.out.println("SwapCards for President1 and Arschloch1");
		Player president1 = gameInstance.getResult().get(0);//erster praesident, da er zuerst in die liste
		Player president2 = gameInstance.getResult().get(1); //zweiter praesident, da er als zweites in die Liste
		int resultSize = gameInstance.getResult().size();
		Player arschloch1 = gameInstance.getResult().get(resultSize-1);//arschloch, da er als letztes in die liste hinzugefuegt wird
		Player arschloch2 = gameInstance.getResult().get(resultSize-2);//arschloch, da er als vorletztes in die liste hinzugefuegt wird
		sortCardsByValue(president1.handCards); //sortieren der Karten nach Zahlenwert
		sortCardsByValue(president2.handCards); //sortieren der Karten nach Zahlenwert
		sortCardsByValue(arschloch1.handCards); //sortieren der Karten nach Zahlenwert
		sortCardsByValue(arschloch2.handCards); //sortieren der Karten nach Zahlenwert

		//um hier die Karten, die getauscht werden sollen, temporaer abzulegen
		List<Card> temp1 = new ArrayList<Card>();

		// die 2 besten (hoechste Zahl) karten von arschloch herausfinden und in temp liste
		int handArschloch1 = arschloch1.handCards.size();
		temp1.add(arschloch1.handCards.get(handArschloch1-2)); //index 0
		temp1.add(arschloch1.handCards.get(handArschloch1-1)); //index 1
		
		//die 2 schlechtesten Karten von praesident herasufinden und in temp liste
		temp1.add(president1.handCards.get(0));
		temp1.add(president1.handCards.get(1));

		//zu den Handkarten von arschloch und praesident
		president1.setHand(temp1.get(0));
		president1.setHand(temp1.get(1));
		arschloch1.setHand(temp1.get(2));
		arschloch1.setHand(temp1.get(3));

		//loeschen der getauschten Karten aus beiden spielern
		arschloch1.handCards.remove(arschloch1.handCards.size()-3);
		arschloch1.handCards.remove(arschloch1.handCards.size()-3); //weil index wieder nach vorn rutscht
		
		president1.handCards.remove(0);
		president1.handCards.remove(0);
		
		if(gameInstance.getResult().size()>3) {
			System.out.println("SwapCards for President2 and Arschloch2");
			//um hier die Karten, die getauscht werden sollen, temporaer abzulegen
			List<Card> temp2 = new ArrayList<Card>();

			// die 2 besten (hoechste Zahl) karten von arschloch herausfinden und in temp liste
			int handArschloch2 = arschloch2.handCards.size();
			temp2.add(arschloch2.handCards.get(handArschloch2-2)); //index 0
			temp2.add(arschloch2.handCards.get(handArschloch2-1)); //index 1
			
			//die 2 schlechtesten Karten von praesident herasufinden und in temp liste
			temp2.add(president2.handCards.get(0));
			temp2.add(president2.handCards.get(1));

			//zu den Handkarten von arschloch und praesident
			president2.setHand(temp2.get(0));
			president2.setHand(temp2.get(1));
			arschloch2.setHand(temp2.get(2));
			arschloch2.setHand(temp2.get(3));

			//loeschen der getauschten Karten aus beiden spielern
			arschloch2.handCards.remove(arschloch2.handCards.size()-3);
			arschloch2.handCards.remove(arschloch2.handCards.size()-3); //weil index wieder nach vorn rutscht
			
			president2.handCards.remove(0);
			president2.handCards.remove(0);
		}
		
	}

	@Override
	public void dealCardsToPlayers(GameInstance gameInstance) {

		//gemischtes deck erstellen
		List<Card> deck = shuffleDeck(generateDeck());

		//anzahl spieler ermittenln
		int anzahlPlayer = gameInstance.getPlayers().size();
		
		for (int i = 0; i < deck.size(); i++) {
			// i%anzahlPlayer, damit durhc jeden Player iteriert wird
			gameInstance.getPlayers().get(i % anzahlPlayer).setHand(deck.get(i));
//			gameInstance.getPlayers().get(i % anzahlPlayer).handCards.add((deck.get(i)));
		}

	}



}