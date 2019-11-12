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
	
	/**
	 * Die Spielhistorie
	 * 
	 * @param fileName = Name der Datei, in der die Gesamtspielhistorie gespeichert wird
	 */
	String fileName = "example.csv";
	
	
	/**
	 * Die Spielhistorie
	 * 
	 *	@param history = Chronologische Liste aller vergangenen Spieldurchläufe
	 */
	

			
	List<GameInstance> history;
	
	/**
	 * @return @param fileName
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * @return @param fileName
	 */
	public List<GameInstance> getHistory() {
		return history;
	}
}
