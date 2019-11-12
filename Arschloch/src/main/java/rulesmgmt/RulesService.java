package rulesmgmt;

import java.util.List;

import cardmgmt.Card;

public interface RulesService {

	
	
    /**
     * Hier werden Karten verglichen. Über Implementierung verschiedener Comparatoren wird hier später die Konfiguration der Regeln ermöglicht
     * @param anzahlKarten: In einem normalen Spiel 32 Karten.
     * @return: Eine Liste von Karten, entsprechend ihrer Anzahl, für eine Spielrund, gemischt
     */
    public List<Card> compareStandard(Card card1, Card card2);
    
    
    public List<Card> compareFrench(Card card1, Card card2);
	
}
