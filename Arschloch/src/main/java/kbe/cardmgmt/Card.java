package kbe.cardmgmt;

/**
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Diese Klasse stellt eine Karte dar.
 * Eine Karte besteht aus einer Zahl und einer Farbe.
 */
public class Card implements Comparable {

    /**
     * Ein Enum, welches die Zahlen darstellt, welche für ein Kartenspiel benötigt werden.
     * Die Zahl bestimmt den Kartenwert.
     */
    public enum Zahl {

        SIEBEN(7), ACHT(8), NEUN(9), ZEHN(10), BUBE(11), DAMEN(12), KOENIG(13), ASS(14);
    	
    	private int kartenWert;
    	
    	Zahl (int kartenZahl){
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

    Zahl zahl;
    Symbol symbol;

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

    /**
     * Gibt eine Farbe zurück
     *
     * @return: Eine Farbe
     */
    public Symbol getSymbol() {
        return symbol;
    }
    
    /**
     * Setzt die eine Karte aus Zahl und Symbol zusammen.
     * 
     * @return:	Name der Karte
     */
    public String toString() {
    	return symbol + " " + zahl;
    }

    @Override
    public int compareTo(Object other) {
        Card otherCard = (Card) other;

        try {
            Integer intThis = this.zahl.getKartenWert();
            Integer intOthercard = otherCard.zahl.getKartenWert();

            if (intThis == intOthercard) {
                System.out.println("This:" + intThis);
                System.out.println("Other:" + intOthercard);
                System.out.println("Gleicher wert");
                return 0;
            }
            else if (intThis < intOthercard) {
                System.out.println("This:" + intThis);
                System.out.println("Other:" + intOthercard);
                System.out.println("this kleiner als other");
                return -1;
            }
            else if (intOthercard == null) {
                System.out.println("intOther NULL");
                return 1;
            }
            else {
                System.out.println("This:" + intThis);
                System.out.println("Other:" + intOthercard);
                System.out.println("this groesser als other");
                return 1;
            }
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: keine Karte");
            return 1;
        }

    }

}
