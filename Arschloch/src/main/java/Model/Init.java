package Model;

/**
 * 
 *
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler 
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * 
 * 
 * Hier wird das Spiel gestartet. 
 * Es wird eine GameInstance generiert.
 * 
 *
 */
public class Init {

	GameInstance gameInstance;

	/**
	 * Generiert eine Spiel-Instanz
	 * 
	 * @param gameInstance: welche Instanz des Spiels gespielt wird
	 */
	public Init(GameInstance gameInstance) {
		this.gameInstance = gameInstance;
	}

	/**
	 * Gibt die Anzahl der Spieler wieder
	 * 
	 * @param gameInstance: welche Instanz des Spiels gespielt wird
	 */
	public void getPlayercount(GameInstance gameInstance) {
	};

	/**
	 * Gibt die Namen der Spieler wieder
	 * 
	 * @param gameInstance: welche Instanz des Spiels gespielt wird
	 */
	public void getNames(GameInstance gameInstance) {
	}
}
