package kbe.playermgmt;

import kbe.cardmgmt.Card;
import kbe.gamemgmt.GameInstance;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @authors Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 */
@Service
public class BotPlayerServiceImpl implements BotPlayerService {

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
    public void setEqualCards(GameInstance gameInstance, List<Card> higherCards){
    	List<Card> temp = new LinkedList<Card>();
    	List<Card> cardsToPlay = new LinkedList<Card>();
    	// und die gleiche zahl haben
    	if(gameInstance.getBoardCards().size() == 2) {
    		System.out.println("Boardcards 2");
    		for (int i = 1; i < higherCards.size(); i++) {
                if (higherCards.get(i).getZahl() == higherCards.get(i-1).getZahl()) {
                    temp.add(higherCards.get(i-1));
                    temp.add(higherCards.get(i));
                    break;
                }
            }
    		System.out.println("temp: " + temp.toString());
    	} else if (gameInstance.getBoardCards().size() == 3 && higherCards.size() >= 3) {
    		System.out.println("Boardcards 3");
    		for (int i = 1; i <= higherCards.size(); i++) {
    			System.out.println(i);
                if (higherCards.get(i).getZahl() == higherCards.get(i-1).getZahl()) {
                	if(higherCards.get(i).getZahl() == higherCards.get(i+1).getZahl()) {
                		temp.add(higherCards.get(i-1));
                        temp.add(higherCards.get(i));
                        temp.add(higherCards.get(i+1));
                	}
                	break;
                }
    		}
    		System.out.println("temp: " + temp.toString());
    	} else if (gameInstance.getBoardCards().size() == 4 && higherCards.size() >= 4) {
    		System.out.println("Boardcards 4");
    		for (int i = 1; i <= higherCards.size(); i++) {
                if (higherCards.get(i).getZahl() == higherCards.get(i-1).getZahl()){
                	if(higherCards.get(i).getZahl() == higherCards.get(i+1).getZahl()) {
                		if(higherCards.get(i).getZahl() == higherCards.get(i+2).getZahl()) {
                			temp.add(higherCards.get(i-1));
                            temp.add(higherCards.get(i));
                            temp.add(higherCards.get(i+1));
                            temp.add(higherCards.get(i+2));
                            
                		}
                		
                	}break;
                }
                    
    		}
    		System.out.println("temp: " + temp.toString());
    	} else {
    		botPass(gameInstance);
    	}

        
        if (temp.size() < gameInstance.getBoardCards().size()) {
        	System.out.println("temp.size() < gameInstance.getBoardCards().size()");
            botPass(gameInstance);
        } else {
            for (int i = 0; i < gameInstance.getBoardCards().size(); i++) {
            	cardsToPlay.add(temp.get(i));
            }
            System.out.println(cardsToPlay);
            updateAll(cardsToPlay, gameInstance);
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
