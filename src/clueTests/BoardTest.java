package clueTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class BoardTest {
	private static Board board;
	public static final int NUM_ROOMS = 11;
	public static final int NUM_ROWS = 25;
	public static final int NUM_COLUMNS = 21;
	
	@BeforeClass
	public static void setUp() {
		board = new Board("layout.txt", "legend.txt");
		board.initialize();
	}
	
	@Test
	public void testRoomLegend() {
		Map<Character, String> rooms =  board.getRooms();
		assertEquals(NUM_ROOMS,rooms.size());
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
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}
	
	@Test
	public void testIsDoor() {
		BoardCell room = board.getCellAt(3, 12);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getCellAt(5, 11);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());
		room = board.getCellAt(13, 14);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());
		room = board.getCellAt(9, 4);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());
		// Test that room pieces that aren't doors know it
		room = board.getCellAt(3, 2);
		assertFalse(room.isDoorway());	
		// Test that walkways are not doors
		BoardCell cell = board.getCellAt(5, 3);
		assertFalse(cell.isDoorway());		
	}
	
	@Test
	public void testNumberOfDoorways() 
	{
		int numDoors = 0;
		int totalCells = board.getNumColumns() * board.getNumRows();
		Assert.assertEquals(525, totalCells);
		for (int row=0; row<board.getNumRows(); row++)
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(17, numDoors);
	}
	@Test
	public void testCorrectRoomInitial() {
		assertEquals('C', board.getCellAt(4, 10).getInitial());
		assertEquals('G', board.getCellAt(3, 2).getInitial());
		assertEquals('S', board.getCellAt(24, 20).getInitial());
		assertEquals('L', board.getCellAt(24, 12).getInitial());
		assertEquals('O', board.getCellAt(15, 16).getInitial());
		assertEquals('X', board.getCellAt(11, 10).getInitial());
		assertEquals('D', board.getCellAt(13, 3).getInitial());
	}
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		Board board = new Board("layoutBadColumns.txt", "legend.txt");
		board.loadRoomConfig();
		board.loadBoardConfig();
	}
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
		Board board = new Board("layoutBadRoom.txt", "legend.txt");
		board.loadRoomConfig();
		board.loadBoardConfig();
	}
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoomFormat() throws BadConfigFormatException, FileNotFoundException {
		Board board = new Board("layout.txt", "legendBadFormat.txt");
		board.loadRoomConfig();
	}
	@Test (expected = FileNotFoundException.class)
	public void testBadBoardFileName() throws BadConfigFormatException, FileNotFoundException {
		Board board = new Board("badname.txt", "legend.txt");
		board.loadBoardConfig();
	}
	@Test (expected = FileNotFoundException.class)
	public void testBadRoomFileName() throws BadConfigFormatException, FileNotFoundException {
		Board board = new Board("layout.txt", "badname.txt");
		board.loadRoomConfig();
	}
}
