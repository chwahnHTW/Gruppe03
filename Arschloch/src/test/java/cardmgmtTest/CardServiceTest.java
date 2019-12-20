package cardmgmtTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardService;
import kbe.cardmgmt.CardServiceImpl;
import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;
import kbe.cardmgmt.Card.Zahl;
import kbe.cardmgmt.Card.Symbol;


/**
 * @authors         Kaya Löher 				| Kim Anh Nguyen 		| Christian Wahnsiedler
 * Email-Adresse: 	s0564784@htw-berlin.de	| s0563958@htw-berlin.de| s0557193@htw-berlin.de
 *
 * In dieser Klasse werden alle Methoden aus dem Karten-Management getestet
 */
public class CardServiceTest {

    private CardService service;

    @Before
    public void setUp() {
        service = new CardServiceImpl();
    }
    
//    @Test
//    public void testSortCardsByValue() {
//    	
//    	Card card1 = new Card(Card.Zahl.SIEBEN ,Card.Symbol.KARO);
//    	Card card2 = new Card(Card.Zahl.ACHT, Card.Symbol.HERZ);
//    	Card card3 = new Card(Card.Zahl.SIEBEN, Card.Symbol.PIK);
//    	Card card4 = new Card(Card.Zahl.BUBE, Card.Symbol.KREUZ);
//    	
//    	List <Card> cardList1 = new ArrayList<Card>();
//    	cardList1.add(card1);
//    	cardList1.add(card2);
//    	cardList1.add(card3);
//    	cardList1.add(card4);
//    	System.out.println(cardList1);
//    
//    	
//    	List <Card> cardList2 = new ArrayList<Card>();
//    	cardList2.add(card1);
//    	cardList2.add(card3);
//    	cardList2.add(card2);
//    	cardList2.add(card4);
//    	System.out.println(cardList2);
//    	
//    	service.sortCardsByValue(cardList1);
//    	System.out.println(service.sortCardsByValue(cardList1));
//    	
//    	Assert.assertEquals(cardList1, cardList2);
//    	
////    	List cardsOnHand = new LinkedList<Card>();
////    	Card card1 = new Card(Zahl.ZEHN, Symbol.HERZ);
////    	Card card2 = new Card(Zahl.SIEBEN, Symbol.HERZ);
////    	cardsOnHand.add(card1);
////    	cardsOnHand.add(card2);
////    	service.sortCardsByValue(cardsOnHand);
////    	Assert.assertEquals(cardsOnHand.get(0), card2);
//    	
//    }
//    
//    @Test
//    public void testGenerateDeck() {
//    	List<Card> deck = service.generateDeck();
//    	System.out.println(deck);
//    	System.out.println(deck.size());
//    	Assert.assertEquals(deck.size(), 32);
//    	
//    	//deck testen nach Symbolen, ob Karten mit den Symbolen erstellt wurde
//    	for (int i = 0; i<=deck.size();i++) {
//    		if(i<=7) {
//    			System.out.println(deck.get(i));
//    			Assert.assertEquals(deck.get(i).getSymbol(), Card.Symbol.KARO);
//    		}
//    		else if(7<i && i <=15) {
//    			System.out.println(deck.get(i));
//    			Assert.assertEquals(deck.get(i).getSymbol(), Card.Symbol.HERZ);
//    		}
//    		else if(15<i && i <=23) {
//    			System.out.println(deck.get(i));
//    			Assert.assertEquals(deck.get(i).getSymbol(), Card.Symbol.PIK);
//    		}
//    		
//    		else if(23<i && i <=31) {
//    			System.out.println(deck.get(i));
//    			Assert.assertEquals(deck.get(i).getSymbol(), Card.Symbol.KREUZ);
//    		}
//    	}
//    	
//    	//deck testen nach Zahlen
//
//    	System.out.println("TESTEN NACH ZAHLEN");
//    	for (int i = 0; i<deck.size()-1;i++) {
//    		
//    		if(i%8 == 0) {
//    			System.out.println("****** SIEBEN testen");
//    			System.out.println(deck.get(i));
//    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.SIEBEN);
//    		}
//    		else if(i%8 == 1) {
//    			System.out.println("***** ACHT testen");
//    			System.out.println(deck.get(i));
//    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.ACHT);
//    		}
//    		else if(i%8 == 2) {
//    			System.out.println("***** NEUN testen");
//    			System.out.println(deck.get(i));
//    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.NEUN);
//    		}
//    		else if(i%8 == 3) {
//    			System.out.println("***** ZEHN testen");
//    			System.out.println(deck.get(i));
//    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.ZEHN);
//    		}
//    		else if(i%8 == 4) {
//    			System.out.println("***** BUBE testen");
//    			System.out.println(deck.get(i));
//    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.BUBE);
//    		}
//    		else if(i%8 == 5) {
//    			System.out.println("***** DAME testen");
//    			System.out.println(deck.get(i));
//    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.DAMEN);
//    		}
//    		else if(i%8 == 6) {
//    			System.out.println("***** KÖNIG testen");
//    			System.out.println(deck.get(i));
//    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.KOENIG);
//    		}
//    		else if(i%8 == 7) {
//    			System.out.println("***** ASS testen");
//    			System.out.println(deck.get(i));
//    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.ASS);
//    		}
//    	}
//  }
//    
//    @Test
//    public void testShuffleDeck() {
//    	Card card1 = new Card(Card.Zahl.SIEBEN ,Card.Symbol.KARO);
//    	Card card2 = new Card(Card.Zahl.SIEBEN, Card.Symbol.HERZ);
//    	Card card3 = new Card(Card.Zahl.NEUN, Card.Symbol.PIK);
//    	Card card4 = new Card(Card.Zahl.BUBE, Card.Symbol.KREUZ);
//    	Card card5 = new Card(Card.Zahl.KOENIG, Card.Symbol.KARO);  
//    	
//    	List <Card> cardList1 = new ArrayList<Card>();
//    	cardList1.add(card1);
//    	cardList1.add(card2);
//    	cardList1.add(card3);
//    	cardList1.add(card4);
//    	cardList1.add(card5);
//    	System.out.println(cardList1);
//    
//    	
//    	List <Card> cardList2 = new ArrayList<Card>();
//    	cardList2.add(card1);
//    	cardList2.add(card2);
//    	cardList2.add(card3);
//    	cardList2.add(card4);
//    	cardList2.add(card5);
//    	System.out.println(cardList2);
//    	Assert.assertEquals(cardList1, cardList2);
//    	
//    	service.shuffleDeck(cardList1);
//    	System.out.println("shuffled: " + cardList1);
//    	Assert.assertNotEquals(cardList1, cardList2);
//    }
//    
//    @Test
//    public void testDealCardsToPlayers() {
//    	GameInstance gi = new GameInstance();
//    	List <Card> deck = service.shuffleDeck(service.generateDeck());
//    	System.out.println(deck);
//    	
//    	Player player1 = new Player("eins", null, Player.Role.ARSCHLOCH);
//    	Player player2 = new Player("zwei", null, Player.Role.MITTELKIND);
//    	Player player3 = new Player("drei", null, Player.Role.PRAESIDENT);
//    	List<Player> playerList = new ArrayList<Player>();
//    	playerList.add(player1);
//    	playerList.add(player2);
//    	playerList.add(player3);
//    	
//    	gi.setPlayers(playerList);
//    	System.out.println(playerList);
//    	System.out.println(gi.players.size());
//    	service.dealCardsToPlayers(gi);
//    	
//    	Assert.assertEquals(player1.getHand(),player1.handCards);
//    	Assert.assertEquals(player2.getHand(),player2.handCards);
//    	Assert.assertEquals(player3.getHand(),player3.handCards);
//    	
////    	Assert.assertEquals(player1.getHand(),);
////    	
////    	List <Card> handCard1 = null;
////    	List <Card> handCard2 = null;
////    	List <Card> handCard3 = null;
////    	
////    	for(int i = 0; i<=11; i++) {
////    		handCard1.add(deck.get(i));
////    	}
////    	System.out.println(handCard1);
////    	    	
////    	
////    	for(int i = 0; i<=22;i++) {
////    		handCard2.add(deck.get(i));
////    	}
////    	
////    	for(int i = 0; i<=32;i++) {
////    		handCard2.add(deck.get(i));
////    	}
////    	
////    	Player player1 = new Player("Chris", )
////    	
////    	List<Player>
//
//    }
    
