package playermgmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


import cardmgmt.Card;
import frontendmgmt.FrontendService;

public class PlayerServiceImpl implements PlayerService{
	
	FrontendService frontendService;

	public Player createPlayer(){
		String name = "";
		int userId;
		
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        try {
			System.out.print("Name: ");
			name = reader.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}  
		System.out.println("Name is " + name);
		Player newPlayer = new Player(name, this.generateId());
		return newPlayer;
	
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

	public void removeFromHand(PlayerService praesident, PlayerService arschloch) {
		// TODO Auto-generated method stub
		
	}

	public void addToHand(PlayerService praesident, PlayerService arschloch) {
		// TODO Auto-generated method stub
		
	}

	public int generateId() {
		// TODO Auto-generated method stub
		return 0;
	}

}
