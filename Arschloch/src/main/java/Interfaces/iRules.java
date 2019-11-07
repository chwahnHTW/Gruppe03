/**
 * 
 */
package Interfaces;

import java.util.List;

import Model.Player;
import Model.Rules;

/**
 *
 * @authors Kaya Löher | Kim Anh Nguyen | Christian Wahnsiedler Email-Adresse:
 *          s0564784@htw-berlin.de | s0563958@htw-berlin.de|
 *          s0557193@htw-berlin.de
 * 
 *
 */
public interface iRules {

	/**
	 * 
	 * @param rules: Regeln, die für ein Spiel gelten sollen.
	 */
	public void calculateRules(List<Player> playerList);

}
