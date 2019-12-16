package kbe.playermgmt;

import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
<<<<<<< HEAD

        for (Card card : cards) {
            System.out.println(card);
            Boolean b = player.getHand().contains(card);
//            System.out.println(b);
=======
        for (Card card : cards) {
            System.out.println(card);
>>>>>>> 868996d69c2c9c1fa7f072d3cd7d240c4cd026ec
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

        //initial: Karo7 Spieler, es gibt noch keinen currentPlayer ---- LOGIK richtig?
        if (instance.getCurrentPlayer() == null) { // (instance.getResult().size() != 0) {
            //für jeden Spieler in der Liste
            for (Player player : instance.getPlayers()) {

                for (Card card : player.getHand()) {
                    if (card.getSymbol().toString() == "KARO" && card.getZahl().toString() == "SIEBEN") {
                        current = player;
                        break;
                    }
                }
            }
        } else {  //dann: im uhrzeigersinn
            //Liste der Spieler im Spiel mit Reihenfolge, wie gesessen wird
            //currentplayer ++

            // für die Länge der Spielerliste suche currentplayer
            for (int i = 0; i < instance.getPlayers().size(); i++) {

//                System.out.println("size "+instance.getPlayers().size());
//                System.out.println("i " + i);

                //wenn current spieler i entspricht
                Player player = instance.getPlayers().get(i);

//                System.out.println("player i "+instance.getPlayers().get(i).getName());
//                System.out.println("player current "+instance.getCurrentPlayer().getName());

                if (player == instance.getCurrentPlayer()) {
                    //nächsten player als currentPlayer setzen

//                    System.out.println(i + 1);

                    //wichtig: bei Ende einer Runde muss der anfangen, der zuletzt gelegt hat
                    //wenn dieser keine Karten mehr hat, dann ist der nächste dran
                    //wenn ende liste dann wieder 0
                    if (i-1 % instance.getPlayers().size()-1 == 0) {
                        //wenn size = currentplayer dann 1. spieler in liste
                        current = instance.getPlayers().get(0);
                        break;
                    } else {
                        current = instance.getPlayers().get(i+1);
                        break;
                    }
                }
            }
        }
//        if (!hasCards(current)) {
//            getNextPlayer(instance);
//        }
        return current;
    }

    @Override
    public Player createPlayer(String name) {
        return new Player(name, null, null);
    }

}
