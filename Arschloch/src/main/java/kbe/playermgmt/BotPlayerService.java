package kbe.playermgmt;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;

import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Simuliert einen Player.
 */
public interface BotPlayerService {

    /**
     *
     * @param gameInstance
     * @param passCounter
     */
    void validateBotMove(GameInstance gameInstance, int passCounter);

    /**
     * Nach validen Spielzug alles updaten.
     *
     * @param cardsToPlay
     * @param gameInstance
     */
    void updateAll(List<Card> cardsToPlay, GameInstance gameInstance);

    /**
     * Setzt Karte(n), die gleichwertig sind zueinander.
     * Funktioniert nur, wenn Boardkarte leer ist.
     *
     * @param cardList
     * @return
     */
    List<Card> setTwoEqualCards(List<Card> cardList);

    /**
     * Sucht nach Karten, die höher sind als Boardkarten.
     *
     * @param botHandCards
     * @param gameInstance
     * @return
     */
    List<Card> findHigherCards(List<Card> botHandCards, GameInstance gameInstance);

    /**
     * Wenn der Bot keine Karte hat zum Legen, wird gepasst.
     * @param gameInstance
     * @param passCounter
     */
    void botPass(GameInstance gameInstance, int passCounter);

    /**
     * Wenn ein Ass gelegt wurde,
     * werden die Boardcards abgeräumt und neuer Zug beginnt.
     *
     * @param gameInstance
     */
    void assOnBoard(GameInstance gameInstance);
}
