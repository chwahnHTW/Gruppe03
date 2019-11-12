package gamemgmt;

import java.util.List;

import cardmgmt.Card;
import playermgmt.Player;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Eine Klasse, die die Spielinstanz realisiert
 * Hier läuft das Spiel im Großteil ab.
 */
public interface GameInstanceService {

    /**
     * Eine neue Spielinstanz wird erzeugt und zurückgegeben
     *
     * @return GameInstanz
     */
    GameInstance startGame();

    /**
     * Die Anzahl der Spieler wird vom User eingegeben und erfasst.
     *
     * @return Anzahl der Spieler
     */
    int determinePlayercount();

    /**
     * Gemäß des in determinePlayercount ermittleten Wertes werden Spieler vom SpielerService der Implementierung erstellt
     *
     * @param playerCount: Anzahl der Spieler
     * @return Liste mit Player-Objekten
     */
    List<Player> createPlayers(int playerCount);

    /**
     * In einer Runde kann jeder Spieler Karten spielen.
     * Hier wählt der Spieler aus, welche Karte(n) von seiner Hand er spielen möchte.
     *
     * @param player: Ein Spieler
     * @return Liste mit Karten, die der Spieler ausgewaehlt hat
     */
    List<Card> selectCards(Player player);

    /**
     * In einer Runde kann jeder Spieler Karten spielen.
     * Hier spielt der Spieler seine Karte(n) aus.
     * Die Karten wurden in der Methode selectCards ausgewaehlt
     *
     * @param player        : ein SPieler
     * @param selectedCards : die Karten, die der Spieler spielen möchte
     */
    void playCards(Player player, List<Card> selectedCards);

    /**
     * Am Anfang des Spiels werden zwischen Gewinner und Verlierer des letzten Spiels Karten getauscht.
     * Dies wird hier relaisiert, sofern es ein letztes Spiel gibt und die Teilnehmer dieselben sind
     *
     * @param players : eine Liste der Spieler
     */
    void swapCards(List<Player> players);


    /**
     * Austeilen der Spielkarten an die Spieler
     *
     * @param gameCards - generierter Kartensatz
     */
    void dealCardsToPlayers(List<Card> gameCards);

    /**
     * Der Spieler, der als nächstes an der Reihe ist wird hier festgelegt.
     *
     * @return Player - Der nächste Spieler
     */
    Player getNextPlayer() throws NullPointerException;

    /**
     * Der Spielstatus eines Spiels. Zu Beginn "Running", nachdem alle bis auf den letzten Speieler ihre Karten abgelegt haben auf Änderung auf "Finished"
     *
     * @param gameInstance : die Spielinstanz
     * @return : Der Status des Spiels. "Running" oder "Finished"
     */
    String calculateGameState(GameInstance gameInstance);

    /**
     * Gibt den ersten Spieler zurück. Entweder das Arschloch oder der Spieler mit der Karo7.
     *
     * @param gameInstance : die Spielinstanz
     * @return : der Spieler, der das Spiel beginnen darf
     */
    Player calculateInitialPlayer(GameInstance gameInstance);

    /**
     * Initiiert Eingabeaufforderung des Spielers, welche Karten er spielen will.
     *
     * @param player : ein Spieler
     * @return : vom Spieler gespielte Karte(n); null, falls der Spieler passt.
     */
    List<Card> getPlayerMove(Player player);
}