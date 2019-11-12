package frontendmgmt;

import java.util.List;

import gamemgmt.GameInstance;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * * Hier wird das Frontend realisiert.
 */
public interface FrontendService {

    /**
     * Das Spiel wird gestartet.
     *
     * @param game: eine Spielinstanz
     * @return: das Ergebnis des Spiels als Spielinstanz
     */
    GameInstance startRound();

    /**
     * Das Spiel wird beendet.
     *
     * @param game: Eine Spielinstanz
     */
    void endRound(GameInstance game);

    /**
     * GUI wird initialisiert.
     *
     * @param game: Eine Spielinstanz
     */
    GameInstance init();

    /**
     *
     */
    int getUserCountInput();
    
    
    String getUserNameInput();
    
    
    List getPlayerMoveInput();
}
