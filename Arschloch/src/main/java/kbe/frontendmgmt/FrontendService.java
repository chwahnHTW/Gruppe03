package kbe.frontendmgmt;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;

import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * * Hier wird das Frontend realisiert.
 */
public interface FrontendService {

    /**
     * Die GUI wird hier initialisiert.
     * es wird keine Spielinstanz erstellt.
     */
    void init();

    /**
     * Validiert einen Spielzug. Nimmt hierfuer Userinput entgegen und vergleicht
     * diesen mit BoardCards. Ueber den Rueckgabewert der Eingabeaufforderung wird
     * der Spielfluss gesteuert Valider Move : Karten vom Spieler abziehen, als
     * BoardCards setzen, Spielstatus pruefen, nextPlayer setzen und weiterspielen(
     * oder Dialog zum Beenden / Neustarten des Spiels aufrufen) Invalider move :
     * Erneute Eingabeaufforderung, kein getNextPlayer
     */
    void validateMove();

    /**
     * Setzen der Spielerrollen, Ueber die Reihenfolge, in der die Spieler in die
     * Ergebnisliste aufgenommen wurden laesst sich ermitteln, welche Rolle sie
     * haben ( erster Spieler, der fertig ist =
     * gameInstance.getResult().get(0).setRole(Player.Role.PRAESIDENT) usw. ) Simple
     * Implementierung, da momentan nur mit 3 Spielern spielbar
     */
    void setPlayerRoles(GameInstance gameInstance);

    /**
     * Methode, die ueberprueft, ob ein Spieler noch Karten hat, um ihn, falls dem
     * nicht so ist, in die Erbegnissliste einzutragen, anhand derer spaeter die
     * Rollen der Spieler ermittelt werden
     */
    void addCurrentPlayerToResult(GameInstance gameInstance);

    /**
     * Methode, die nach Spielabschluss erfaesst, ob weitergepspielt werden soll
     *
     * @return boolean - weiterspielen oder nicht ( true , false )
     */
    Boolean getContinueGame() throws IllegalArgumentException;

    /**
     * fragt ab, ob ein Spiel neu gestartet werden soll oder ein altes wiederhergestellt werden soll
     *
     * @return
     */
    Boolean playNewGame() throws IllegalArgumentException;

    /**
     * Methode, um die Namen der Spieler eines Spiels zu erfassen
     *
     * @return - String - Name fuer einen User
     */
    String getUserNameInput();

    /**
     * Zeigt die gespeicherte Id der Spielinstanz an, um sie später wieder herstellen zu können
     */
    void showSavedGameId();

    /**
     *
     * @param gameInstance
     */
    void showResultList(GameInstance gameInstance);

    /**
     * Methode, um Userinput ( Spieleranzahl ) zu erhalten
     *
     * @return vom Spieler eingegebene Spieleranzahl
     * @throws IllegalArgumentException
     */
    int getUserCountInput();

    /**
     * GameId eingeben, um Spiel später wieder herstellen zu können
     *
     * @return gameId
     * @throws IllegalArgumentException
     */
    int getGameId();

    /**
     * Hier wird ein neues Spiel erstellt, in dem neue Spieler eingegeben werden.
     * Jeder Spieler bekommt eine Hand an Karten und es wird der erste Spieler gesetzt
     *
     * @param gameInstance : Spiel Instanz
     */
    void startNewGame(GameInstance gameInstance);

    /**
     * Hier wird eine gespeicherte Spielinstanz wieder hergestellt
     *
     * @param gameInstance gameInstanz
     */
    void startSavedGame(GameInstance gameInstance);

    /**
     * Fragt, ob User mit Bots spielen möchte.
     *
     * @return
     * @throws IllegalArgumentException
     */
    Boolean getIfBotPlayer();

    /**
     * startet ein Spiel
     * @param gameInstance : eine Spielinstanz
     */
    void startGame(GameInstance gameInstance);

    /**
     * Passen eines Zuges wird hier validiert
     * @param gameInstance
     */
    void pass(GameInstance gameInstance);

    void gameStateEvaluation(GameInstance gameInstance);

    int getGameIdForUser(GameInstance instance);

    /**
     * Hier wird das letzte beendete Spiel aus der Hsitorie abgerufen, um dessen Result zu untersuchen
     * und zu prüfen, ob Karten getauscht werden müssen, oder nicht, sowie welcher Spieler anfängt
     *
     * @return : eine Spielinstanz
     */
    GameInstance getLastPlayedGame(int gameId);

    /**
     * @param instance
     */
    void saveCurrentGame(GameInstance instance);

    /**
     * Fragt den User ab nach welcher Regel gespielt werden soll.
     * Ob Arschloch oder President bei einer neuen Runde anfangen soll.
     *
     * @param gameInstance
     */
    void askInitialPlayerString(GameInstance gameInstance);

    /**
     * Setzt den Spieler, der anfagen soll,
     * nachdem über askInitialPlayer nach der Regel gefragt wurde.
     *
     * @param gameInstance
     * @throws IllegalArgumentException
     */
    void setInitialPlayer(GameInstance gameInstance) throws IllegalArgumentException;

    /**
     * validiert den Zug eines Botspielers.
     * Funktioniert ähnlich wie PlayerService.validateMove()
     *
     * @param gameInstance
     */
    void validateBotMove(GameInstance gameInstance);

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
     * @param gameInstance : die SPielinstanz
     * @param higherCards :  höhere Karten
     */
    void setEqualCards(GameInstance gameInstance, List<Card> higherCards);
}
