package kbe.cardmgmt;

import javax.persistence.*;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Diese Klasse stellt eine Karte dar.
 * Eine Karte besteht aus einer Zahl und einer Farbe.
 * <p>
 * Die Karte wird in einer Tabelle gespeichert namens "Cards"
 * Es wird eine Id generiert.
 */
@Entity
@Table(name = "Cards")
public class Card implements Comparable {

    @Column(name = "zahl")
    public Zahl zahl;
    @Column(name = "symbol")
    public Symbol symbol;
    @Id
    @GeneratedValue
    Integer cardId;

    /**
     * Eine Karte besteht aus einer Zahl und einer Farbe.
     *
     * @param zahl:   Die Zahl der Karte
     * @param symbol: Die Farbe der Karte
     */
    public Card(Zahl zahl, Symbol symbol) {
        this.zahl = zahl;
        this.symbol = symbol;

    }

    /**
     * Konstruktor der Karte
     */
    public Card() {
    }

    /**
     * Gibt eine Zahl zurück
     *
     * @return: Eine Zahl
     */
    public Zahl getZahl() {

        return zahl;
    }

    public void setZahl(Zahl zahl) {
        this.zahl = zahl;
    }

    /**
     * Gibt eine Farbe zurück
     *
     * @return: Eine Farbe
     */
    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    /**
     * Setzt die eine Karte aus Zahl und Symbol zusammen.
     *
     * @return: Name der Karte
     */
    public String toString() {
        return symbol + " " + zahl;
    }

    /**
     * Vergleicht die Karte mit einer anderen Karte
     *
     * @param other : zweite Karte
     * @return : 0 = sie sind gleich, -1 = verglichene Karte ist größer, 1 = Karte ist größer
     */
    public int compareTo(Object other) {
        Card otherCard = (Card) other;

        try {
            Integer intThis = this.zahl.getKartenWert();
            Integer intOthercard = otherCard.zahl.getKartenWert();

            if (intThis == intOthercard) {
                return 0;
            } else if (intThis < intOthercard) {
                return -1;
            } else if (intOthercard == null) {
                return 1;
            } else {
                return 1;
            }
        } catch (NullPointerException e) {
            return 1;
        }

    }

    /**
     * Ein Enum, welches die Zahlen darstellt, welche für ein Kartenspiel benötigt werden.
     */
    public enum Zahl {

        SIEBEN(7), ACHT(8), NEUN(9), ZEHN(10), BUBE(11), DAMEN(12), KOENIG(13), ASS(14);

        private int kartenWert;

        Zahl(int kartenZahl) {
            this.kartenWert = kartenZahl;
        }

        public int getKartenWert() {
            return this.kartenWert;
        }

    }

    /**
     * Ein Enum, welches die Farben darstellt, die für ein Kartenspiel benötigt werden.
     */
    public enum Symbol {

        KARO, HERZ, PIK, KREUZ;

    }

}
