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
     * Zu den untenstehenden Methoden:
     * wir wollen ja die Karten zum Beispiel von Arschloch zu Praesident hinzufuegen, richtig?
     * Da wir die Karten, die ein Spieler auf einer Hand hat, ueber den Spieler selbst ablegen/speichern,
     * waere es doch sinnvoll als Parameter die zwei Spieler zu uebergeben und ueber die Spieler an die Karten ranzukommen.
     * So trennen wir spezifische Methoden, also Methoden mit Parameter Spieler nur in SpielerService
     * und alle Methode mit Parameter Karte nur in KarteService. Klingt das sinnvol fuer euch?
     *
     * Oder ist die Methode swapCards in GameInstance schon damit gemeint?
     */

//	public void removeFromHand(PlayerService player, List<Card> selectedCards);
//	
//	public void addToHand(PlayerService player, List<Card> selectedCards);

    /**
     * @param praesident
     * @param arschloch
     */
    public void removeFromHand(PlayerService praesident, PlayerService arschloch);

    /**
     * @param praesident
     * @param arschloch
     */
    public void addToHand(PlayerService praesident, PlayerService arschloch);

}
