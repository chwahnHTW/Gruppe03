package kbe.historymgmt;

import kbe.gamemgmt.GameInstance;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Diese Klasse stellt die Spielhistorie dar.
 * In der Historie werden vorherige Spielergebnisse gespeichert.
 */
@Deprecated
public interface HistoryService {

//    void tueEtwas();

    /**
     * Speichert eine Spielinstanz in die Historie
     *
     * @param gameInstance - Abzuspeichernde Spielinstanz
     */
    void persist(GameInstance gameInstance);

    /**
     * Speichert die Spielhistorie in einer CSV Datei ab
     *
     * @param instance
     */
    void saveToCSV(GameInstance instance);



}