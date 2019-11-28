package playermgmt;

import java.util.List;

import org.springframework.stereotype.Component;

import cardmgmt.Card;

@Component
public class PlayerServiceImpl implements PlayerService{

	public Boolean hasCards(Player player) {
		if(player.handCards.isEmpty()){
			return false;
		}
		else{
			return true;
		}
	}

	public void removeFromHand(Player player, List<Card> cards) {
		player.handCards.remove(cards);
	}

	public void addToHand(Player player, List<Card> cards) {
		player.handCards.addAll(cards);
	}

	@Override
	public void playCards(Player player, List<Card> selectedCards) {
		//Der Spieler spielt seine Karten
		//dabei muss removeFromHand aufgerufen werden
		//Wo landen die Karten?
	}

	@Override
	public Player getNextPlayer() throws NullPointerException {
		//Liste der Spieler im Spiel mit Reihenfolge, wie gesessen wird

		//wichtig: bei Ende einer Runde muss der anfangen, der zuletzt gelegt hat
			//wenn dieser keine Karten mehr hat, dann ist der nächste dran

		return null;
	}

	@Override
	public Player createPlayer(String name) {
		//wenn es schon eine voherige Runde gab, muss die Rolle übergeben werden

		//aus der CardImpl werden durch dealCardsToPlayers() die handCards festgelegt

		//der Name kommt aus dem Frontend

		return new Player(name, null, null);
	}

}
