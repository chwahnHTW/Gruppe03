
package kbe.gamemgmt;

import java.util.LinkedList;
import java.util.List;

import kbe.cardmgmt.Card;
import kbe.playermgmt.Player;

import javax.persistence.*;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Diese Klasse stellt die Instanz eines Spiels dar.
 * Ein Spiel beinhaltet die Spieler, das momentane Rundenergebnis, die Karten, mit denen gerade gespielt wird und den aktuellen Spieler.
 */
@Entity
@Table(name = "GameInstance")
public class GameInstance {


    /**
     * @param players: Liste der an einem Spiel beteiligten Spieler
     * @param result:  Siegerreihenfolge
     * @param cards:   Karte(n), die sich momentan auf dem Spielfeld befindet/n. Diese gilt es zu überspielen
     * @param current: Spieler, der gerade an der Reihe ist, einen Zug zu machen
     */


    private Integer gameId;


    private List<Player> players;


    private List result = new LinkedList<Player>();


    private List<Card> boardCards = null;


    private Player currentPlayer = null;

    /**
     * Generiert eine Spielinstanz
     * Enthält die Informationen, die während eines Spiels vorrangig wichtig sind
     */
    public GameInstance() {

    }

    @Id
    @GeneratedValue
    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    /**
     * Gibt eine Liste aller Spieler zurück
     *
     * @return : Liste aller Spieler
     */
    @Column(name = "players")
    @OneToMany
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Setzt die Spieler einer Spielinstanz
     *
     * @param players : Eine Liste von Spielern
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Gibt eine Liste sortiert nach der Rangfolge der Spieler zurück
     *
     * @return : Liste von Spielern
     */
    @Transient
    public List<Player> getResult() {
        return result;
    }

    /**
     * Setzt eine Liste von Spielern zurück, sortiert nach ihrer Rangfolge
     *
     * @param result : Liste von Spielern
     */
    public void setResult(Player result) {
        this.result.add(result);
    }

    /**
     * Gibt eine Liste von Karten zurück, die aktuell auf dem Tisch liegen
     *
     * @return : Liste von Karten
     */
    @Column(name = "cards")
    @OneToMany
    public List<Card> getBoardCards() {
        return boardCards;
    }

    /**
     * Setzt die Liste von Karten, die aktuell auf dem Tisch liegen sollen
     *
     * @param boardCards : Liste von Karten
     */
    public void setBoardCards(List<Card> boardCards) {
        this.boardCards = boardCards;
    }

    /**
     * Gibt den aktuellen Spieler zurück
     *
     * @return : der Aktuelle Spieler
     */
    @Column(name = "current")
    @OneToOne
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Setzt den aktuellen Spieler
     *
     * @param currentPlayer : der aktuelle Spieler
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

}