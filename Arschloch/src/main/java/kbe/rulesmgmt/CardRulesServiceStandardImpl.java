package kbe.rulesmgmt;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardComparator;
import org.springframework.stereotype.Service;


@Service
//@Qualifier("CardRulesServiceStandardImpl")
public class CardRulesServiceStandardImpl implements CardRulesService {

	/**CardRulesServiceImplementierung zum Vergleich von Karten-Wertigkeiten
	 * Standard Implementierung : Wertigkeiten aufsteigend - 7-8-9-10-Bube-König-Ass
	 */
	@Override
	public int compareCardsStandard(Card c1, Card c2) {
//		Collections.sort(cards, new CardComparator());
//		return cards;
		
		if(c2 == null) {
			return 1;
		}
		if (c1.getZahl().getKartenWert() == c2.getZahl().getKartenWert()) {
			return 0; // beide sind gleich
		}
		else if(c1.getZahl().getKartenWert() < c2.getZahl().getKartenWert()) {
			return -1; //c1 ist kleiner
		}
		return 1; // c1 ist groesser
	}

	@Override
	public int compareCardsReverse(Card c1, Card c2) {
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
}
