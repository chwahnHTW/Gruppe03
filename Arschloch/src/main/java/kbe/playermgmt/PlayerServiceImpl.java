package kbe.playermgmt;

import java.util.List;

import kbe.gamemgmt.GameInstance;
import org.springframework.stereotype.Service;

import kbe.cardmgmt.Card;

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
            System.out.println(card);
            Boolean b = player.getHand().contains(card);
//            System.out.println(b);
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
        //initial: Karo7 Spieler, es gibt noch keinen currentPlayer ---- LOGIK richtig?
        if (instance.getCurrentPlayer() == null) { // (instance.getResult().size() != 0) {
            //für jeden Spieler in der Liste
            for (Player player : instance.getPlayers()) {

                for (Card card : player.handCards) {
                    if (card.getSymbol().toString() == "KARO" && card.getZahl().toString() == "SIEBEN") {
                        instance.setCurrentPlayer(player);
                        return instance.getCurrentPlayer();
                    }
                }
            }
        } else {  //dann: im uhrzeigersinn
            //Liste der Spieler im Spiel mit Reihenfolge, wie gesessen wird
            //currentplayer ++

            // für die Länge der Spielerliste suche currentplayer
            for (int i = 0; i <= instance.getPlayers().size(); i++) {
                //wenn current spieler i entspricht
                if (instance.getPlayers().get(i).equals(instance.getCurrentPlayer())) {
                    //nächsten player als currentPlayer setzen
                    int x = i;
                    x++;
                    //wichtig: bei Ende einer Runde muss der anfangen, der zuletzt gelegt hat
                    //wenn dieser keine Karten mehr hat, dann ist der nächste dran
                    //wenn ende liste dann wieder 0
                    if (instance.getPlayers().size() <= x) {
                        instance.setCurrentPlayer(instance.getPlayers().get(x));
                        if (hasCards(instance.getCurrentPlayer())) {
                            return instance.getCurrentPlayer();
                        } else {
                            getNextPlayer(instance);
                        }
                        //wenn size = currentplayer dann 1. spieler in liste
                    } else {
                        instance.setCurrentPlayer(instance.getPlayers().get(0));
                        if (hasCards(instance.getCurrentPlayer())) {
                            return instance.getCurrentPlayer();
                        } else {
                            getNextPlayer(instance);
                        }
                    }
                }
            }
        }
        return instance.getCurrentPlayer();
    }

    @Override
    public Player createPlayer(String name) {
        return new Player(name, null, null);
    }

}
