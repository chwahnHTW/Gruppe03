package kbe.rulesmgmt;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardComparator;
import org.springframework.stereotype.Service;

/**
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Hier wird die Regel implementiert, dass die Kartenwerte normal zählen, sodass die niedrigste Karte Sieben und die höchste Karte Ass ist.
 * Wertigkeiten aufsteigend - 7-8-9-10-Bube-König-Ass
 */
@Service
public class CardRulesServiceStandardImpl implements CardRulesService {

	@Override
	public List<Card> compareCards(List <Card> cards) {
		Collections.sort(cards, new CardComparator());
		return cards;
	}

}
