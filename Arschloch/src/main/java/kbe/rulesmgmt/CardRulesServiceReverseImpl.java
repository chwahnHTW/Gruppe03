package kbe.rulesmgmt;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardComparator;

/**
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Hier wird die Regel implementiert, dass die Kartenwerte von hinten zählen, sodass die niedrigste Karte Ass und die höchste Karte Sieben ist.
 * Wertigkeiten aufsteigend - Ass-König-Bube-10-9-8-7
 */
@Component
public class CardRulesServiceReverseImpl implements CardRulesService{

	@Override
	public List<Card> compareCards(List<Card> cards) {
		Collections.sort(cards, new CardComparator());
		Collections.reverse(cards);
		return null;
	}
}
