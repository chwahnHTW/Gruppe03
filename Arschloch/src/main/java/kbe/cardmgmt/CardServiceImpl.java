package kbe.cardmgmt;

import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */
@Service
public class CardServiceImpl implements CardService {

    @Override
    public List<Card> sortCardsByValue(List<Card> cards) {
        Collections.sort(cards, new CardComparator());
        return cards;

    }

    @Override
    public List<Card> generateDeck() {
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
    public List<Card> shuffleDeck(List<Card> deck) {
        Collections.shuffle(deck);
        return deck;
    }

    @Override
    public void swapCards(GameInstance gameInstance) {
        Player president1 = gameInstance.getResult().get(0);
        Player president2 = gameInstance.getResult().get(1);
        int resultSize = gameInstance.getResult().size();
        Player arschloch1 = gameInstance.getResult().get(resultSize - 1);
        Player arschloch2 = gameInstance.getResult().get(resultSize - 2);
        sortCardsByValue(president1.getHand());
        sortCardsByValue(president2.getHand());
        sortCardsByValue(arschloch1.getHand());
        sortCardsByValue(arschloch2.getHand());

        List<Card> temp1 = new ArrayList<Card>();

        int handArschloch1 = arschloch1.getHand().size();
        temp1.add(arschloch1.getHand().get(handArschloch1 - 2));
        temp1.add(arschloch1.getHand().get(handArschloch1 - 1));

        temp1.add(president1.getHand().get(0));
        temp1.add(president1.getHand().get(1));

        president1.setHand(temp1.get(0));
        president1.setHand(temp1.get(1));
        arschloch1.setHand(temp1.get(2));
        arschloch1.setHand(temp1.get(3));

        arschloch1.getHand().remove(arschloch1.getHand().size() - 3);
        arschloch1.getHand().remove(arschloch1.getHand().size() - 3);

        president1.getHand().remove(0);
        president1.getHand().remove(0);

        if (gameInstance.getResult().size() > 3) {
            List<Card> temp2 = new ArrayList<Card>();

            int handArschloch2 = arschloch2.getHand().size();
            temp2.add(arschloch2.getHand().get(handArschloch2 - 2));
            temp2.add(arschloch2.getHand().get(handArschloch2 - 1));

            temp2.add(president2.getHand().get(0));
            temp2.add(president2.getHand().get(1));

            president2.setHand(temp2.get(0));
            president2.setHand(temp2.get(1));
            arschloch2.setHand(temp2.get(2));
            arschloch2.setHand(temp2.get(3));

            arschloch2.getHand().remove(arschloch2.getHand().size() - 3);
            arschloch2.getHand().remove(arschloch2.getHand().size() - 3);

            president2.getHand().remove(0);
            president2.getHand().remove(0);
        }
    }

    @Override
    public void dealCardsToPlayers(GameInstance gameInstance) {
        List<Card> deck = shuffleDeck(generateDeck());

        int anzahlPlayer = gameInstance.getPlayers().size();

        for (int i = 0; i < deck.size(); i++) {
            gameInstance.getPlayers().get(i % anzahlPlayer).setHand(deck.get(i));
        }

        for (int j = 0; j < gameInstance.getPlayers().size(); j++) {
            sortCardsByValue(gameInstance.getPlayers().get(j).getHandCards());
        }
    }
}