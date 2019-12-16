//package kbe.rulesmgmt;
//
//import java.util.Collections;
//import java.util.List;
//
//import org.springframework.stereotype.Component;
//
//import kbe.cardmgmt.Card;
//import kbe.cardmgmt.CardComparator;
//
//
//@Component
//public class CardRulesServiceReverseImpl implements CardRulesService{
//
//	/**CardRulesServiceImplementierung zum Vergleich von Karten-Wertigkeiten
//	 * Französisches Blatt  Implementierung : Reihenfolge von hinten
//	 */
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
//}
