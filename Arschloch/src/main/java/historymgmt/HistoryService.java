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
	 * Speichert das Ergebnis des Spiels in die Spielhistorie
	 *
	 * @param game: eine Spielinstanz
	 */
	History saveResultToHistory(GameInstance game);

	/**
	 *
	 * @param game
	 */
	void persist(GameInstance game);

	/**
	 * Hier wird in einer Liste gespeichert, in welcher Reihenfolge die Spieler gewonnen haben.
	 *
	 * @param game: eine Spielinstanz
	 * @return: Liste von Spielern mit ihren Ergebnissen.
	 */
	List<Player> getResult(GameInstance game);

}