    //////////////////////////////////////////
    
    @Test
    public void testSwapCards() {
    	GameInstance gi = new GameInstance();
    	Player president = new Player("drei", null, Player.Role.PRAESIDENT);
    	Player mittelkind = new Player("zwei", null, Player.Role.MITTELKIND);
    	Player arschloch = new Player("eins", null, Player.Role.ARSCHLOCH);
    	List<Player> resultPlayer = new ArrayList<Player>();
    	resultPlayer.add(arschloch);
    	resultPlayer.add(mittelkind);
    	resultPlayer.add(president);
    	gi.setResult(president);
    	gi.setResult(mittelkind);
    	gi.setResult(arschloch);
    	gi.setPlayers(resultPlayer);
    	service.dealCardsToPlayers(gi);
    	
//    	System.out.println("result am index 0: " + gi.getResult().get(0).getRole());
//    	System.out.println("result am index 1: " + gi.getResult().get(1).getRole());
//    	System.out.println("result am index 2: " + gi.getResult().get(2).getRole());
//    	System.out.println(gi.getPlayers().get(0).getHand().size());
//    	System.out.println(gi.players.size());
    	
    	/**
    	 * handkarten nochmal von jeden temporär in liste
    	 * um spaeter vergleichen zu können
    	 */
//    	System.out.println(arschloch.handCards.size());
//    	System.out.println(mittelkind.handCards.size());
//    	System.out.println(president.handCards.size());
    	
    	List<Card> handCardsArschlochVorher = arschloch.handCards;
    	System.out.println("handCardsArschlochVorher: " + service.sortCardsByValue(handCardsArschlochVorher));
//    	List<Card> handCardsMittel = mittelkind.handCards; //nicht relevant
    	List<Card> handCardsPresidentVorher = president.handCards;
    	
//    	System.out.println(arschloch.handCards);
//    	service.sortCardsByValue(arschloch.handCards);
//    	System.out.println(arschloch.handCards);
//    	System.out.println(arschloch.handCards.get(arschloch.handCards.size()-2));
//    	System.out.println(arschloch.handCards.get(arschloch.handCards.size()-1));
    	
    	service.swapCards(gi);
    	
    	System.out.println("");
    	System.out.println("Ass vorher: " + handCardsArschlochVorher);
    	System.out.println("Ass vorher sortiert: " + service.sortCardsByValue(handCardsArschlochVorher) + handCardsArschlochVorher.size());
    	System.out.println("Ass nach swap: " + service.sortCardsByValue(gi.getPlayers().get(2).handCards) + gi.getPlayers().get(2).handCards.size());
    	System.out.println("Ass nach swap: " + service.sortCardsByValue(arschloch.handCards));
    	System.out.println("");
    	System.out.println("President vorher: " + handCardsPresidentVorher);
    	System.out.println("President vorher sortiert: " + service.sortCardsByValue(handCardsPresidentVorher) + handCardsArschlochVorher.size());
    	System.out.println("President nach swap: " + service.sortCardsByValue(gi.getPlayers().get(0).handCards) + gi.getPlayers().get(0).handCards.size());
    	
    	/**
    	 * Prüfen, ob die letzen Karten von Arschloch jetzt die letzten von Präsident sind
    	 * Prüfen, ob die ersten Karten von Präsident jetzt die ersten von Arschloch sind
    	 */
//    	Assert.assertEquals(handCardsArschlochVorher.get(handCardsArschlochVorher.size()-2), president.handCards.get(president.handCards.size()-1));
//    	System.out.println(handCardsArschlochVorher);
//    	System.out.println(handCardsArschlochVorher.get(handCardsArschlochVorher.size()-2));
//    	System.out.println(president.handCards);
//    	System.out.println(president.handCards.get(president.handCards.size()-1));
    }
    
//   @Test
//    public void testSwapCards() {
//    	List players = new ArrayList<Player>();
//	   //Player player1 = new Player();
//	   List deck = service.generateDeck();
//	   
//	   for(int i = 0; i<=4; i++) {
//		   
//	   }
//   }

}
