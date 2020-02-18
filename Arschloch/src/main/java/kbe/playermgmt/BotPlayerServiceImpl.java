package kbe.playermgmt;

import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardService;
import kbe.cardmgmt.CardServiceImpl;
import kbe.frontendmgmt.FrontendController;
import kbe.gamemgmt.GameInstance;
import kbe.gamemgmt.GameInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */
@Service
public class BotPlayerServiceImpl implements BotPlayerService {

    @Autowired
    private FrontendController frontendController;
    @Autowired
    private GameInstanceService GISI;
    @Autowired
    private PlayerService PLAYSI;
    private CardService cardService = new CardServiceImpl();



    @Override
    public List<Card> setTwoEqualCards(List<Card> cardList) {

        List<Card> twoEqualCards = new LinkedList<Card>();
        boolean isEqual = true;

        Card x = (Card) cardList.get(0);
        Card y = (Card) cardList.get(1);

        int c = x.compareTo(y);
        if (c == 0) {
            isEqual = true;
        } else {
            isEqual = false;
        }

        if (isEqual) {
            twoEqualCards.add(x);
            twoEqualCards.add(y);
            return twoEqualCards;
        } else {
            twoEqualCards.add(x);
            return twoEqualCards;
        }
    }

    @Override
    public List<Card> findHigherCards(List<Card> botHandCards, GameInstance gameInstance) {
        List<Card> higherCards = new LinkedList<Card>();
        Card b = gameInstance.getBoardCards().get(0);

        for (Card card : botHandCards) {
            if (card.getZahl().compareTo(b.getZahl()) > 0) {
                higherCards.add(card);
            }
        }
        return higherCards;
    }

    @Override
    public void assOnBoard(GameInstance gameInstance) {
        try {
            if (gameInstance.getBoardCards().get(0).getZahl().toString() == "ASS") {
                gameInstance.setBoardCards(null);
            }
        } catch (NullPointerException e) {
            gameInstance.setBoardCards(null);
        } catch (IndexOutOfBoundsException IOOB) {
            gameInstance.setBoardCards(null);
        }
    }

}
