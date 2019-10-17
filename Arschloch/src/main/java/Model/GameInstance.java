package Model;

import java.awt.List;

/**
 * 
 *
 * @author Kaya Löher, Kim Anh Ngyuen, Christian Wahnsiedler
 * Email-Adresse: s0564784@htw-berlin.de, ...
 *
 *
 * Diese Klasse stellt die Instanz eines Spiels dar. 
 *
 */
public class GameInstance {

	Player[] players;
	String[] result;
	List deck;
	Card boardCard;
	Player currentPlayer;

	/**
	 * 
	 * @param players ist ein Array aus den Spielern
	 */
	GameInstance(Player[] players) {
		this.players = players;
	}

	/**
	 * startet das Spiel
	 */
	private void startGame() {
	};

	/**
	 * beendet das Spiel
	 */
	private void endGame() {
	};

}