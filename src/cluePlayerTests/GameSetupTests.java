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
		Card weaponCard = new Card("candlestick", Card.CardType.WEAPON);
		Card personCard = new Card("Mrs.Peacock", Card.CardType.PERSON);
		Card roomCard = new Card("Office", Card.CardType.ROOM);
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
		//Is this ok to do?? Otherwise I have issues getting the initial deck count to work correctly 
		board.loadRoomConfig();
		board.loadBoardConfig();
		board.loadConfigFiles();
		board.calcAdjacencies();
		board.createDeck();
		deck = board.getDeck();
		
		assertEquals(23, board.getDeck().size());
		assertEquals(6, board.countPersonCards(deck));
		assertEquals(6, board.countWeaponCards(deck));
		assertEquals(11, board.countRoomCards(deck));
		
		//Need to fix these. 
		//assertTrue(deck.contains(roomCard));
		//assertTrue(deck.contains(personCard));
		//assertTrue(deck.contains(weaponCard));
		
	}
	
	
	@Test
	public void dealTest(){
		Player testPlayer = new Player();
		
		LinkedList<Card> deck = new LinkedList<Card>();
		//Test that all cards are dealt
		deck = board.getDeck();
		assertEquals(0, deck.size());
		
		//Test that each player has an equal amount of cards
		testPlayer = board.getAllPlayers().get(0);
		assertTrue(testPlayer.getMyCards().size() - 3 <= 1 && testPlayer.getMyCards().size() - 2 >= 0 );
		testPlayer = board.getAllPlayers().get(1);
		assertTrue(testPlayer.getMyCards().size() - 3 <= 1);
	}

}
