package Model;

public class GameInstance {

	Player[] players;
	String[] result;
	Deck deck;
	Card boardCard;
	Rule rule;
	
	
	GameInstance(Player[] players){
		this.players = players;
}
	
	
	private void startGame() {};
	
	private void endGame() {};
	
}