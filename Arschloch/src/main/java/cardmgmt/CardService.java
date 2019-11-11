package cardmgmt;

import java.util.List;

import playermgmt.PlayerService;

public interface CardService {

    // getter und setter in domain klassen

    /**
     * Gibt die Zahl einer Karte zurück.
     *
     * @return: Zahl der Karte
     */
    public int getNumber();


    /**
     * Gibt die Farbe einer Karte zurück.
     *
     * @return: Farbe der Karte
     */
    public String getSymbol();

    /**
     * @param cards
     * @return
     */
    public List orderCardsByValue(List cards);

// methoden die palyer benutzen in palyer verschieben

    /**
     * @param player
     * @param selectedCards
     */
    public void removeFromHand(PlayerService player, List selectedCards);

    /**
     * @param player
     * @param selectedCards
     */
    public void addToHand(PlayerService player, List selectedCards);

    /**
     * @return
     */
    public List generateDeck();

}
