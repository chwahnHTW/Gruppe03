package Model;

import java.awt.List;

/**
 * 
 *
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler 
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * 
 *
 *
 * Diese Klasse stellt die Instanz eines Spiels dar. 
 * Hier läuft das Spiel im Großteil ab.
 *
 */
public class GameInstance {

	Player[] players;
	String[] result;
	Card[] boardCards; //da es mehrere Karten sein können, die momentan auf dem Tisch liegen
	Player currentPlayer;

	/**
	 * Enthält die Informationen, die während eines Spiels vorrangig wichtig sind.
	 * 
	 * @param players ist ein Array aus den Spielern
	 */
	GameInstance(Player[] players, Player currentPlayer, Card[] currentBoard) {
		this.players = players;
		this.currentPlayer = currentPlayer;
		this.boardCards = currentBoard;
	}

	/**
	 * startet das Spiel
	 */
	private void startGame() {
	}

	/**
	 * beendet das Spiel
	 */
	private void endGame() {
	}

}