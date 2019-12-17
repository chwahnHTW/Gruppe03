package kbe.cardmgmt;

import org.springframework.stereotype.Service;

import java.util.Comparator;
/**
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Diese Klasse stellt einen Comparator zum Vergleich von Karten dar.
 * Zwei Card-Objekte werden anhand ihres Kartenwertes verglichen.
 */
@Service
public class CardComparator implements Comparator<Card>{
	
	@Override
	public int compare(Card o1, Card o2) {
		if(o2 == null) {
			return 1;
		}
		if (o1.getZahl().getKartenWert() == o2.getZahl().getKartenWert()) {
			return 0;
		}
		else if(o1.getZahl().getKartenWert() < o2.getZahl().getKartenWert()) {
			return -1;
		}
		return 1;
	}
}
