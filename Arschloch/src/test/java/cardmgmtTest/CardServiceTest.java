package cardmgmtTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import kbe.cardmgmt.Card;
import kbe.cardmgmt.CardService;
import kbe.cardmgmt.CardServiceImpl;
import kbe.gamemgmt.GameInstance;
import kbe.playermgmt.Player;


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
    
    @Test
    public void testSortCardsByValue() {
    	System.out.println("*********** TEST SORTCARDSBYVALUE ***********");
    	Card card1 = new Card(Card.Zahl.SIEBEN ,Card.Symbol.KARO);
    	Card card2 = new Card(Card.Zahl.ACHT, Card.Symbol.HERZ);
    	Card card3 = new Card(Card.Zahl.SIEBEN, Card.Symbol.PIK);
    	Card card4 = new Card(Card.Zahl.BUBE, Card.Symbol.KREUZ);
    	
    	List <Card> cardList1 = new ArrayList<Card>();
    	cardList1.add(card1);
    	cardList1.add(card2);
    	cardList1.add(card3);
    	cardList1.add(card4);
    	System.out.println(cardList1);
    
    	
    	List <Card> cardList2 = new ArrayList<Card>();
    	cardList2.add(card1);
    	cardList2.add(card3);
    	cardList2.add(card2);
    	cardList2.add(card4);
    	System.out.println(cardList2);
    	
    	service.sortCardsByValue(cardList1);
    	System.out.println(service.sortCardsByValue(cardList1));
    	
    	Assert.assertEquals(cardList1, cardList2);
    	
////    	List cardsOnHand = new LinkedList<Card>();
////    	Card card1 = new Card(Zahl.ZEHN, Symbol.HERZ);
////    	Card card2 = new Card(Zahl.SIEBEN, Symbol.HERZ);
////    	cardsOnHand.add(card1);
////    	cardsOnHand.add(card2);
////    	service.sortCardsByValue(cardsOnHand);
////    	Assert.assertEquals(cardsOnHand.get(0), card2);
    	
    }
