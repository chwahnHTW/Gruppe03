package Model;

/**
 * 
 *
 * @author Kaya Löher, Kim Anh Ngyuen, Christian Wahnsiedler Email-Adresse:
 *         s0564784@htw-berlin.de, ...
 * 
 * 
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
	 * Gibt den Spieleraccount wieder
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
