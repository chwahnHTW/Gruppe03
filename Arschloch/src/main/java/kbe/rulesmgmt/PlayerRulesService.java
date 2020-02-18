package kbe.rulesmgmt;

import kbe.gamemgmt.GameInstance;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Stellt die Regeln für Spieler zur Verfügung.
 */
public interface PlayerRulesService {
    /**
     * Hier werden Karten in Bezug auf ihren Zahlenwert verglichen. Annotations ermöglichen hier später eine externe Konfiguration der Regeln
     * über Setzen der Rangfolge der Wertigkeiten.
     * Standard-Blatt
     *
     * @param gameInstance : die Spielinstanz
     * @return : Liste in Reihenfolge der Kartenwertigekeiten, soriteirt nach Standard-Blatt
     */
    void determineInitialPlayer(GameInstance gameInstance);

}