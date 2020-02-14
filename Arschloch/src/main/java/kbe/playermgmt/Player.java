package kbe.playermgmt;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

import com.sun.istack.internal.NotNull;
import kbe.cardmgmt.Card;
import org.springframework.lang.NonNull;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Stellt einen Spieler dar. Ein Spieler besteht aus einem Namen, einer Id und den Karten, die er während eines Spiels besitzt.
 */
@Entity
@Table(name = "Player")
public class Player {
    private Integer playerId;

    private Role role;

    private String name;

    private List<Card> handCards;


    /**
     * Ein Enum, welches die Rolle darstellt, welche ein Spieler haben kann.
     */
    public enum Role {

        PRAESIDENT1, PRAESIDENT2, MITTELKIND, ARSCHLOCH2, ARSCHLOCH1;

    }

    @Id
    @GeneratedValue
    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    /**
     * Generiert einen Spieler.
     *
     * @param name:      Der Name des Spielers
     * @param handCards: Die Karten, die der Spieler besitzt
     * @param role:      Die Rolle eines Spielers
     *            String name, List<Card> handCards, Role role
     */
    public Player(String name, List<Card> handCards, Role role) {
        this.name = name;
        this.handCards = new LinkedList<Card>();
        this.role = role;
    }

    public Player(){

    }

    /**
     * gibt den Namen eines Spielers zurück
     *
     * @return: Der Name eines Spielers
     */
    @Column(name = "name")
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
    @Transient
    public List<Card> getHand() {
        return handCards;
    }

    @OneToMany
    @Column(name ="handCards")
    public List<Card> getHandCards() {
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
    @Column(name = "role")
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
