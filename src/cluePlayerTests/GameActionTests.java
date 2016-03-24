package cluePlayerTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CluePlayers.ComputerPlayer;
import CluePlayers.Solution;
import clueGame.Board;
import clueGame.BoardCell;

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
	
	//Testing selecting a location with random picking
	@Test 
	public void randomTargetLocationTest(){
		ComputerPlayer player = new ComputerPlayer();
		// Pick a location with no rooms in target, just three targets
		board.calcTargets(14, 0, 2);
		boolean loc_12_0 = false;
		boolean loc_14_2 = false;
		boolean loc_15_1 = false;
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			if (selected == board.getCellAt(12, 0))
				loc_12_0 = true;
			else if (selected == board.getCellAt(14, 2))
				loc_14_2 = true;
			else if (selected == board.getCellAt(15, 1))
				loc_15_1 = true;
			else
				fail("Invalid target selected");
		}
		// Ensure each target was selected at least once
		assertTrue(loc_12_0);
		assertTrue(loc_14_2);
		assertTrue(loc_15_1);							
	}
	
	//Testing selecting a location if room is NOT last one visited 
	@Test
	public void notVisitedRoomTargetLocationTest(){
		
	}
	
	//Testing selecting a location if room is last one visited
	@Test 
	public void VisitedRoomTargetLocationTest(){
		
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
