package kbe.playermgmt;

import java.util.List;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Der Spieler muss vor dem Spiel erstellt werden.
 * Der Spieler bekommt eine Hand an Karten zugewiesen.
 */

public interface PlayerService {

    /**
     * Untersucht, ob ein Spieler noch Karten hat
     *
     * @param player: Der Spieler
     * @return: True = Spieler hat Karten, False = Spieler hat keine Karten mehr
     */
    Boolean hasCards(Player player);

    /**
     * Hier werden die Karten von der Hand des Spielers entfernt, die er abgegeben hat.
     *
     * @param player: Der Spieler
     * @param cards:  Die Karten, die abgegeben wurden
     */
    void removeFromHand(Player player, List<Card> cards);

    /**
     * Hier werden Karten dem Spieler zugeteilt
     *
     * @param player: Der Spieler
     * @param cards:  Die Karten, die er bekommen soll
     */
    void addToHand(Player player, List<Card> cards);

    /**
     * Ein Spieler wird generiert
     *
     * @param name: Name des Spieler
     * @return Liste mit Player-Objekten
     */
    Player createPlayer(String name);

    /**
     * Der Spieler, der als nächstes an der Reihe ist wird hier festgelegt.
     *
     * @param instance - die Spielinstanz
     * @return Player - Der nächste Spieler
     */
    Player getNextPlayer(GameInstance instance) throws NullPointerException;

}
