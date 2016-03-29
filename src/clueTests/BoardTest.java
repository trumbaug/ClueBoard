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
	public static final int NUM_ROWS = 20;
	public static final int NUM_COLUMNS = 23;
	
	@BeforeClass
	public static void setUp() {
		board = new Board("IndClueLayout.csv", "BoardGameKey.txt");
		board.initialize();
	}
	
	@Test
	public void testRooms() {
		//Ensures correct number of rooms
		Map<Character, String> rooms = board.getRooms();
		assertEquals(NUM_ROOMS, rooms.size());
		//Ensures correct data for rooms
		assertEquals(rooms.get('C'), "Conservatory");
		assertEquals(rooms.get('K'), "Kitchen");
		assertEquals(rooms.get('B'), "Ballroom");
		assertEquals(rooms.get('D'), "Dining Room");
		assertEquals(rooms.get('S'), "Study");
		assertEquals(rooms.get('L'), "Library");
		assertEquals(rooms.get('X'), "Closet");
	}
	
	@Test
	public void testBoardDimensions(){
		//Ensures proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}
	
	@Test
	public void FourDoorDirections() {
		//Test doorway in every direction and some none doorways
		BoardCell room = board.getCellAt(2, 13);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.DOWN, room.getDoorDirection());
		room = board.getCellAt(13, 2);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.RIGHT, room.getDoorDirection());
		room = board.getCellAt(2, 19);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.LEFT, room.getDoorDirection());
		room = board.getCellAt(14, 20);
		assertTrue(room.isDoorway());
		assertEquals(DoorDirection.UP, room.getDoorDirection());
		// Test that room pieces that aren't doors know it
		room = board.getCellAt(11, 9);
		assertFalse(room.isDoorway());	
		// Test that walkways are not doors
		BoardCell cell = board.getCellAt(13, 4);
		assertFalse(cell.isDoorway());	
	}
	
	@Test
	public void testNumberOfDoorways() {
		int numDoors = 0;
		int totalCells = board.getNumColumns() * board.getNumRows();
		assertEquals(460, totalCells);
		for(int row = 0; row < board.getNumRows(); row++){
			for(int col = 0; col < board.getNumColumns(); col++){
				BoardCell cell = board.getCellAt(row, col);
				if(cell.isDoorway()){
					numDoors++;
				}
			}
		}
		assertEquals(9, numDoors);
	}
	
	@Test
	public void testRoomInitials() {
		//Tests a few room cells to check if initial is correct
		assertEquals('S', board.getCellAt(0, 19).getInitial());
		assertEquals('L', board.getCellAt(8, 18).getInitial());
		assertEquals('X', board.getCellAt(7, 10).getInitial());
		assertEquals('C', board.getCellAt(11, 1).getInitial());
		assertEquals('K', board.getCellAt(0, 0).getInitial());
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		Board board = new Board("layoutBadColumns.txt", "BoardGameKey.txt");
		board.loadRoomConfig();
		board.loadBoardConfig();
	}
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoom() throws BadConfigFormatException, FileNotFoundException {
		Board board = new Board("layoutBadRoom.txt", "BoardGameKey.txt");
		board.loadRoomConfig();
		board.loadBoardConfig();
	}
	@Test (expected = BadConfigFormatException.class)
	public void testBadRoomFormat() throws BadConfigFormatException, FileNotFoundException {
		Board board = new Board("IndClueLayout.csv", "legendBadFormat.txt");
		board.loadRoomConfig();
	}

}
