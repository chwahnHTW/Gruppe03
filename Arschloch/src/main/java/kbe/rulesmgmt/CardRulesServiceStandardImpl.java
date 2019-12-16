package kbe.rulesmgmt;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardComparator;


@Component
public class CardRulesServiceStandardImpl implements CardRulesService {

	/**CardRulesServiceImplementierung zum Vergleich von Karten-Wertigkeiten
	 * Standard Implementierung : Wertigkeiten aufsteigend - 7-8-9-10-Bube-König-Ass
	 */
	@Override
	public List<Card> compareCards(List <Card> cards) {
		Collections.sort(cards, new CardComparator());
		return cards;
	}

}
