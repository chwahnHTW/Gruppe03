package frontendmgmt;

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
    GameInstance startGame(GameInstance game);

    /**
     * Das Spiel wird beendet.
     *
     * @param game: Eine Spielinstanz
     */
    void endGame(GameInstance game);

    /**
     * GUI wird initialisiert.
     *
     * @param game: Eine Spielinstanz
     */
    void init(GameInstance game);

    /**
     *
     */
    void getUserCountInput();
}
