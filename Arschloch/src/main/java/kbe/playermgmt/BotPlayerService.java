package kbe.playermgmt;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;

import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Simuliert einen Botspieler.
 * Der Botspieler spielt selber als Computer.
 * Um einen Botspieler zum Spielen zu bekommen, muss man immer für den Bot "Play Cards" drücken.
 * Er entscheidet selber, ob er spielt und was er spielt.
 */
public interface BotPlayerService {

    /**
     * Setzt Karte(n), die gleichwertig sind zueinander.
     * Funktioniert nur, wenn Boardkarte leer ist.
     *
     * @param cardList :
     * @return : Liste von Karten, die gleich sind
     */
    List<Card> setTwoEqualCards(List<Card> cardList);

    /**
     * Sucht nach Karten, die höher sind als Boardkarten.
     *
     * @param botHandCards : die Karten des Bots
     * @param gameInstance : die Spielinstanz
     * @return : Liste von Karten, die höher sind als die Boardkarten
     */
    List<Card> findHigherCards(List<Card> botHandCards, GameInstance gameInstance);

    /**
     * Wenn ein Ass gelegt wurde, werden die Boardcards abgeräumt und neuer Zug beginnt.
     *
     * @param gameInstance : die Spielinstanz
     */
    void assOnBoard(GameInstance gameInstance);

}
