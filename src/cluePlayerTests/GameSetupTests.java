package cluePlayerTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CluePlayers.Card;
import CluePlayers.ComputerPlayer;
import CluePlayers.HumanPlayer;
import clueGame.Board;

public class GameSetupTests {
	private static Board board;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		board = new Board();
	}

	//Tests for loading players from file correctly 
	@Test
	public void humanPlayerTest() {
		HumanPlayer human = new HumanPlayer();
		human = board.getHumanPlayer();
		//check correct loading of human player for name, color, starting location
		assertEquals(human.getName(), "Miss Scarlett");
		assertEquals(human.getColor(), Color.red);
		assertEquals(human.getColumn(), 10);
		assertEquals(human.getRow(), 17);
	}
	
	@Test
	public void computerPlayersTest(){
		LinkedList<ComputerPlayer> computerPlayers = new LinkedList <ComputerPlayer>();
		computerPlayers = board.getComputerPlayers();
		//check correct loading of first computer players
		assertEquals(computerPlayers.peekFirst().getName(), "Mrs. Peacock");
		assertEquals(computerPlayers.peekFirst().getColor(), Color.magenta);
		assertEquals(computerPlayers.peekFirst().getColumn(), 8);
		assertEquals(computerPlayers.peekFirst().getRow(), 3);
		//check correct loading of last computer players
		assertEquals(computerPlayers.peekLast().getName(), "Mrs. White");
		assertEquals(computerPlayers.peekLast().getColor(), Color.white);
		assertEquals(computerPlayers.peekLast().getColumn(), 15);
		assertEquals(computerPlayers.peekLast().getRow(), 7);
	}
	
	@Test
	public void deckTest(){
		
	}
	
	@Test
	public void dealTest(){
		
	}

}
