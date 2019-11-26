package gamemgmt;

import java.util.List;
import org.springframework.stereotype.Component;

import cardmgmt.Card;
import playermgmt.Player;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Eine Klasse, die die Spielinstanz realisiert
 * Hier läuft das Spiel im Großteil ab.
 */


public interface GameInstanceService {

    /**
     * Eine neue Spielinstanz wird erzeugt und zurückgegeben
     *
     * @return GameInstanz
     */
    GameInstance startGame();

    /**
     * Der Spielstatus eines Spiels. Zu Beginn "Running", nachdem alle bis auf den letzten Speieler ihre Karten abgelegt haben auf Änderung auf "Finished"
     *
     * @param gameInstance : die Spielinstanz
     * @return : Der Status des Spiels. "Running" oder "Finished"
     */
    String calculateGameState(GameInstance gameInstance);

}