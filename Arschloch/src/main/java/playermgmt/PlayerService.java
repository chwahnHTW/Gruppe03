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
     *
     * @return
     */
    String getPlayerNameInput();

    /**
     *
     * @return
     */
    List<Player> getPlayerMove();

    /**
     *
     * @return
     */
    int getPlayerCountInput();

	/**
	 * Hier werden doch die von den Handkarten die bestimmten Karten auf das feld gelegt werden, richtig?
	 */

    /**
     *
     * @param player: Der Spieler
     * @param handCards:
     */
	void removeFromHand(Player player, List<Card> handCards);

    /**
     *
     * @param player: Der Spieler
     * @param handCards
     */
	void addToHand(Player player, List<Card> handCards);

}
