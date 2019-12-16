package kbe.cardmgmt;

import java.util.Comparator;

public class CardComparator implements Comparator<Card>{
	
	@Override
	public int compare(Card o1, Card o2) {
		if(o2 == null) {
			return 1;
		}
		if (o1.getZahl().getKartenWert() == o2.getZahl().getKartenWert()) {
			return 0; // beide sind gleich
		}
		else if(o1.getZahl().getKartenWert() < o2.getZahl().getKartenWert()) {
			return -1; //o1 ist kleiner
		}
		return 1; // o1 ist groesser
	}
	
//	private int compare2(Card o1, Card o2) {
//		return o1.getZahl().compareTo(o2.getZahl());
//	}
}

//else if(o1 == null || o2 == null || o1 == null && o2 == null) {
//	return -1; //
//}