/**
 * 
 */
package historymgmt;

import java.util.LinkedList;
import java.util.List;

import gamemgmt.GameInstance;
import gamemgmt.GameInstanceService;

/**
 *
 * @authors Kaya Löher | Kim Anh Nguyen | Christian Wahnsiedler Email-Adresse:
 *          s0564784@htw-berlin.de | s0563958@htw-berlin.de|
 *          s0557193@htw-berlin.de
 * 
 *
 */
public interface HistoryService {

	/**
	 * --> IHistory
	 * 
	 * Speichert das Ergebnis des Spiels in die Spielhistorie
	 */
	public void saveResultToHistory(GameInstance game);

	public void persist(GameInstanceService game);


	public List getResult(GameInstanceService game);

}
