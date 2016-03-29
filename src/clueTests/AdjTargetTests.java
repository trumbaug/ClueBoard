package clueTests;

import java.util.LinkedList;
import java.util.Set;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class AdjTargetTests {
	private static Board board;
	@BeforeClass
	public static void setUp() {
		board = new Board("IndClueLayout.csv", "BoardGameKey.txt");
		board.initialize();
	}
	
	//Adjacency tests for tiles that are in a room and are not a doorway, total tests = 4;
	@Test
	public void testAdjacenciesInsideRooms()
	{
		// Test a corner
		LinkedList<BoardCell> testList = board.getAdjList(0, 0);
		assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(4, 1);
		assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(14, 19);
		//assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(5, 19);
		assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(16, 14);
		assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(14, 18);
		assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	// These tests are PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		LinkedList<BoardCell> testList = board.getAdjList(16, 15);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(16, 16)));
		// TEST DOORWAY LEFT 
		testList = board.getAdjList(2, 19);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(2, 18)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(12, 10);
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(13, 10)));
		//TEST DOORWAY UP
		testList = board.getAdjList(14, 20);
		
		assertEquals(1, testList.size());
		assertTrue(testList.contains(board.getCellAt(13, 20)));

	}

	// Test adjacency at entrance to rooms
	// These tests are GREEN in planning spreadsheet
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT
		LinkedList<BoardCell> testList = board.getAdjList(16, 16);
		assertTrue(testList.contains(board.getCellAt(16, 15)));
		assertTrue(testList.contains(board.getCellAt(16, 17)));
		assertTrue(testList.contains(board.getCellAt(15, 16)));
		assertTrue(testList.contains(board.getCellAt(17, 16)));
		assertEquals(4, testList.size());
		
		// Test beside a door direction DOWN
		testList = board.getAdjList(13, 10);
		assertTrue(testList.contains(board.getCellAt(13, 11)));
		assertTrue(testList.contains(board.getCellAt(13, 9)));
		assertTrue(testList.contains(board.getCellAt(12, 10)));
		assertEquals(3, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(17, 4);
		System.out.println(testList);
		assertTrue(testList.contains(board.getCellAt(17, 5)));
		assertTrue(testList.contains(board.getCellAt(17, 3)));
		assertTrue(testList.contains(board.getCellAt(16, 4)));
		assertTrue(testList.contains(board.getCellAt(18, 4)));
		assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(13, 20);
		assertTrue(testList.contains(board.getCellAt(14, 20)));
		assertTrue(testList.contains(board.getCellAt(12, 20)));
		assertTrue(testList.contains(board.getCellAt(13, 21)));
		assertTrue(testList.contains(board.getCellAt(13, 19)));
		assertEquals(4, testList.size());
	}

	// Test a variety of walkway scenarios
	// These tests are LIGHT PURPLE on the planning spreadsheet
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on top edge of board, just one walkway piece
		LinkedList<BoardCell> testList = board.getAdjList(0, 18);
		assertTrue(testList.contains(board.getCellAt(1, 18)));
		assertEquals(1, testList.size());

		// Test on left edge of board, three walkway pieces
		testList = board.getAdjList(7, 0);
		assertTrue(testList.contains(board.getCellAt(6, 0)));
		assertTrue(testList.contains(board.getCellAt(8, 0)));
		assertTrue(testList.contains(board.getCellAt(7, 1)));
		assertEquals(3, testList.size());

		// Test between two rooms, walkways right and left
		testList = board.getAdjList(7, 17);
		assertTrue(testList.contains(board.getCellAt(7, 16)));
		assertTrue(testList.contains(board.getCellAt(7, 18)));
		assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(10,13);
		assertTrue(testList.contains(board.getCellAt(11, 13)));
		assertTrue(testList.contains(board.getCellAt(9, 13)));
		assertTrue(testList.contains(board.getCellAt(10, 14)));
		assertTrue(testList.contains(board.getCellAt(10, 12)));
		assertEquals(4, testList.size());

		// Test on bottom edge of board, next to 1 room piece
		testList = board.getAdjList(19, 12);
		assertTrue(testList.contains(board.getCellAt(18, 12)));
		assertEquals(1, testList.size());

		// Test on right edge of board, next to 1 room piece
		testList = board.getAdjList(7, 22);
		assertTrue(testList.contains(board.getCellAt(7, 21)));
		assertEquals(1, testList.size());

		// Test on walkway next to  door that is not in the needed
		// direction to enter
		testList = board.getAdjList(13, 11);
		
		assertTrue(testList.contains(board.getCellAt(13, 12)));
		assertTrue(testList.contains(board.getCellAt(13, 10)));
		assertTrue(testList.contains(board.getCellAt(12, 11)));
		assertEquals(3, testList.size());
		
	}


	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(12, 22, 1);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(13, 22)));
		assertTrue(targets.contains(board.getCellAt(12, 21)));	

		board.calcTargets(19, 4, 1);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(18, 4)));
		assertTrue(targets.contains(board.getCellAt(19, 3)));	
	}

	// Tests of just walkways, 2 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(12, 22, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(13, 21)));
		assertTrue(targets.contains(board.getCellAt(12, 20)));

		board.calcTargets(19, 4, 1);
		targets= board.getTargets();
		assertEquals(2, targets.size());
		assertTrue(targets.contains(board.getCellAt(19, 3)));
		assertTrue(targets.contains(board.getCellAt(18, 4)));			
	}

	// Tests of just walkways, 4 steps
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(12, 22, 4);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(7, targets.size());
		assertTrue(targets.contains(board.getCellAt(10, 20)));
		assertTrue(targets.contains(board.getCellAt(11, 19)));
		assertTrue(targets.contains(board.getCellAt(12, 18)));
		assertTrue(targets.contains(board.getCellAt(13, 19)));
		assertTrue(targets.contains(board.getCellAt(14, 20)));
		assertTrue(targets.contains(board.getCellAt(13, 21)));

		// Includes a path that doesn't have enough length
		board.calcTargets(19, 4, 4);
		targets= board.getTargets();
		assertEquals(5, targets.size());
		assertTrue(targets.contains(board.getCellAt(16, 3)));
		assertTrue(targets.contains(board.getCellAt(17, 4)));	
		assertTrue(targets.contains(board.getCellAt(15, 4)));	
		assertTrue(targets.contains(board.getCellAt(18, 3)));	
		assertTrue(targets.contains(board.getCellAt(17, 5)));	
		
	}	

	// Tests of just walkways plus one door, 6 steps
	// These are LIGHT BLUE on the planning spreadsheet

	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(12, 22, 6);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(12, targets.size());
		
		assertTrue(targets.contains(board.getCellAt(10, 20)));
		assertTrue(targets.contains(board.getCellAt(12, 20)));
		assertTrue(targets.contains(board.getCellAt(14, 20)));
		
		assertTrue(targets.contains(board.getCellAt(9, 19)));
		assertTrue(targets.contains(board.getCellAt(11, 19)));
		assertTrue(targets.contains(board.getCellAt(13, 19)));
		
		assertTrue(targets.contains(board.getCellAt(10, 18)));
		assertTrue(targets.contains(board.getCellAt(12, 18)));
		
		assertTrue(targets.contains(board.getCellAt(13, 17)));
		assertTrue(targets.contains(board.getCellAt(11, 17)));
		
		assertTrue(targets.contains(board.getCellAt(12, 16)));
		assertTrue(targets.contains(board.getCellAt(13, 21)));
	}	

	// Test getting into a room
	// These are LIGHT BLUE on the planning spreadsheet

	@Test 
	public void testTargetsIntoRoom()
	{
		// One room is exactly 2 away
		board.calcTargets(17, 16, 2);
		Set<BoardCell> targets= board.getTargets();
		assertEquals(4, targets.size());
		// directly left (can't go right 2 steps)
		assertTrue(targets.contains(board.getCellAt(16, 15)));
		// directly up and down
		assertTrue(targets.contains(board.getCellAt(15, 16)));
		// one up/down, one left/right
		assertTrue(targets.contains(board.getCellAt(18, 17)));
		assertTrue(targets.contains(board.getCellAt(16, 17)));
		assertTrue(targets.contains(board.getCellAt(16, 15)));
	}

	// Test getting into room, doesn't require all steps
	// These are LIGHT BLUE on the planning spreadsheet
	//Accidentally made this target one that has already been tested by another test. It is colored green but also should be blue
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(13, 10, 3);
		Set<BoardCell> targets = board.getTargets();
		assertEquals(6, targets.size());
		// directly left
		assertTrue(targets.contains(board.getCellAt(13, 7)));
		// right then up
		assertTrue(targets.contains(board.getCellAt(11, 11)));
		assertTrue(targets.contains(board.getCellAt(12, 12)));
		// directly right
		assertTrue(targets.contains(board.getCellAt(13, 13)));
		// into the rooms
		assertTrue(targets.contains(board.getCellAt(12, 10)));		
		// right then down
		assertTrue(targets.contains(board.getCellAt(14, 12)));

	}

	// Test getting out of a room
	// These are LIGHT BLUE on the planning spreadsheet
	@Test
	public void testRoomExit()
	{
		// Take one step, essentially just the adj list
		board.calcTargets(13, 2, 1);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		assertEquals(1, targets.size());
		assertTrue(targets.contains(board.getCellAt(13, 3)));
		// Take two steps
		board.calcTargets(17, 5, 2);
		targets= board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCellAt(16, 4)));
		assertTrue(targets.contains(board.getCellAt(17, 3)));
		assertTrue(targets.contains(board.getCellAt(18, 4)));
	}

}
