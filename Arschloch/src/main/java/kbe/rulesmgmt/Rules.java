package kbe.rulesmgmt;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Class : Rules - Regeln, nach denen Karten verglichen und der jeweils erste Spieler einer neuen Runde ermittelt werden können.
 * Konfiguration über Inection versch. Implementierungen der jeweiligen Regel-Services
 */
@Component
public class Rules {


    /**
     * @param palyerRS - PlayerRulesService, der die Rollen der Spieler eines Spiels vergleicht, um denjenigen Spieler zu bestimmen, der in einer
     * Runde als erster eine Karte spielen darf.
     * Standard-Implementierung : Arschloch oder Spieler mit Karo 7 als erster
     * PresidentFirst-Implementierung : President oder Spieler mit Karo 7 als erster
     */
    @Autowired
    PlayerRulesService playerRS;

    /**
     * @param palyerRS - PlayerRulesService, der die Rollen der Spieler eines Spiels vergleicht, um denjenigen Spieler zu bestimmen, der in einer
     * Runde als erster eine Karte spielen darf.
     * Standard-Implementierung : Arschloch oder Spieler mit Karo 7 als erster
     * PresidentFirst-Implementierung : President oder Spieler mit Karo 7 als erster
     */
    @Autowired
    CardRulesService cardRS;

    private Player determineInitialPlayer(GameInstance gameInstance) {
        return playerRS.determineInitialPlayer(gameInstance);
    }

//    private List<Card> comapareCards(Card card1, Card card2) {
//        return cardRS.compareCards(card1, card2);
//    }
}
