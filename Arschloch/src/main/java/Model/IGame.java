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
 * Das Interface für das gesamte Spiel. 
 * Es wird das Kartenspiel Arschloch dargestellt.
 *
 */
public interface IGame {

	/**
	 * --> IFrontend
	 * 
	 * Das Spiel wird gestartet.
	 * 
	 * @return eine GameInstance
	 */
	public GameInstance startGame(GameInstance game);

	/**
	 * --> IGameInstance
	 * 
	 * Die Anzahl der Spieler wird festgelegt.
	 * 
	 * @return Anzahl der Spieler
	 */
	public int setPlayercount(Player[] playercount);

	/**
	 * --> IPlayer
	 * 
	 * Ein Spieler wird erstellt.
	 * 
	 * @return ein Spieler
	 */
	public Player createPlayer(int userid, String name);

	/**
	 * --> IFrontend
	 * 
	 * Das Spiel wird beendet.
	 */
	public void endGame(GameInstance game);

	/**
	 * --> IGameInstance
	 * 
	 * In einer Runde kann jeder Spieler Karten spielen. Hier wählt der Spieler aus, welche Karte(n) von seiner Hand er spielen möchte.
	 */
	public LinkedList<Card> selectCards(Player player);

	/**
	 * --> IGameInstance
	 * 
	 * In einer Runde kann jeder Spieler Karten spielen. Hier spielt der Spieler seine Karte(n) aus.
	 */
	public void playCards(LinkedList<Card> selectedCards);

	/**
	 * --> IGameInstance
	 * 
	 * Am Anfang des Spiels werden zwischen Gewinner und Verlierer des letzten Spiels Karten getauscht. 
	 * Dies wird hier relaisiert.
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
	 * Hier wird das Ergebnis der Runde gesetzt. 
	 * Der Spieler, der als letzter Karten abgegeben hat, ist dann an der Reihe.
	 */
	public void setResult(GameInstance game);

	/**
	 * --> IGameInstance
	 * 
	 * Das Ergebnis der letzten Runde wird hier gespeichert, um die Reihenfolge der Spieler für weitere Züge festzulegen.
	 * 
	 * @return Das Ergebnis der letzte Runde
	 */
	public LinkedList<String> getResult(GameInstance game);

	/**
	 * --> IGameInstance
	 * 
	 * Vergleich der Karten der Hand eines Spielers, um zu ermitteln, welche Karte die höchste ist. 
	 * Kann gespielte Karten mit boardCards vergleichen.
	 * 
	 * @return Reihenfolge der Karten der Spieler
	 */
	public Comparable<Card> compareCards(Player[] players);

	/**
	 * --> IGameInstance
	 * 
	 * Der Spielstatus eines Spiels.
	 * Zu Beginn "Running", nachdem alle bis auf den letzten Speieler ihre Karten abgelegt haben auf Änderung auf "Finished"
	 * 
	 * @return Der Status des Spiels. "Running" oder "Finished"
	 */
	public String getGameState(GameInstance game);

	/**
	 * --> IGameInstance
	 * 
	 * Gibt den ersten Spieler zurück. Entweder das Arschloch oder der Spieler mit der Karo7.
	 * 
	 * @return der Spieler, der das Spiel beginnen darf
	 */
	public Player getInitialPlayer(Player[] players);

	/**
	 * --> IHistory
	 * 
	 * Speichert das Ergebnis des Spiels in die Spielhistorie
	 */
	public void saveResultToHistory(GameInstance game);

	/**
	 * --> IFrontend
	 * 
	 * GUI wird initialisiert.
	 * 
	 */
	public void init(GameInstance game);
}
