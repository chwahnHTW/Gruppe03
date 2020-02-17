package kbe.gamemgmt;

import kbe.cardmgmt.Card;
import kbe.playermgmt.Player;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Diese Klasse stellt die Instanz eines Spiels dar.
 * Ein Spiel beinhaltet die Spieler, das momentane Rundenergebnis, die Karten, mit denen gerade gespielt wird und den aktuellen Spieler.
 *
 * Es gibt eine tabelle für eine Spielinstanz, welche in der Datenbank gespeichert wird
 */
@Entity
@Table(name = "GameInstance")
public class GameInstance {
    public Integer getGameId() {
        return gameId;
    }

    @Id
    @GeneratedValue
    private Integer gameId;

    @JoinColumn(name = "players")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players;

    @JoinColumn(name = "result")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> result = new LinkedList<>();

    @JoinColumn(name = "boardCards")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Card> boardCards = null;

    @JoinColumn(name = "current")
    @OneToOne(cascade = CascadeType.ALL)
    private Player currentPlayer = null;

    /**
     * Generiert eine Spielinstanz
     */
    public GameInstance() {

    }

    /**
     * Gibt eine Liste aller Spieler zurück
     *
     * @return : Liste aller Spieler
     */
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