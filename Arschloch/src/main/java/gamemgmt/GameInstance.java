  
package gamemgmt;

import java.util.List;

import cardmgmt.Card;
import playermgmt.Player;

/**
 * 
 *
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler 
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * 
 *
 *
 * Diese Klasse stellt die Instanz eines Spiels dar. 
 * Ein Spiel beinhaltet die Spieler, das momentane Rundenergebnis, die Karten, mit denen gerade gespielt wird und den aktuellen Spieler.
 * Hier läuft das Spiel im Großteil ab.
 *
 */
public class GameInstance {

	/**
	 * @param players - Liste der an einem Spiel beteiligten Spieler
	 * @param result - Siegerreihenfolge
	 * @param boardCards - Karte(n), die sich momentan auf dem Spielfeld befindet/n. Diese gilt es zu überspielen
	 * @param currentPlayer - Spieler, der gerade an der Reihe ist, einen Zug zu machen
	 */
	public List<Player> players;
	public List<Player> result;
	public List<Card> boardCards;
	public Player currentPlayer;
	
	/**
	 * Enthält die Informationen, die während eines Spiels vorrangig wichtig sind
	 * 
	 */
	public GameInstance() {
	}
	
	
	/**
	 * Getter und Setter der jeweiligen Felder/Attribute
	 * 
	 */
	public List<Player> getPlayers() {
		return players;
	}
	private void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Player> getResult() {
		return result;
	}

	private void setResult(List<Player> result) {
		this.result = result;
	}

	public List<Card> getBoardCards() {
		return boardCards;
	}

	private void setBoardCards(List<Card> boardCards) {
		this.boardCards = boardCards;
	}
	

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	private void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

}