//    
    @Test
    public void testGenerateDeck() {
    	System.out.println("*********** TEST GENERATEDECK ***********");
    	List<Card> deck = service.generateDeck();
    	System.out.println(deck);
    	System.out.println(deck.size());
    	Assert.assertEquals(deck.size(), 32);
    	
    	//deck testen nach Symbolen, ob Karten mit den Symbolen erstellt wurde
    	for (int i = 0; i<=deck.size();i++) {
    		if(i<=7) {
    			System.out.println(deck.get(i));
    			Assert.assertEquals(deck.get(i).getSymbol(), Card.Symbol.KARO);
    		}
    		else if(7<i && i <=15) {
    			System.out.println(deck.get(i));
    			Assert.assertEquals(deck.get(i).getSymbol(), Card.Symbol.HERZ);
    		}
    		else if(15<i && i <=23) {
    			System.out.println(deck.get(i));
    			Assert.assertEquals(deck.get(i).getSymbol(), Card.Symbol.PIK);
    		}
    		
    		else if(23<i && i <=31) {
    			System.out.println(deck.get(i));
    			Assert.assertEquals(deck.get(i).getSymbol(), Card.Symbol.KREUZ);
    		}
    	}
    	
    	//deck testen nach Zahlen

    	System.out.println("TESTEN NACH ZAHLEN");
    	for (int i = 0; i<deck.size()-1;i++) {
    		
    		if(i%8 == 0) {
    			System.out.println("****** SIEBEN testen");
    			System.out.println(deck.get(i));
    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.SIEBEN);
    		}
    		else if(i%8 == 1) {
    			System.out.println("***** ACHT testen");
    			System.out.println(deck.get(i));
    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.ACHT);
    		}
    		else if(i%8 == 2) {
    			System.out.println("***** NEUN testen");
    			System.out.println(deck.get(i));
    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.NEUN);
    		}
    		else if(i%8 == 3) {
    			System.out.println("***** ZEHN testen");
    			System.out.println(deck.get(i));
    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.ZEHN);
    		}
    		else if(i%8 == 4) {
    			System.out.println("***** BUBE testen");
    			System.out.println(deck.get(i));
    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.BUBE);
    		}
    		else if(i%8 == 5) {
    			System.out.println("***** DAME testen");
    			System.out.println(deck.get(i));
    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.DAMEN);
    		}
    		else if(i%8 == 6) {
    			System.out.println("***** KÖNIG testen");
    			System.out.println(deck.get(i));
    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.KOENIG);
    		}
    		else if(i%8 == 7) {
    			System.out.println("***** ASS testen");
    			System.out.println(deck.get(i));
    			Assert.assertEquals(deck.get(i).getZahl(), Card.Zahl.ASS);
    		}
    	}
  }
    
    @Test
    public void testShuffleDeck() {
    	System.out.println("*********** TEST SHUFFELDECK ***********");
    	Card card1 = new Card(Card.Zahl.SIEBEN ,Card.Symbol.KARO);
    	Card card2 = new Card(Card.Zahl.SIEBEN, Card.Symbol.HERZ);
    	Card card3 = new Card(Card.Zahl.NEUN, Card.Symbol.PIK);
    	Card card4 = new Card(Card.Zahl.BUBE, Card.Symbol.KREUZ);
    	Card card5 = new Card(Card.Zahl.KOENIG, Card.Symbol.KARO);  
    	
    	List <Card> cardList1 = new ArrayList<Card>();
    	cardList1.add(card1);
    	cardList1.add(card2);
    	cardList1.add(card3);
    	cardList1.add(card4);
    	cardList1.add(card5);
    	System.out.println(cardList1);
    
    	
    	List <Card> cardList2 = new ArrayList<Card>();
    	cardList2.add(card1);
    	cardList2.add(card2);
    	cardList2.add(card3);
    	cardList2.add(card4);
    	cardList2.add(card5);
    	System.out.println(cardList2);
    	Assert.assertEquals(cardList1, cardList2);
    	
    	service.shuffleDeck(cardList1);
    	System.out.println("shuffled: " + cardList1);
    	Assert.assertNotEquals(cardList1, cardList2);
    }
    
    @Test
    public void testDealCardsToPlayers() {
    	System.out.println("*********** TEST DEALCARDSTOPLAYERS ***********");
    	GameInstance gi = new GameInstance();
    	
    	Player player1 = new Player("eins", null, null);
    	Player player2 = new Player("zwei", null, null);
    	Player player3 = new Player("drei", null, null);
    	List<Player> playerList = new ArrayList<Player>();
    	playerList.add(player1);
    	playerList.add(player2);
    	playerList.add(player3);
    	
    	gi.setPlayers(playerList);
    	service.dealCardsToPlayers(gi);
    	
    	Assert.assertEquals(player1.getHand(),player1.handCards);
    	Assert.assertEquals(player2.getHand(),player2.handCards);
    	Assert.assertEquals(player3.getHand(),player3.handCards);
    	
//    	Assert.assertEquals(player1.getHand(),);
//    	
//    	List <Card> handCard1 = null;
//    	List <Card> handCard2 = null;
//    	List <Card> handCard3 = null;
//    	
//    	for(int i = 0; i<=11; i++) {
//    		handCard1.add(deck.get(i));
//    	}
//    	System.out.println(handCard1);
//    	    	
//    	
//    	for(int i = 0; i<=22;i++) {
//    		handCard2.add(deck.get(i));
//    	}
//    	
//    	for(int i = 0; i<=32;i++) {
//    		handCard2.add(deck.get(i));
//    	}
//    	
//    	Player player1 = new Player("Chris", )
//    	
//    	List<Player>

    }
    
    @Test
    public void testSwapCards3Players() {
    	System.out.println("*********** TEST SWAPCARDS 3 PLAYERS ***********");
    	GameInstance gi = new GameInstance();
    	Player president = new Player("president", null, null);
    	Player mittelkind = new Player("mittelkind", null, null);
    	Player arschloch = new Player("arschloch", null, null);
    	List<Player> resultPlayer = new ArrayList<Player>();
    	resultPlayer.add(president);
    	resultPlayer.add(mittelkind);
    	resultPlayer.add(arschloch);
    	gi.setPlayers(resultPlayer);
    	gi.setResult(president);
    	gi.setResult(mittelkind);
    	gi.setResult(arschloch);
    	
    	service.dealCardsToPlayers(gi);    	
    	/**
    	 * handkarten nochmal von jeden temporär in liste
    	 * um spaeter vergleichen zu können
    	 */
    	List<Card> handCardsArschlochVorher = new ArrayList<Card>();
    	handCardsArschlochVorher.add(service.sortCardsByValue(arschloch.handCards).get(arschloch.handCards.size()-2));
    	handCardsArschlochVorher.add(service.sortCardsByValue(arschloch.handCards).get(arschloch.handCards.size()-1));
    	List<Card> handCardsPresidentVorher = new ArrayList<Card>();
    	handCardsPresidentVorher.add(service.sortCardsByValue(president.handCards).get(0));
    	handCardsPresidentVorher.add(service.sortCardsByValue(president.handCards).get(1));
    	
    	service.swapCards(gi);
    	
    	/**
    	 * Prüfen, ob die letzen Karten von Arschloch jetzt die letzten von Präsident sind
    	 * Prüfen, ob die ersten Karten von Präsident jetzt die ersten von Arschloch sind
    	 */
    	Assert.assertEquals(handCardsArschlochVorher.get(0), president.handCards.get(president.handCards.size()-2));
    	Assert.assertEquals(handCardsArschlochVorher.get(1), president.handCards.get(president.handCards.size()-1));
    	System.out.println("presi: " + president.handCards);
    	System.out.println("arsch: " + arschloch.handCards);
    	System.out.println(handCardsPresidentVorher.get(0));
    	System.out.println(arschloch.handCards.get(arschloch.handCards.size()-2));
    	Assert.assertEquals(handCardsPresidentVorher.get(0), arschloch.handCards.get(arschloch.handCards.size()-2));
    	Assert.assertEquals(handCardsPresidentVorher.get(1), arschloch.handCards.get(arschloch.handCards.size()-1));
    }
    
    @Test
    public void testSwapCardsMorePlayers() {
    	System.out.println("*********** TEST SWAPCARDS MORE PLAYERS ***********");
    	GameInstance gi = new GameInstance();
    	Player president1 = new Player("president1", null, null);
    	Player president2 = new Player("president2", null, null);
    	Player mittelkind = new Player("mittelkind", null, null);
    	Player arschloch1 = new Player("arschloch1", null, null);
    	Player arschloch2 = new Player("arschloch2", null, null);
    	List<Player> resultPlayer = new ArrayList<Player>();
    	resultPlayer.add(president1);
    	resultPlayer.add(president2);
    	resultPlayer.add(mittelkind);
    	resultPlayer.add(arschloch2);
    	resultPlayer.add(arschloch1);
    	gi.setPlayers(resultPlayer);
    	gi.setResult(president1);
    	gi.setResult(president2);
    	gi.setResult(mittelkind);
    	gi.setResult(arschloch2);
    	gi.setResult(arschloch1);
    	
    	service.dealCardsToPlayers(gi); 
    	System.out.println("President1: " + service.sortCardsByValue(president1.getHand())+president1.getHand().size());
    	System.out.println("President2: " + service.sortCardsByValue(president2.getHand())+president2.getHand().size());
    	System.out.println("Arschloch1: " + service.sortCardsByValue(arschloch1.getHand())+ arschloch1.getHand().size());
    	System.out.println("Arschloch2: " + service.sortCardsByValue(arschloch2.getHand())+arschloch2.getHand().size());
    	System.out.println(gi.getResult().get(0).getName());
    	System.out.println(gi.getResult().get(1).getName());
    	System.out.println(gi.getResult().get(2).getName());
    	System.out.println(gi.getResult().get(3).getName());
    	System.out.println(gi.getResult().get(4).getName());
    	/**
    	 * handkarten nochmal von jeden temporär in liste
    	 * um spaeter vergleichen zu können
    	 */
    	List<Card> handCardsArschloch1Vorher = new ArrayList<Card>();
    	handCardsArschloch1Vorher.add(service.sortCardsByValue(arschloch1.handCards).get(arschloch1.handCards.size()-2));
    	handCardsArschloch1Vorher.add(service.sortCardsByValue(arschloch1.handCards).get(arschloch1.handCards.size()-1));
    	List<Card> handCardsPresident1Vorher = new ArrayList<Card>();
    	handCardsPresident1Vorher.add(service.sortCardsByValue(president1.handCards).get(0));
    	handCardsPresident1Vorher.add(service.sortCardsByValue(president1.handCards).get(1));
    	
    	List<Card> handCardsArschloch2Vorher = new ArrayList<Card>();
    	handCardsArschloch2Vorher.add(service.sortCardsByValue(arschloch2.handCards).get(arschloch2.handCards.size()-2));
    	handCardsArschloch2Vorher.add(service.sortCardsByValue(arschloch2.handCards).get(arschloch2.handCards.size()-1));
    	List<Card> handCardsPresident2Vorher = new ArrayList<Card>();
    	handCardsPresident2Vorher.add(service.sortCardsByValue(president2.handCards).get(0));
    	handCardsPresident2Vorher.add(service.sortCardsByValue(president2.handCards).get(1));
    	
    	service.swapCards(gi);
    	
    	/**
    	 * Prüfen, ob die letzen Karten von Arschloch jetzt die letzten von Präsident sind
    	 * Prüfen, ob die ersten Karten von Präsident jetzt die ersten von Arschloch sind
    	 */
    	Assert.assertEquals(handCardsArschloch1Vorher.get(0), president1.handCards.get(president1.handCards.size()-2));
    	Assert.assertEquals(handCardsArschloch1Vorher.get(1), president1.handCards.get(president1.handCards.size()-1));
    	Assert.assertEquals(handCardsPresident1Vorher.get(0), arschloch1.handCards.get(arschloch1.handCards.size()-2));
    	Assert.assertEquals(handCardsPresident1Vorher.get(1), arschloch1.handCards.get(arschloch1.handCards.size()-1));
    	
    	System.out.println("NACH SAWP");
    	System.out.println("President2: " + president2.handCards);
    	System.out.println("Arschloch2: " + arschloch2.handCards);
    	Assert.assertEquals(handCardsArschloch2Vorher.get(0), president2.handCards.get(president2.handCards.size()-2));
    	Assert.assertEquals(handCardsArschloch2Vorher.get(1), president2.handCards.get(president2.handCards.size()-1));
    	Assert.assertEquals(handCardsPresident2Vorher.get(0), arschloch2.handCards.get(arschloch2.handCards.size()-2));
    	Assert.assertEquals(handCardsPresident2Vorher.get(1), arschloch2.handCards.get(arschloch2.handCards.size()-1));
    }

}
