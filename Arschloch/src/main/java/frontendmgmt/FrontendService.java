package frontendmgmt;

import java.util.List;

import cardmgmt.Card;
import gamemgmt.GameInstance;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * * Hier wird das Frontend realisiert.
 */
public interface FrontendService {

    /**
     * Die GUI wird hier initialisiert.
     * es wird keine Spielinstanz erstellt.
     */
    void init();

    /**
     * Das Spiel wird beendet.
     *
     * @param game: Eine Spielinstanz
     */
    void endRound(GameInstance game);

    /**
     * Eine Spielinstanz wird erstellt und zurückgegeben.
     * GUI wird mit Spielinstanz bestückt
     *
     * @return : eine Spielinstanz
     */
    GameInstance startGame();

    /**
     * ein Dialog zum Erfassen von Userinput ( Anzahl der für das kommende Spiel zu erstellenden Spieler )
     *
     * @return Anzahl der zu erstellenden Spieler
     */
    int getUserCountInput();

    /**
     * ein Dialog zum Erfassen von Userinput ( Name der für das kommende Spiel zu erstellenden Spieler )
     *
     * @return Name der Spieler
     */
    String getUserNameInput();

    /**
     * ein Dialog zum Erfassen von Userinput ( gespielte Karten pro Zug )
     *
     * @return List<Card> Liste der vom Spieler gespielten Katen. null = Spielzug Pass
     */
    List<Card> getPlayerMoveInput();

}
