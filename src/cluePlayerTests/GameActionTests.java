package cluePlayerTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CluePlayers.Solution;
import clueGame.Board;

public class GameActionTests {
	private static Board board;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		board = new Board();
		board.initialize();
	}

	@Test
	public void accusationTest() {
		//Check that checkAccusation returns true if the correct solution is passed
		Solution test = new Solution();
		test.person = "Miss Scarlett";
		test.room = "Library";
		test.weapon = "candlestick";
		board.setTheAnswer(test);
		assertTrue(board.checkAccusation(test));
		//Check that checkAccusation returns false if the incorrect weapon is passed
		Solution test1 = new Solution();
		test1.person = "Miss Scarlett";
		test1.room = "Library";
		test1.weapon = "lead pipe";
		assertFalse(board.checkAccusation(test1));
		//Check that checkAccusation returns false if the incorrect room is passed
		Solution test2 = new Solution();
		test2.person = "Miss Scarlett";
		test2.room = "Conservatory";
		test2.weapon = "candlestick";
		assertFalse(board.checkAccusation(test2));
		//Check that checkAccusation returns false if the incorrect person is passed
		Solution test3 = new Solution();
		test3.person = "Colonel Mustard";
		test3.room = "Library";
		test3.weapon = "candlestick";
		assertFalse(board.checkAccusation(test3));
		//Check that checkAccusation returns false if the incorrect person, weapon and room are passed
		Solution test4 = new Solution();
		test4.person = "Colonel Mustard";
		test4.room = "Conservatory";
		test4.weapon = "lead pipe";
		assertFalse(board.checkAccusation(test4));
	}
	
	@Test 
	public void targetLocationTest(){
		
	}
	
	@Test
	public void disproveSuggestionTest(){
		//Test for one player, one correct match
		//Test for one player, multiple possible matches
		//Test that all players queried
	}
	
	@Test
	public void computerPlayerSuggestionTest(){
		
	}

}
