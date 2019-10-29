/**
 * 
 */
package Interfaces;

import Model.GameInstance;

/** 
*
* @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler 
* Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
* 
*
*/
public interface iFrontend {
	
	/**
	 * --> IFrontend
	 * 
	 * Das Spiel wird gestartet.
	 * 
	 * @return eine GameInstance
	 */
	public GameInstance startGame(GameInstance game);

	/**
	 * --> IFrontend
	 * 
	 * Das Spiel wird beendet.
	 */
	public void endGame(GameInstance game);
	
	/**
	 * --> IFrontend
	 * 
	 * GUI wird initialisiert.
	 * 
	 */
	public void init(GameInstance game);
}
