package kbe.rulesmgmt;

import java.util.List;

import kbe.cardmgmt.Card;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Hier werden Karten verglichen. Über Implementierung verschiedener Comparatoren wird hier später die Konfiguration der Regeln ermöglicht
 * In einem normalen Spiel 32 Karten.
 */


public interface CardRulesService {

    /**
     * Hier werden Karten in Bezug auf ihren Zahlenwert verglichen. Annotations ermöglichen hier später eine externe Konfiguration der Regeln
     * über Setzen der Rangfolge der Wertigkeiten.
     *
     * @param card1 - erste zu vergleichende Karte
     * @param card2 - zweite zu vergleichende Karte
     * @return Liste in Reihenfolge der Kartenwertigekeiten, soriert nach jeweiliger Regel 
     * 
     * Standard-Implementierung : 7-8-9-10-Bube-Dame-König-Ass
     * FranzösischesBlatt-Implementierung
     */
    List<Card> compareCards(Card card1, Card card2);

}
