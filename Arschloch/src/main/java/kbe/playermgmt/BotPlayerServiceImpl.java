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
    public void validateBotMove(GameInstance gameInstance, int passCounter) {
        List<Card> cardsToPlay = new LinkedList<Card>();
        List<Card> botHandCards = new LinkedList<Card>();
        botHandCards = cardService.sortCardsByValue(gameInstance.getCurrentPlayer().getHand());
        List<Card> higherCards = new LinkedList<Card>();
        if (gameInstance.getBoardCards() == null) {
            if (botHandCards.size() < 2) {
                cardsToPlay = botHandCards;
            } else if (botHandCards.size() >= 2) {
                cardsToPlay = setTwoEqualCards(botHandCards);
            }
            updateAll(cardsToPlay, gameInstance);
        } else if (gameInstance.getBoardCards().size() == 1) {
            higherCards = findHigherCards(botHandCards, gameInstance);
            if (higherCards.isEmpty()) {
                botPass(gameInstance, passCounter);
            } else {
                cardsToPlay.add(higherCards.get(0));
                updateAll(cardsToPlay, gameInstance);
            }
        } else if (gameInstance.getBoardCards().size() == 2) {
            List<Card> temp = new LinkedList<Card>();
            higherCards = findHigherCards(botHandCards, gameInstance);
            if (higherCards.isEmpty()) {
                botPass(gameInstance, passCounter);
            } else {
                for (int i = 1; i < higherCards.size(); i++) {
                    if (higherCards.get(i - 1).getZahl().equals(higherCards.get(i).getZahl())) {
                        temp.add(higherCards.get(i - 1));
                        temp.add(higherCards.get(i));
                    }
                }
                if (temp.size() < 2) {
                    botPass(gameInstance, passCounter);
                } else {
                    cardsToPlay.add(temp.get(0));
                    cardsToPlay.add(temp.get(1));
                    updateAll(cardsToPlay, gameInstance);
                }
            }
        }
        assOnBoard(gameInstance);
    }

    @Override
    public void updateAll(List<Card> cardsToPlay, GameInstance gameInstance) {
        gameInstance.setBoardCards(cardsToPlay);
        PLAYSI.removeFromHand(gameInstance.getCurrentPlayer(), cardsToPlay);
        frontendController.addCurrentPlayerToResult(gameInstance);
        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
        frontendController.setPassCounter(0);
    }

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
    public void botPass(GameInstance gameInstance, int passCounter) {
        passCounter++;
        gameInstance.setCurrentPlayer(PLAYSI.getNextPlayer(gameInstance));
        int playersWithCardsCounter = 0;
        for (Player player : gameInstance.getPlayers()) {
            if (PLAYSI.hasCards(player)) {
                playersWithCardsCounter++;
            }
        }
        if (passCounter == playersWithCardsCounter - 1) {
            gameInstance.setBoardCards(null);
        }
        frontendController.setPassCounter(passCounter);
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
