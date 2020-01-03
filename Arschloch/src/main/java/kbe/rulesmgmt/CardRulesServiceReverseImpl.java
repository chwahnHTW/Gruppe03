package kbe.rulesmgmt;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardComparator;


@Component
@Qualifier("CardRulesServiceReverseImpl")
public class CardRulesServiceReverseImpl implements CardRulesService{

	/**CardRulesServiceImplementierung zum Vergleich von Karten-Wertigkeiten
	 * Französisches Blatt  Implementierung : Reihenfolge von hinten
	 */
//	@Override
//	public List<Card> compareCards(List<Card> cards) {
//		Collections.sort(cards, new CardComparator());
//		Collections.reverse(cards);
//		return null;
//	}
//
////	public List<Card> compareCardsALT(Card card1, Card card2) {
////
////		return null;
////	}
//
	@Override
	public int compareCardsReverse(Card c1, Card c2) {
//		Collections.sort(cards, new CardComparator());
//		return cards;
		
		if(c2 == null) {
			return 1;
		}
		if (c1.getZahl().getKartenWert() == c2.getZahl().getKartenWert()) {
			return 0; // beide sind gleich
		}
		else if(c2.getZahl().getKartenWert() < c1.getZahl().getKartenWert()) {
			return -1; //c2 ist kleiner
		}
		return 1; // c2 ist groesser
	}

	@Override
	public int compareCardsStandard(Card c1, Card c2) {
		// TODO Auto-generated method stub
		return 0;
	}
}
