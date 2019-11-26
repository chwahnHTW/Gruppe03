package rulesmgmt;

import java.util.List;

import org.springframework.stereotype.Component;

import cardmgmt.Card;
import gamemgmt.GameInstance;
import playermgmt.Player;


public interface PlayerRulesService {
    /**
     * Hier werden Karten in Bezug auf ihren Zahlenwert verglichen. Annotations ermöglichen hier später eine externe Konfiguration der Regeln
     * über Setzen der Rangfolge der Wertigkeiten.
     * Standard-Blatt
     *
     * @param card1 - erste zu vergleichende Karte
     * @param card2 - zweite zu vergleichende Karte
     * @return Liste in Reihenfolge der Kartenwertigekeiten, soriteirt nach Standard-Blatt
     */
    Player determineInitialPlayer(GameInstance gameInstance);

}
