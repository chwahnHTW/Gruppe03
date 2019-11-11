package playermgmt;

import java.util.List;

import cardmgmt.Card;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * Der Spieler muss vor dem Spiel erstellt werden.
 * Der Spieler bekommt eine Hand an Karten zugewiesen.
 */
public interface PlayerService {

    /**
     * Ein Spieler wird erstellt.
     *
     * @return ein Spieler
     */
    public Player createPlayer();

    /**
     * @return
     */
    public int generateId();

    /**
     * In einer Runde kann jeder Spieler Karten spielen. Hier wählt der Spieler aus,
     * welche Karte(n) von seiner Hand er spielen möchte.
     *
     * @return Liste mit Karten, die der Spieler ausgewaehlt hat
     */
    public List<Card> selectCards(List<Card> boardCards);

    /*
     * Method to check if a player still has cards or not
     */
    public Boolean hasCards(Player player);

    /**
     * @return
     */
    public String getPlayerNameInput();

    /**
     * @return
     */
    public List<Player> getPlayerMove();

    /**
     * @return
     */
    public int getPlayerCountInput();


	
	/**
	 * Hier werden doch die von den Handkarten die bestimmten Karten auf das feld gelegt werden, richtig?
	 */
	
	public void removeFromHand(PlayerService player, List<Card> handCards);
	
	public void addToHand(PlayerService player, List<Card> handCards);

}
