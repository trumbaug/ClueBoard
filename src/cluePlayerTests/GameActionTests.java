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
		Solution test = new Solution();
		test.person = "Miss Scarlett";
		test.room = "Library";
		test.weapon = "candlestick";
		board.setTheAnswer(test);
		assertTrue(board.checkAccusation(test));
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
