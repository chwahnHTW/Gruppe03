/**
 * 
 */
package Interfaces;

import Model.GameInstance;

/**
 *
 * @authors Kaya Löher | Kim Anh Nguyen | Christian Wahnsiedler Email-Adresse:
 *          s0564784@htw-berlin.de | s0563958@htw-berlin.de|
 *          s0557193@htw-berlin.de
 * 
 *
 */
public interface iHistory {

	/**
	 * --> IHistory
	 * 
	 * Speichert das Ergebnis des Spiels in die Spielhistorie
	 */
	public void saveResultToHistory(GameInstance game);

}
