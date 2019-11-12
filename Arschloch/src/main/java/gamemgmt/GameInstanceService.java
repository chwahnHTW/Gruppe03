/**
 * 
 */
package gamemgmt;
import java.util.LinkedList;
import java.util.List;

import cardmgmt.Card;
import gamemgmt.GameInstance;
import historymgmt.History;
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
	 * Eine neue Spielinstanz wird erzeugt und zurückgegeben
	 * 
	 * @return GameInstanz
	 */
	public GameInstance startGame();
	
	
	/**
	 * Die Anzahl der Spieler wird vom User eingegeben und erfasst.
	 * 
	 * @return Anzahl der Spieler
	 */
	public int determinePlayercount();
	
	/**
	 * Gemäß des in determinePlayercount ermittleten Wertes werden Spieler vom SpielerService der Implementierung erstellt
	 * 
	 * @return Liste mit Player-Objekten
	 */
	public List<Player> createPlayers(int playerCount);

	/**
	 * In einer Runde kann jeder Spieler Karten spielen. Hier wählt der Spieler aus,
	 * welche Karte(n) von seiner Hand er spielen möchte.
	 * 
	 * @return Liste mit Karten, die der Spieler ausgewaehlt hat
	 */
	public List<Card> selectCards(Player player);

	/**
	 *              In einer Runde kann jeder Spieler Karten spielen. Hier spielt
	 *              der Spieler seine Karte(n) aus. Die Karten wurden in der Methode
	 *              selectCards ausgewaehlt
	 */
	public void playCards(Player player, List<Card> selectedCards);

	/**
	 * Am Anfang des Spiels werden zwischen Gewinner und Verlierer des letzten
	 * Spiels Karten getauscht. Dies wird hier relaisiert, sofern es ein letztes Spiel gibt und die Teilnehmer
	 * dieselben sind
	 */
	public void swapCards(List<Player> players);

	
	/**
	 *Austeilen der Spielkarten an die Spieler
	 *@param gameCards - generierter Kartensatz
	 */
	public void dealCardsToPlayers(List<Card> gameCards);
	
	/**
	 * IGameInstance
	 * 
	 * Der Spieler, der als nächstes an der Reihe ist.
	 * 
	 * @return Player - Der nächste Spieler
	 */
	public Player getNextPlayer() throws NullPointerException;
	
	/**
	 * 
	 * Der Spielstatus eines Spiels. Zu Beginn "Running", nachdem alle bis auf den
	 * letzten Speieler ihre Karten abgelegt haben auf Änderung auf "Finished"
	 * 
	 * @return Der Status des Spiels. "Running" oder "Finished"
	 */
	public String calculateGameState(GameInstance gameInstance);

	/**
	 * Gibt den ersten Spieler zurück. Entweder das Arschloch oder der Spieler mit
	 * der Karo7.
	 * 
	 * @return der Spieler, der das Spiel beginnen darf
	 */
	 public Player calculateInitialPlayer(GameInstance gameInstance);

	/**
	 * Initiiert Eingabeaufforderung
	 * 
	 * @return vom Spieler gespielte Karte(n); null, falls der Spieler passt.
	 */
	public List<Card> getPlayerMove(Player player);



	

}