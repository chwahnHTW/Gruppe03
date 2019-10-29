/**
 * 
 */
package Interfaces;

import java.util.LinkedList;

import Model.Card;
import Model.GameInstance;
import Model.Player;

/**
 *
 * @authors Kaya Löher | Kim Anh Nguyen | Christian Wahnsiedler Email-Adresse:
 *          s0564784@htw-berlin.de | s0563958@htw-berlin.de|
 *          s0557193@htw-berlin.de
 * 
 *
 */
public interface iGameInstance {

	/**
	 * --> IGameInstance
	 * 
	 * Die Anzahl der Spieler wird festgelegt.
	 * 
	 * @return Anzahl der Spieler
	 */
	public int setPlayercount(Player[] playercount);

	/**
	 * --> IGameInstance
	 * 
	 * In einer Runde kann jeder Spieler Karten spielen. Hier wählt der Spieler aus,
	 * welche Karte(n) von seiner Hand er spielen möchte.
	 * 
	 * @return Liste mit Karten, die der Spieler ausgewaehlt hat
	 */
	public LinkedList<Card> selectCards(Player player);

	/**
	 * --> IGameInstance
	 * 
	 * @Parameters: selectedCards
	 * 
	 *              In einer Runde kann jeder Spieler Karten spielen. Hier spielt
	 *              der Spieler seine Karte(n) aus. Die Karten wurden in der Methode
	 *              selectCards ausgewaehlt
	 */
	public void playCards(LinkedList<Card> selectedCards);

	/**
	 * --> IGameInstance
	 * 
	 * Am Anfang des Spiels werden zwischen Gewinner und Verlierer des letzten
	 * Spiels Karten getauscht. Dies wird hier relaisiert.
	 */
	public void swapCards(GameInstance game);

	/**
	 * --> IGameInstance
	 * 
	 * Der Spieler, der in einer Runde an der Reihe ist wird hier ermittelt.
	 * 
	 * @return der akutelle Spieler einer Runde
	 */
	public Player getCurrentPlayer(GameInstance game);

	/**
	 * IGameInstance
	 * 
	 * Der Spieler, der als nächstes an der Reihe ist.
	 * 
	 * @return Der nächste Spieler
	 */
	public Player getNextPlayer(GameInstance game);

	/**
	 * --> IGameInstance
	 * 
	 * Hier wird das Ergebnis der Runde gesetzt. Der Spieler, der als letzter Karten
	 * abgegeben hat, ist dann an der Reihe.
	 */
	public void setResult(GameInstance game);

	/**
	 * --> IGameInstance
	 * 
	 * Das Ergebnis der letzten Runde wird hier gespeichert, um die Reihenfolge der
	 * Spieler für weitere Züge festzulegen.
	 * 
	 * @return Das Ergebnis der letzte Runde
	 */
	public LinkedList<String> getResult(GameInstance game);

	/**
	 * --> IGameInstance
	 * 
	 * Vergleich der Karten der Hand eines Spielers, um zu ermitteln, welche Karte
	 * die höchste ist. Kann gespielte Karten mit boardCards vergleichen.
	 * 
	 * @return Reihenfolge der Karten der Spieler
	 */
	public Comparable<Card> compareCards(Player[] players);

	/**
	 * --> IGameInstance
	 * 
	 * Der Spielstatus eines Spiels. Zu Beginn "Running", nachdem alle bis auf den
	 * letzten Speieler ihre Karten abgelegt haben auf Änderung auf "Finished"
	 * 
	 * @return Der Status des Spiels. "Running" oder "Finished"
	 */
	public String getGameState(GameInstance game);

	/**
	 * --> IGameInstance
	 * 
	 * Gibt den ersten Spieler zurück. Entweder das Arschloch oder der Spieler mit
	 * der Karo7.
	 * 
	 * @return der Spieler, der das Spiel beginnen darf
	 */
	public Player getInitialPlayer(Player[] players);

}
