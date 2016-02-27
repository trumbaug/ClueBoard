package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Board {
	private int numRows;
	private int numColumns;
	private int numDoors = 0;
	public final static int BOARD_SIZE = 50;
	private BoardCell[][] board;
	private static Map<Character,String> rooms;
	private Map<BoardCell, LinkedList<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;
	private void setUp() throws BadConfigFormatException
	{
		FileReader reader = null;
		try {
			reader = new FileReader(boardConfigFile);
			Scanner in = new Scanner(reader);
			/*char dummy;
			dummy = in.next().charAt(0);
			int row = 0;
			while (in.hasNextLine()) {
				int col = 0;
				while (dummy != '\n')
				{
					if(dummy != ',')
					{
						board[row][col] = new BoardCell(row,col,dummy);
						col++;
					}
					dummy = in.next().charAt(0);
				}
				numColumns = col;
				row++;
			}
			numRows = row;*/
			
			
		} catch (FileNotFoundException e) {
			throw new BadConfigFormatException("Cannot open file.!");
		}
	}
	public Board()  {
		super();
		board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
		boardConfigFile = "layout.txt";
		roomConfigFile = "legend.txt";
		try{
			setUp();
		}
		catch (Exception e)
		{
		System.out.println(e);
		}
		
	}
	public Board(String boardConfigFile, String roomConfigFile) throws BadConfigFormatException{
		super();
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
		try{
			setUp();
		}
		catch (BadConfigFormatException e)
		{
		 throw e;
		}
	}
	public int getNumDoors() {
		return numDoors;
	}
	public int getNumRows() {
		return 51;
	}
	public int getNumColumns() {
		return 51;
	}
	public static HashMap<Character, String> getRooms() {
		return new HashMap<Character, String>();
	}
	public void initialize() {
		
	}
	public void loadRoomConfig(String roomConfigFile) {
		
	}
	public void loadBoardConfig(String filename) {
		
	}
	public void calcAdjacencies() {
		
	}
	public void calcTargets(int row, int column, int pathLength) {
		
	}
	public BoardCell getCellAt(int row, int column) {
		return board[row][column];
	}
	
}
