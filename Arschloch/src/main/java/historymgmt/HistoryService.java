package historymgmt;

import java.util.List;

import gamemgmt.GameInstance;
import playermgmt.Player;

/**
 *
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Diese Klasse stellt die Spielhistorie dar.
 * In der Historie werden vorherige Spielergebnisse gespeichert.
 *
 *
 */
public interface HistoryService {

	/**
	 * Speichert eine Spielinstanz in die Historie
	 * @param game - Abzuspeichernde Spielinstanz
	 */
	public void persist(GameInstance gameInstance);

	/**
	 * Hier wird das letzte beendete Spiel aus der Hsitorie abgerufen, um dessen Result zu untersuchen 
	 * und zu prüfen, ob Karten getauscht werden müssen, oder nicht, sowie welcher Spieler anfängt
	 *
	 * @param game: eine Spielinstanz
	 * @return: letzte gespeicehrte GameInstanz mit Result-Wert
	 */
	public GameInstance getLastPlayedGame();

}
