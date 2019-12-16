package kbe.cardmgmt;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * <p>
 * Diese Klasse stellt eine Karte dar.
 * Eine Karte besteht aus einer Zahl und einer Farbe.
 */
public class Card implements Comparable{

    /**
     * Ein Enum, welches die Zahlen darstellt, welche für ein Kartenspiel benötigt werden.
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
    
    public Card() {}

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
	public int compareTo(Object otherCard) {

		Card other = (Card) otherCard;
		
		if ( this.zahl.compareTo(other.zahl) == 1) {
			
			System.out.println("works baby");
			return 1;
		}
			
		else if (this.zahl.compareTo(other.zahl) == 0) {
			System.out.println("invalid move, same number values");
			return 0;
		}
		
		else if (this.zahl.compareTo(other.zahl) == 1) {
			System.out.println("invalid move, lower number value");
			return -1;
		}
	
		else if (other.zahl == null) {
			System.out.println("works baby");
			return 1;
		}
	
		else return 0;		
	}

}
