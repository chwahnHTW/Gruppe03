/**
 * 
 */
package gamemgmt;
import java.util.LinkedList;
import java.util.List;

import cardmgmt.Card;
import gamemgmt.GameInstance;
import playermgmt.Player;
import playermgmt.PlayerService;





/**
 *
 * @authors Kaya Löher | Kim Anh Nguyen | Christian Wahnsiedler Email-Adresse:
 *          s0564784@htw-berlin.de | s0563958@htw-berlin.de|
 *          s0557193@htw-berlin.de
 * 
 *
 */
public interface GameInstanceService {

	/**
	 * --> IGameInstance
	 * 
	 * Die Anzahl der Spieler wird vom User eingegeben und erfasst.
	 * 
	 * @return Anzahl der Spieler
	 */
	public int determinePlayercount();
	
	/**
	 * --> IGameInstance
	 * 
	 * Gemäß des in determinePlayercount ermittleten Wertes werden Spieler erstellt
	 * 
	 * @return Anzahl der Spieler
	 */
	public void setPlayercount(int playerCount);

	/**
	 * --> IGameInstance
	 * 
	 * In einer Runde kann jeder Spieler Karten spielen. Hier wählt der Spieler aus,
	 * welche Karte(n) von seiner Hand er spielen möchte.
	 * 
	 * @return Liste mit Karten, die der Spieler ausgewaehlt hat
	 */
	public List selectCards(PlayerService player);

	/**
	 * --> IGameInstance
	 * 
	 * @Parameters: selectedCards
	 * 
	 *              In einer Runde kann jeder Spieler Karten spielen. Hier spielt
	 *              der Spieler seine Karte(n) aus. Die Karten wurden in der Methode
	 *              selectCards ausgewaehlt
	 */
	public void playCards(PlayerService player, List selectedCards);

	/**
	 * --> IGameInstance
	 * 
	 * Am Anfang des Spiels werden zwischen Gewinner und Verlierer des letzten
	 * Spiels Karten getauscht. Dies wird hier relaisiert.
	 */
	public void swapCards(List players);

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
	public PlayerService getNextPlayer() throws NullPointerException;

	/**
	 * --> IGameInstance
	 * 
	 * Hier wird das Ergebnis der Runde gesetzt. Der Spieler, der als letzter Karten
	 * abgegeben hat, ist dann an der Reihe.
	 */
	public List getResult(GameInstanceService game);

	/**
	 * --> IGameInstance
	 * 
	 * Das Ergebnis der letzten Runde wird hier gespeichert, um die Reihenfolge der
	 * Spieler für weitere Züge festzulegen.
	 * 
	 * @return Das Ergebnis der letzte Runde
	 */
	public void setResult(GameInstanceService game);


	/**
	 * --> IGameInstance
	 * 
	 * Der Spielstatus eines Spiels. Zu Beginn "Running", nachdem alle bis auf den
	 * letzten Speieler ihre Karten abgelegt haben auf Änderung auf "Finished"
	 * 
	 * @return Der Status des Spiels. "Running" oder "Finished"
	 */
	public String getGameState(GameInstanceService game);

	/**
	 * --> IGameInstance
	 * 
	 * Gibt den ersten Spieler zurück. Entweder das Arschloch oder der Spieler mit
	 * der Karo7.
	 * 
	 * @return der Spieler, der das Spiel beginnen darf
	 */
	public PlayerService getInitialPlayer();
	
	
	
	/**
	 * --> GameInstanceService
	 * 
	 * Initiiert Eingabeaufforderung
	 * 
	 * @return Eingabe
	 */
	public List getPlayerMove(PlayerService player);
	

}
