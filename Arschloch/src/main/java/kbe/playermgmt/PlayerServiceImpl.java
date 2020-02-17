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
//            System.out.println(card);
            if (player.getHand().contains(card)) {
                player.getHand().remove(card);
            } else {
                //fehlermeldung weil karten nicht in der hand
            }
        }
    }

    @Override
    public void addToHand(Player player, List<Card> cards) {
        if (!player.getHand().containsAll(cards)) {
            player.getHand().addAll(cards);
        } else {
            //fehlermeldung weil keine doppelten karten
        }
    }

    @Override
    public Player getNextPlayer(GameInstance instance) throws NullPointerException {
        Player current = null;

        //initial: Karo7 Spieler, es gibt noch keinen currentPlayer
        if (instance.getCurrentPlayer() == null) {
            current = setFirstPlayer(instance);
        } else {
            //dann: im uhrzeigersinn
            current = setNewPlayer(instance);
        }

        if (hasCards(current)) {
            return current;
        } else {
            instance.setCurrentPlayer(current);
            return getNextPlayer(instance);
        }
    }

    public Player setNewPlayer(GameInstance instance) {
        Player current = null;
        //Liste der Spieler im Spiel mit Reihenfolge, wie gesessen wird
        //currentplayer ++
        // für die Länge der Spielerliste suche currentplayer
        for (int i = 0; i < instance.getPlayers().size(); i++) {
            //wenn current spieler i entspricht
            Player player = instance.getPlayers().get(i);
            if (player == instance.getCurrentPlayer()) {
                //nächsten player als currentPlayer setzen
                //wichtig: bei Ende einer Runde muss der anfangen, der zuletzt gelegt hat
                //wenn dieser keine Karten mehr hat, dann ist der nächste dran
                //wenn ende liste dann wieder 0
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

    public Player setFirstPlayer(GameInstance instance) {
        Player current = null;
        //für jeden Spieler in der Liste
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
