/**
 * 
 */
package Model;

import java.util.LinkedList;

/**
 * 
 *
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler 
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * 
 * 
 *
 */
public interface IGame {
	
	/**
	 * Das Spiel wird gestartet.
	 * @return eine GameInstance
	 */
	public GameInstance startGame();
	
	/**
	 * Die Anzahl der Spieler wird festgelegt.
	 * @return Anzahl der Spieler
	 */
	public int setPlayercount();
	
	/**
	 * Ein Spieler wird erstellt.
	 * @return ein Spieler
	 */
	public Player createPlayer();
	
	/**
	 * Das Spiel wird beendet.
	 */
	public void endGame();
	
	/**
	 * In einer Runde kann jeder Spieler Karten spielen.
	 * Hier spielt der Spieler seine Karte(n) aus.
	 */
	public void playCards();
	
	/**
	 * Am Anfang des Spiels werden zwischen Gewinner und Verlierer des letzten Spiels Karten getauscht. 
	 * Dies wird hier relaisiert.
	 */
	public void swapCards();
	
	/**
	 * Der Spieler, der in einer Runde an der Reihe ist wird hier ermittelt.
	 * @return der akutelle Spieler einer Runde
	 */
	public Player getCurrentPlayer();
	
	/**
	 * Der Spieler, der als nächstes an der Reihe ist.
	 * @return Der nächste Spieler
	 */
	public Player getNextPlayer();
	
	/**
	 * Hier wird das Ergebnis der Runde gesetzt.
	 * Der Spieler, der als letzter Karten abgegeben hat ist dann an der Reihe.
	 */
	public void setResult();
	
	/**
	 * 
	 * @return Das Ergebnis der letzte Runde
	 */
	public LinkedList<String> getResult();
	
	/**
	 * Vergleich der Karten der Hand eines Spielers, um zu ermitteln, welche Karte die höchste ist.
	 * @return 
	 */
	public Comparable<Card> compareCards();
	
	/**
	 * 
	 * @return Der Status des Spiels
	 */
	public String gameState();
	
	/**
	 * Gibt den ersten Spieler zurück.
	 * Entweder das Arschloch oder der Spieler mit der Karo7
	 * @return
	 */
	public Player getInitialPlayer();
	
	/**
	 * Speichert das Ergebnis des Spiels in die Spielhistorie
	 */
	public void saveResultToHistory();
}
