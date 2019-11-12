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
     * GUI wird initialisiert. Keine GameInstanz
     *
     */
    public void init();

    /**
     * Das Spiel wird beendet.
     * @param game: Eine Spielinstanz
     */
    
    public void endRound(GameInstance game);
    
    /**
     * Eine GameInstanz wird erstellt und zurückgegeben.
     * GUI wird mit GameInstanz bestückt
     *
     */
    public GameInstance startGame();

    /**
     * ein Dialog zum Erfassen von Userinput ( Anzahl der für das kommende Spiel zu erstellenden Spieler )
     * @return Anzahl der zu erstellenden Spieler
     */
    public int getUserCountInput();
    
    /**
     * ein Dialog zum Erfassen von Userinput ( Name der für das kommende Spiel zu erstellenden Spieler )
     * @return Name der Spieler
     */
    public String getUserNameInput();
    
    /**
     * ein Dialog zum Erfassen von Userinput ( gespielte Karten pro Zug ) 
     * @return List<Card> Liste der vom Spieler gespielten Katen. null = Spielzug Pass
     */
    public List getPlayerMoveInput();

}
