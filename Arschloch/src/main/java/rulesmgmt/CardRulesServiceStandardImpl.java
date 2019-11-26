package rulesmgmt;

import java.util.List;

import org.springframework.stereotype.Component;

import cardmgmt.Card;


@Component
public class CardRulesServiceStandardImpl implements CardRulesService {

	/**CardRulesServiceImplementierung zum Vergleich von Karten-Wertigkeiten
	 * Standard Implementierung : Wertigkeiten aufsteigend - 7-8-9-10-Bube-König-Ass
	 */
	@Override
	public List<Card> compareCards(Card card1, Card card2) {
		return null;
	}

}
