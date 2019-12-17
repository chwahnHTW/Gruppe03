package kbe.cardmgmt;

import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class CardComparator implements Comparator<Card>{
	
	/*Klasse, um den Vergleich von Spielkarten durchzufuehren 
	 * return int - Vergleich groeßßer, kleiner oder gleich
	 * 
	 * */
	
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

}
