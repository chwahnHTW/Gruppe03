package playermgmt;

import java.util.List;

import cardmgmt.Card;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Stellt einen Spieler dar. Ein Spieler besteht aus einem Namen, einer Id und den Karten, die er während eines Spiels besitzt.
 */
public class Player {

    /**
     * Ein Enum, welches die Rolle darstellt, welche ein Spieler haben kann.
     */
    public enum Rolle {

        PRAESIDENT1, PRAESIDENT2, MITTELKIND, ARSCHLOCH1, ARSCHLOCH2;

    }

    Rolle role;
    int userId;
    String name;
    List<Card> handCards;

    /**
     * Generiert einen Spieler.
     *
     * @param name:      Der Name des Spielers
     * @param userid:    Die Id des Spielers
     * @param handCards: Die Karten, die der Spieler besitzt
     * @param role:      Die Rolle eines Spielers
     */
    Player(String name, int userid, List<Card> handCards, Rolle role) {
        this.name = name;
        this.userId = userid;
        this.handCards = handCards;
        this.role = role;
    }

    /**
     * Gibt die Userid zurück
     *
     * @return: die userId ces Spielers
     */
    public int getUserId() {
        return userId;
    }

    /**
     * setzt die Id eines Spielers
     *
     * @param id:     Die ID des Spielers
     * @param player: der Spieler
     */
    public void setUserId(int id, Player player) {
        player.userId = id;
    }

    /**
     * gibt den Namen eines Spielers zurück
     *
     * @return: Der Name eines Spielers
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen für einen Spieler
     *
     * @param name:   Name eines Spielers
     * @param player: Der Spieler
     */
    public void setName(String name, Player player) {
        player.name = name;
    }

    /**
     * Gibt das Array der Karten, die der Spieler hat, zurück.
     *
     * @return: Die Karten des Spielers
     */
    public List<Card> getHand() {
        return handCards;
    }

    /**
     * setzt die Karten, die der Spieler bekommen soll.
     *
     * @param cards:  Eine Hand von Karten
     * @param player: der Spieler
     */
    public void setHand(List<Card> cards, Player player) {
        player.handCards = cards;
    }

    /**
     * Gibt die Rolle eines Spielers zurück.
     *
     * @return: Die Rolle des Spielers
     */
    public Rolle getRolle() {
        return role;
    }

    /**
     * setzt die Rolle eines Spielers
     *
     * @param role:   Die Rolle
     * @param player: Der Spieler
     */
    public void setRolle(Rolle role, Player player) {
        player.role = role;
    }
}
