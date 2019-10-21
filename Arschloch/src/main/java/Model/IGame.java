/**
 * 
 */
package Model;

import java.util.LinkedList;

/**
 * 
 *
 * @authors 		Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler 
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 * 
 * 
 *
 */
public interface IGame {
	
	/**
	 * 
	 * @return
	 */
	public GameInstance startGame();
	
	/**
	 * 
	 * @return
	 */
	public int setPlayercount();
	
	/**
	 * 
	 * @return
	 */
	public Player createPlayer();
	
	/**
	 * 
	 */
	public void endGame();
	
	/**
	 * 
	 */
	public void playCards();
	
	/**
	 * 
	 */
	public void swapCards();
	
	/**
	 * 
	 * @return
	 */
	public Player getCurrentPlayer();
	
	/**
	 * 
	 * @return
	 */
	public Player getNextPlayer();
	
	/**
	 * 
	 */
	public void setResult();
	
	/**
	 * 
	 * @return
	 */
	public LinkedList<String> getResult();
	
	/**
	 * 
	 * @return
	 */
	public Comparable<Card> compareCards();
	
	/**
	 * 
	 * @return
	 */
	public String gameState();
	
	/**
	 * 
	 * @return
	 */
	public Player getInitialPlayer();
}
