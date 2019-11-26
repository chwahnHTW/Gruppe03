package cardmgmt;

import java.util.List;

import org.springframework.stereotype.Component;

import gamemgmt.GameInstance;
import playermgmt.Player;

/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */

@Component
public class CardServiceImpl implements CardService {

    @Override
    public List<Card> orderCardsByValue(List<Card> cards) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Card> generateDeck(int anzahlKarten) {
    	List<Card> deck = new ArrayList<Card>();
    	
    	for (Card.Symbol symbol : Card.Symbol.values()) {
    		for (Card.Zahl zahl : Card.Zahl.values()) {
    			Card card = new Card();
    			card.zahl = zahl;
    			card.symbol = symbol;
    			deck.add(card);
    		}
    	}
        return deck;
    }

    @Override
    public List<Card> shuffleDeck(List<Card> cardList) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public void swapCards(GameInstance gameInstance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dealCardsToPlayers(GameInstance gameInstance) {
		// TODO Auto-generated method stub
		
	}


}
