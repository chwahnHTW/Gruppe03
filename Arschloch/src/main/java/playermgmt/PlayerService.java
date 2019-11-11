package playermgmt;

import java.util.List;

import cardmgmt.Card;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Der Spieler muss vor dem Spiel erstellt werden.
 * Der Spieler bekommt eine Hand an Karten zugewiesen.
 */
public interface PlayerService {

    /**
     * Ein Spieler wird erstellt.
     *
     * @return ein Spieler
     */
    Player createPlayer();

    /**
     * Die Id eines Spielers wird generiert.
     *
     * @return: Die Id für einen Spieler
     */
    int generateId();

    /**
     * In einer Runde kann jeder Spieler Karten spielen. Hier wählt der Spieler aus,
     * welche Karte(n) von seiner Hand er spielen möchte.
     *
     * @return Liste mit Karten, die der Spieler ausgewaehlt hat
     */
    List<Card> selectCards(List<Card> boardCards);

    /**
     * Untersucht, ob ein Spieler noch Karten hat
     *
     * @param player: Der Spieler
     * @return: True = Spieler hat Karten, False = Spieler hat keine Karten mehr
     */
    Boolean hasCards(Player player);

    /**
     * Hier wird der Name des Spielers übergeben, den er eingetippt hat
     *
     * @return: der Name des Spielers
     */
    String getPlayerNameInput();

    /**
     * Hier werden die Karten gespeichert, die der Spieler in seinem Zug spielen will
     *
     * @return: Liste von Karten, die gespielt werden
     */
    List<Player> getPlayerMove();

    /**
     * Hier wird gezählt, wieviele Spieler am Spiel beteiligt sind
     *
     * @return: Anzahl der Spieler
     */
    int getPlayerCountInput();

	/**
	 * Hier werden doch die von den Handkarten die bestimmten Karten auf das feld gelegt werden, richtig?
	 */

    /**
     * Hier werden die Karten von der Hand des Spielers entfernt, die er abgegeben hat.
     *
     * @param player: Der Spieler
     * @param cards: Die Karten, die abgegeben wurden
     */
	void removeFromHand(Player player, List<Card> cards);

    /**
     * Hier werden Karten dem Spieler zugeteilt
     *
     * @param player: Der Spieler
     * @param cards: Die Karten, die er bekommen soll
     */
	void addToHand(Player player, List<Card> cards);

}
