package cardmgmt;

import java.util.List;

import org.springframework.stereotype.Component;

import gamemgmt.GameInstance;
import playermgmt.Player;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Diese Klasse stellt den CardService dar.
 * Dieser Service bildet eine Schnittstelle, um ein Kartendeck für das Spiel zur Verfügung stellen zu können.
 * <p>
 * Eine Karte besteht aus einer Zahl und einer Farbe.
 * Ein normales Kartendeck besteht aus den Zahlen 7 bis Ass und den vier Farben (Herz, Karo, Pik und Kreuz).
 * Es gibt dementsprechend 32 Karten.
 */

@Component
public interface CardService {

    /**
     * Hier werden die Karten nach ihrer Wertigkeit sortiert.
     * Dies ist wichtig, um herauszufinden, welche Karten Präsident und Arschloch später tauschen müssen (die niedrigsten/höchsten Karten)
     *
     * @param cards: Die Karten, die ein Spieler auf der Hand hat am Anfang eines Spiels, bevor  Karten getauscht wurden.
     * @return : Eine geordnete Liste von Karten für die Spielerhand.
     */
    List<Card> orderCardsByValue(List<Card> cards);

    /**
     * Das Kartendeck wird hier generiert.
     * Ein normales Kartendeck besteht aus den Zahlen 7 bis Ass und den vier Farben (Herz, Karo, Pik und Kreuz).
     * <p>
     * Das Kartendeck wird gemischt, bevor es an die Spieler ausgeteilt wird.
     *
     * @param anzahlKarten: In einem normalen Spiel 32 Karten.
     * @return : Eine Liste von Karten, entsprechend ihrer Anzahl, für eine Spielrunde.
     */
    List<Card> generateDeck(int anzahlKarten);

    /**
     * Das Kartendeck wird hier gemischt
     * Ein normales Kartendeck besteht aus den Zahlen 7 bis Ass und den vier Farben (Herz, Karo, Pik und Kreuz).
     * <p>
     * Das Kartendeck wird gemischt, bevor es an die Spieler ausgeteilt wird.
     *
     * @param cardList: In einem normalen Spiel 32 Karten.
     * @return : Eine Liste von Karten, entsprechend ihrer Anzahl, für eine Spielrund, gemischt
     */
    List<Card> shuffleDeck(List<Card> cardList);

    
    /**
     * Am Anfang des Spiels werden zwischen Gewinner und Verlierer des letzten Spiels Karten getauscht.
     * Dies wird hier relaisiert, sofern es ein letztes Spiel gibt und die Teilnehmer dieselben sind
     *
     * @param players : eine Liste der Spieler
     */
    void swapCards(GameInstance gameInstance);
    
    /**
     * Austeilen der Spielkarten an die Spieler
     *
     * @param gameCards - generierter Kartensatz
     */
    void dealCardsToPlayers(GameInstance gameInstance);

}
