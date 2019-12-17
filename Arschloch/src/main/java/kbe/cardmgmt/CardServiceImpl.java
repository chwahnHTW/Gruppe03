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

		Player president = gameInstance.getResult().get(0);//praesident, da er zuerst in die liste
		Player arschloch = gameInstance.getResult().get(3);//arschloch, da er als letztes in die liste hinzugefuegt wird
		sortCardsByValue(president.getHand()); //sortieren der Karten nach Zahlenwert
		sortCardsByValue(arschloch.getHand()); //sortieren der Karten nach Zahlenwert

		//um hier die Karten, die getauscht werden sollen, temporaer abzulegen
		List<Card> temp1 = new ArrayList<Card>();



		// die 2 besten (hoechste Zahl) karten von arschloch herausfinden und in temp liste
		int handArschloch = arschloch.getHand().size();
		temp1.add(arschloch.getHand().get(handArschloch-2)); //index 0
		temp1.add(arschloch.getHand().get(handArschloch-1)); //index 1

		//die 2 schlechtesten Karten von praesident herasufinden und in temp liste
		temp1.add(president.getHand().get(0));
		temp1.add(president.getHand().get(1));

		//zu den Handkarten von arschloch und praesident
		president.setHand(temp1.get(0));
		president.setHand(temp1.get(1));
		arschloch.setHand(temp1.get(2));
		arschloch.setHand(temp1.get(3));

		//loeschen der getauschten Karten aus beiden spielern
		arschloch.getHand().remove(handArschloch-2);
		arschloch.getHand().remove(handArschloch-1); //weil index wieder nach vorn rutscht
		president.getHand().remove(0);
		president.getHand().remove(1);
	}

	@Override
	public void dealCardsToPlayers(GameInstance gameInstance) {

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