package kbe.cardmgmt;

import java.util.Comparator;

public class CardComparator implements Comparator<Card>{

	@Override
	public int compare(Card o1, Card o2) {
		return o1.getZahl().compareTo(o2.getZahl());
	}

}
