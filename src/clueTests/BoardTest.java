package clueTests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class BoardTest {
	Board board;
	@Before
	public void setUp() {
		board = new Board();
	}
	
	@Test (expected = Exception.class)
	public void testExceptions() throws BadConfigFormatException{
		try {
			Board board1 = new Board("layout.txt","legend.txt");
		} catch (BadConfigFormatException e) {
				throw e;
		}
	}
	
	@Test
	public void testRoomLegend() {
		HashMap<Character, String> rooms =  board.getRooms();
		assertEquals(9,rooms.size());
		assertEquals("Kitchen",rooms.get('K'));
		assertEquals("Studio",rooms.get('S'));
		assertEquals("Office",rooms.get('O'));
		assertEquals("Bedroom",rooms.get('B'));
		assertEquals("Dining Room",rooms.get('D'));
		assertEquals("Lounge",rooms.get('L'));
		assertEquals("Garage",rooms.get('G'));
		assertEquals("Attic",rooms.get('A'));
		assertEquals("Cellar",rooms.get('C'));
		
	}
	
	@Test
	public void testBoardDimensions() {
		assertTrue(board.getNumRows() <= board.BOARD_SIZE);
		assertTrue(board.getNumColumns() <= board.BOARD_SIZE);
	}
	
	@Test
	public void testIsDoor() {
		BoardCell cell = new BoardCell(0,1,'W',DoorDirection.NONE);
		assertEquals(board.getNumDoors(),13);
		assertTrue(cell.getDoorDir() == DoorDirection.DOWN);
		assertTrue(cell.isDoorWay());
	}
	
	@Test
	public void testCorrectRoomInitial() {
		BoardCell cell = new BoardCell(0,1,'W',DoorDirection.NONE);
		assertEquals(cell.getInitial(), 'L');
		BoardCell cell1 = new BoardCell(0,1,'O',DoorDirection.NONE);
		assertEquals(cell.getInitial(), 'P');
		BoardCell cell2 = new BoardCell(0,1,'G',DoorDirection.NONE);
		assertEquals(cell.getInitial(), 'X');
	}
}
