package kbe.historymgmt;

import kbe.JpaRepository.PlayerRepository;
import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Diese Klasse stellt die Spielhistorie dar.
 * In der Historie werden vorherige Spielergebnisse gespeichert.
 */
public interface HistoryService {


    /**
     * Speichert eine Spielinstanz in die Historie
     *
     * @param gameInstance - Abzuspeichernde Spielinstanz
     */
    void persist(GameInstance gameInstance);

    /**
     * Hier wird das letzte beendete Spiel aus der Hsitorie abgerufen, um dessen Result zu untersuchen
     * und zu prüfen, ob Karten getauscht werden müssen, oder nicht, sowie welcher Spieler anfängt
     *
     * @return : eine Spielinstanz
     */
    GameInstance getLastPlayedGame();


    /**
     * Speichert die Spielhistorie in einer CSV Datei ab
     *
     * @param instance
     */
    void saveToCSV(GameInstance instance);

}
