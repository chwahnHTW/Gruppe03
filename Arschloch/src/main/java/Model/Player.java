package Model;

import java.lang.reflect.Array;

public class Player {

	String name;
	Card[] cards;
	
	
	Player(String name){
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Card[] getCards() {
		return cards;
	}


	public void setCards(Card[] cards) {
		this.cards = cards;
	}
	
	
}
