package rulesmgmt;

import java.util.List;

import org.junit.runners.Parameterized.Parameter;

import cardmgmt.Card;

public interface RulesService {

	
	
    /**
     * Hier werden Karten verglichen. Über Implementierung verschiedener Comparatoren wird hier später die Konfiguration der Regeln ermöglicht
     * @param anzahlKarten: In einem normalen Spiel 32 Karten.
     * @return: Eine Liste von Karten, entsprechend ihrer Anzahl, für eine Spielrund, gemischt
     */
	
// later : @value = standard annotation
	/**
	 * Hier werden Karten in Bezug auf ihren Zahlenwert verglichen. Annotations ermöglichen hier später eine externe Konfiguration der Regeln
	 * über Setzen der Rangfolge der Wertigkeiten. 
	 * Standard-Blatt
	 * @param card1 - erste zu vergleichende Karte
	 * @param card2 - zweite zu vergleichende Karte
	 * @return Liste in Reihenfolge der Kartenwertigekeiten, soriteirt nach Standard-Blatt
	 */
    public List<Card> compareStandard(Card card1, Card card2);
    
// later : @value = french annotation
	/**
	 * Hier werden Karten in Bezug auf ihren Zahlenwert verglichen. Annotations ermöglichen hier später eine externe Konfiguration der Regeln
	 * über Setzen der Rangfolge der Wertigkeiten. 
	 * Standard-Blatt
	 * @param card1 - erste zu vergleichende Karte
	 * @param card2 - zweite zu vergleichende Karte
	 * @return Liste in Reihenfolge der Kartenwertigekeiten, sortiert nach französischem Blatt
	 */
    public List<Card> compareFrench(Card card1, Card card2);
	
}
