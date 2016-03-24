package cluePlayerTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CluePlayers.Card;
import CluePlayers.ComputerPlayer;
import CluePlayers.HumanPlayer;
import CluePlayers.Player;
import clueGame.BadConfigFormatException;
import clueGame.Board;

public class GameSetupTests {
	private static Board board;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		board = new Board();
		board.initialize();
	}

	//Tests for loading players from file correctly 
	@Test
	public void humanPlayerTest() {
		HumanPlayer human = new HumanPlayer();
		human = board.getHumanPlayer();
		//check correct loading of human player for name, color, starting location
		assertEquals("Miss Scarlett", human.getName());
		assertEquals(Color.red, human.getColor());
		assertEquals(17, human.getColumn());
		assertEquals(10, human.getRow());
	}
	
	@Test
	public void computerPlayersTest(){
		LinkedList<ComputerPlayer> computerPlayers = new LinkedList <ComputerPlayer>();
		computerPlayers = board.getComputerPlayers();
		//check correct loading of first computer players
		assertEquals("Mrs. Peacock", computerPlayers.peekFirst().getName());
		assertEquals(Color.blue,computerPlayers.peekFirst().getColor());
		assertEquals(8, computerPlayers.peekFirst().getColumn());
		assertEquals(3, computerPlayers.peekFirst().getRow());
		//check correct loading of last computer players
		assertEquals("Mrs. White", computerPlayers.peekLast().getName());
		assertEquals(Color.white, computerPlayers.peekLast().getColor());
		assertEquals(15, computerPlayers.peekLast().getColumn());
		assertEquals(7, computerPlayers.peekLast().getRow());
	}
	
	@Test
	public void deckTest() throws FileNotFoundException, BadConfigFormatException{
		LinkedList<Card> deck = new LinkedList<Card>();
		board.loadRoomConfig();
		board.loadBoardConfig();
		board.loadConfigFiles();
		board.calcAdjacencies();
		board.createDeck();
		deck = board.getDeck();
		//Make sure that the deck contains the correct amount of cards in total and the correct amount of cards in each category
		assertEquals(23, board.getDeck().size());
		assertEquals(6, board.countPersonCards(deck));
		assertEquals(6, board.countWeaponCards(deck));
		assertEquals(11, board.countRoomCards(deck));
		
		//Tests if deck contains a correct room, weapon, and suspect card
		Card weaponCard = new Card();
		weaponCard = deck.get(6);
		Card suspectCard = new Card();
		suspectCard = deck.get(0);
		Card roomCard = new Card();
		roomCard = deck.get(12);
		assertEquals("candlestick", weaponCard.getCardName());
		assertEquals(Card.CardType.WEAPON, weaponCard.getCardType());
		assertEquals("Mrs. Peacock", suspectCard.getCardName());
		assertEquals(Card.CardType.PERSON, suspectCard.getCardType());
		assertEquals("Kitchen", roomCard.getCardName());
		assertEquals(Card.CardType.ROOM, roomCard.getCardType());

		
	}
	
	@Test
	public void dealTest(){
		Player testPlayer = new Player();
		LinkedList<Card> deck = new LinkedList<Card>();
		//Test that all cards are dealt
		deck = board.getDeck();
		assertEquals(0, deck.size());
		int deckSize = board.getDeckSize();
		int handSize = deckSize/board.getAllPlayers().size();
		System.out.println(handSize);
		//Test that each player has either three or four cards in their hand. Fix 
		testPlayer = board.getAllPlayers().get(0);
		assertTrue(testPlayer.getMyCards().size() - handSize == 0 || testPlayer.getMyCards().size() - handSize == 1 );
		testPlayer = board.getAllPlayers().get(1);
		assertTrue(testPlayer.getMyCards().size() - handSize == 0 || testPlayer.getMyCards().size() - handSize == 1  );
		testPlayer = board.getAllPlayers().get(2);
		assertTrue(testPlayer.getMyCards().size() - handSize == 0 || testPlayer.getMyCards().size() - handSize == 1  );
		testPlayer = board.getAllPlayers().get(3);
		assertTrue(testPlayer.getMyCards().size() - handSize == 0 || testPlayer.getMyCards().size() - handSize == 1 );
		testPlayer = board.getAllPlayers().get(4);
		assertTrue(testPlayer.getMyCards().size() - handSize == 0 || testPlayer.getMyCards().size() - handSize == 1 );
		testPlayer = board.getAllPlayers().get(5);
		assertTrue(testPlayer.getMyCards().size() - handSize == 0 || testPlayer.getMyCards().size() - handSize == 1 );
		
		//Test whether one card is not given to more than one player
		HumanPlayer human = new HumanPlayer();
		ComputerPlayer computer = new ComputerPlayer();
		Card testCard = new Card();
		human = board.getHumanPlayer();
		computer = board.getComputerPlayers().get(4);
		testCard = human.getMyCards().get(2);
		assertFalse(computer.getMyCards().contains(testCard));
		
	}

}
