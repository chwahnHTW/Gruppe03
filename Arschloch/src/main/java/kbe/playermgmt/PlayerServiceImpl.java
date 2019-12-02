package kbe.playermgmt;

import java.util.List;

import kbe.gamemgmt.GameInstance;
import org.springframework.stereotype.Component;

import kbe.cardmgmt.Card;

@Component
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
        if (player.getHand().containsAll(cards)) {
            player.getHand().remove(cards);
        } else {
            //fehlermeldung weil karten nicht in der hand
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
    public void playCards(Player player, List<Card> selectedCards) {
        //gehört eigentlich ins Frontend
        //Der Spieler spielt seine Karten
        //dabei muss removeFromHand aufgerufen werden
        //Wo landen die Karten?

        removeFromHand(player, selectedCards);
    }

    @Override
    public Player getNextPlayer(GameInstance instance) throws NullPointerException {
        //initial: Karo7 Spieler, es gibt noch keinen currentPlayer ---- LOGIK richtig?
        if (instance.getCurrentPlayer().equals(0)) { // (instance.getResult().size() != 0) {
            //für jeden Spieler in der Liste
            Card initial = new Card(Card.Zahl.SIEBEN, Card.Symbol.KARO);
            for (int i = 0; i <= instance.getPlayers().size(); i++) {
                //suche die Karo7 in den HandCards
                if (instance.getPlayers().get(i).getHand().contains(initial)) {
                    //und eigentlich noch setCurrentPlayer
                    //instance.setCurrentPlayer(instance.getPlayers().get(i));
                    return instance.getPlayers().get(i);
                }
            }
        } else {  //dann: im uhrzeigersinn
            //Liste der Spieler im Spiel mit Reihenfolge, wie gesessen wird
            //currentplayer ++
            Player current = instance.getCurrentPlayer();
            for (int i = 0; i <= instance.getPlayers().size(); i++) {
                //suche currentplayer
                if (instance.getPlayers().get(i).equals(current)) {
                    int x = i;
                    x++;
                    //nächsten player als nextPlayer setzen

                    //wenn ende liste dann wieder 0
                    //wenn size = currentplayer dann 1. spieler in liste

                    //wenn nächster in liste keine karten dann der darauffolgende
                    Player next = instance.getPlayers().get(x);
                    if(hasCards(next)){
                        return next;
                    }
                }
            }
        }
        //wichtig: bei Ende einer Runde muss der anfangen, der zuletzt gelegt hat
        //wenn dieser keine Karten mehr hat, dann ist der nächste dran
        return null;
    }

    @Override
    public Player createPlayer(String name) {
        return new Player(name, null, null);
    }

}
