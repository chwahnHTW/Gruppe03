package playermgmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import cardmgmt.Card;
import frontendmgmt.FrontendService;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de 	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */
public class PlayerServiceImpl implements PlayerService {
	
	FrontendService frontendService;

	public Player createPlayer(){
		return null;
//		String name = "";
//		int userId;
//		List<Card> handCards;
//		Player.Rolle role;
//
//        BufferedReader reader =
//                new BufferedReader(new InputStreamReader(System.in));
//        try {
//			System.out.print("Name: ");
//			name = reader.readLine();
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("Name is " + name);
//		Player newPlayer = new Player(name, this.generateId(), handCards, role);
//		return newPlayer;
	}

	public List<Card> selectCards(List<Card> boardCards) {

		return null;
	}

	public String getPlayerNameInput() {

		return null;
	}

	public List<Player> getPlayerMove() {
		return null;
	}

	public int getPlayerCountInput() {
		
		return 0;
	}

	public Boolean hasCards(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	public void removeFromHand(Player player, List<Card> handCards) {
		// TODO Auto-generated method stub
		
	}

	public void addToHand(Player player, List<Card> handCards) {
		// TODO Auto-generated method stub
		
	}

	public int generateId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
