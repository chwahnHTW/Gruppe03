package kbe.playermgmt;

import java.util.LinkedList;
import java.util.List;

import kbe.cardmgmt.Card;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Stellt einen Spieler dar. Ein Spieler besteht aus einem Namen, einer Id und den Karten, die er während eines Spiels besitzt.
 */
public class Player {

    /**
     * Ein Enum, welches die Rolle darstellt, welche ein Spieler haben kann.
     */
    public enum Role {

        PRAESIDENT1, PRAESIDENT2, MITTELKIND, ARSCHLOCH2, ARSCHLOCH1;

    }

    public Role role;
    public String name;
    public List<Card> handCards;

    /**
     * Generiert einen Spieler.
     *
     * @param name:      Der Name des Spielers
     * @param handCards: Die Karten, die der Spieler besitzt
     * @param role:      Die Rolle eines Spielers
     */
    public Player(String name, List<Card> handCards, Role role) {
        this.name = name;
//        this.handCards = handCards;
        this.handCards = new LinkedList<Card>();
        this.role = role;
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
     * @param name: Name eines Spielers
     */
    public void setName(String name) {
        this.name = name;
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
     * ACHTUNG NEUER SETTER!!!
     * @param handCards
     */
    public void setHandCards(List<Card> handCards) {
    	this.handCards = handCards;
    }

    /**
     * Fuegt einzelne Karten in die Liste der Handkarten des Spielers.
     *
     * @param card: eine Karte
     */
    public void setHand(Card card) {
        handCards.add(card);
    }

    /**
     * Gibt die Rolle eines Spielers zurück.
     *
     * @return: Die Rolle des Spielers
     */
    public Role getRole() {
        return role;
    }

    /**
     * setzt die Rolle eines Spielers
     *
     * @param role: Die Rolle
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
