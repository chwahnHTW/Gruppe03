package cardmgmt;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Diese Klasse stellt eine Karte dar.
 * Eine Karte besteht aus einer Zahl und einer Farbe.
 */
public class Card {

    /**
     * Ein Enum, welches die Zahlen darstellt, welche für ein Kartenspiel benötigt werden.
     */
    public enum Zahl {

        SIEBEN, ACHT, NEUN, ZEHN, BUBE, DAMEN, KOENIG, ASS;

    }

    /**
     * Ein Enum, welches die Farben darstellt, die für ein Kartenspiel benötigt werden.
     */
    public enum Symbol {

        KARO, HERZ, PIK, KREUZ;

    }

    final Zahl zahl;
    final Symbol symbol;

    /**
     * Eine Karte besteht aus einer Zahl und einer Farbe.
     * @param zahl: Die Zahl der Karte
     * @param symbol: Die Farbe der Karte
     */
    public Card(Zahl zahl, Symbol symbol) {
        this.zahl = zahl;
        this.symbol = symbol;

    }

    /**
     * Gibt eine Zahl zurück
     * @return: Eine Zahl
     */
    public Zahl getZahl() {
        return zahl;
    }

    /**
     * Gibt eine Farbe zurück
     * @return: Eine Farbe
     */
    public Symbol getSymbol() {
        return symbol;
    }

}
