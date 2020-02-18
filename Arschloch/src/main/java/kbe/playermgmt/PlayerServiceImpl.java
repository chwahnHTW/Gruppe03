package kbe.playermgmt;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Override
    public Boolean hasCards(Player player) {
        if (player.getHand().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void removeFromHand(Player player, List<Card> cards) {
        for (Card card : cards) {
            if (player.getHand().contains(card)) {
                player.getHand().remove(card);
            }
        }
    }

    @Override
    public void addToHand(Player player, List<Card> cards) {
        if (!player.getHand().containsAll(cards)) {
            player.getHand().addAll(cards);
        }
    }

    @Override
    public Player getNextPlayer(GameInstance instance) throws NullPointerException {
        Player current = null;
        if (instance.getCurrentPlayer() == null) {
            current = setFirstPlayer(instance);
        } else {
            current = setNewPlayer(instance);
        }
        if (hasCards(current)) {
            return current;
        } else {
            instance.setCurrentPlayer(current);
            return getNextPlayer(instance);
        }
    }

    @Override
    public Player setNewPlayer(GameInstance instance) {
        Player current = null;
        for (int i = 0; i < instance.getPlayers().size(); i++) {
            Player player = instance.getPlayers().get(i);
            if (player == instance.getCurrentPlayer()) {
                if (i + 1 == instance.getPlayers().size()) {
                    current = instance.getPlayers().get(0);
                    break;
                } else {
                    current = instance.getPlayers().get(i + 1);
                    break;
                }
            }
        }
        return current;
    }

    @Override
    public Player setFirstPlayer(GameInstance instance) {
        Player current = null;
        for (Player player : instance.getPlayers()) {
            for (Card card : player.getHand()) {
                if (card.getSymbol().toString() == "KARO" && card.getZahl().toString() == "SIEBEN") {
                    current = player;
                    break;
                }
            }
        }
        return current;
    }

    @Override
    public Player createPlayer(String name) {
        return new Player(name, null, null);
    }

}
