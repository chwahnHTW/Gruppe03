package historymgmt;

import java.util.List;

import gamemgmt.GameInstance;

/**
 *
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Nach jedem beendeten Spiel gibt es ein Ergbenis mit der Reihenfolge der Gewinner, den Regeln und den schon gespielten Spielen.
 * Hier wird die Spielhistorie gespeichert.
 *
 */
public class History {

	List<GameInstance> history;

	/**
	 * Die Spielhistorie
	 */
	public History() {
	}

//	/**
//	 * Speichern der Spielergebnisse in History
//	 */
//	public void persist() {
//	}
}
