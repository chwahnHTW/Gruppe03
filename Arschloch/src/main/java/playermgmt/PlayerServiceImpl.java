package playermgmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;


import cardmgmt.Card;

public class PlayerServiceImpl implements PlayerService{
	

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

	public List getHand(Player player) {
		return player.handCards;
	}

	public void setHand(List<Card> cards, Player player){
		player.handCards = cards;	
	}	
	
	
	public int getUserId(Player player) {
		return player.userId;
	}

	public String getName(Player player) {

		return player.name;
	}

	public void setName(String name) {

		
	}

	public List selectCards(List boardCards) {

		return null;
	}

	public Boolean hasCards() {

		return null;
	}

	public String getPlayerNameInput() {

		return null;
	}

	public List getPlayerMove() {
		return null;
	}

	public int getPlayerCountInput() {
		
		return 0;
	}

	public int generateId() {
		// TODO Auto-generated method stub
		return 0;
	}


}
