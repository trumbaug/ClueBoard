package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import Experiment.BoardCellEx;

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
	private Set<BoardCell> visited;	
	public Board()  {
		super();
		board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
		boardConfigFile = "ClueLayout.csv";
		roomConfigFile = "ClueLegend.txt";
		rooms = new HashMap<Character,String>();
	}
	public Board(String boardConfigFile, String roomConfigFile) {
		super();
		board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
		rooms = new HashMap<Character,String>();
	}
	public int getNumDoors() {
		return numDoors;
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public static Map<Character, String> getRooms() {
		return rooms;
	}
	public void initialize() {
		try{
			loadRoomConfig();
			loadBoardConfig();
		}
		catch (BadConfigFormatException e)
		{
			e.getMessage();
		}
		catch (FileNotFoundException e)
		{
			e.getMessage();
		}
	}
	public void loadRoomConfig()  throws FileNotFoundException, BadConfigFormatException{
		FileReader reader = null;
		try{
			reader = new FileReader(roomConfigFile);
			Scanner in = new Scanner(reader);
			String dummy;
			String[] ar;
			
			while (in.hasNext())
			{
				dummy = in.nextLine();
				ar = dummy.split(", ");
				if (!(ar[2].equals("Card") || ar[2].equals("Other")))
				{
					throw new BadConfigFormatException("Bad legend file.");
				}
				rooms.put(ar[0].charAt(0), ar[1]);
			}}
		catch (FileNotFoundException e)
		{
			throw e;
		} 
		catch (BadConfigFormatException e)
		{
			throw e;
		}
	}
	public void loadBoardConfig() throws BadConfigFormatException, FileNotFoundException{
		FileReader reader = null;
		try {
			reader = new FileReader(boardConfigFile);
			Scanner in = new Scanner(reader);
			String dummy;
			String[] ar;
			numRows = 0;
			Set<Character> checker = rooms.keySet();
			try{
				dummy = in.nextLine();
				ar = dummy.split(",");
				numColumns = ar.length;
				for (int i = 0; i < numColumns; i++)
				{
					if(!checker.contains(ar[i].charAt(0)))
					{	
						throw new BadConfigFormatException("Bad room format");
					}
					if (ar[i].length() == 1)
					{
						board[numRows][i] = new BoardCell(0,i,ar[i].charAt(0),DoorDirection.NONE);
					}
					else
					{
						board[numRows][i] = new BoardCell(0,i,ar[i].charAt(0),ar[i].charAt(1));
					}
				}
			}
			catch (Exception e)
			{	
				throw new BadConfigFormatException("Empty layout file.");}
			while (in.hasNext())
			{
				numRows++;
				dummy = in.nextLine();
				ar = dummy.split(",");
				if (ar.length != numColumns)
				{
					throw new BadConfigFormatException("Bad Columns file");
				}
				for (int i = 0; i < numColumns; i++)
				{
					if(!checker.contains(ar[i].charAt(0)))
					{
						throw new BadConfigFormatException("Bad room format");
					}
					if (ar[i].length() == 1)
					{
						board[numRows][i] = new BoardCell(0,i,ar[i].charAt(0),DoorDirection.NONE);
					}
					else
					{
						board[numRows][i] = new BoardCell(0,i,ar[i].charAt(0),ar[i].charAt(1));
					}
				}	
			 }
			 numRows++;
			}
			catch (FileNotFoundException e) {
				throw e;}
			catch (BadConfigFormatException e){
				throw e;}
	}
	public BoardCell getCellAt(int row, int column) {
		return board[row][column];
	}
	public void calcTargets(int row, int col , int pathLength) {
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		visited.add(board[row][col]);
		findAllTargets(board[row][col], pathLength);
	}
	public void calcAdjacencies() {


	for (int i = 0; i < 4; i++) {
		for (int j = 0; j < 4; j++) {
			LinkedList<BoardCell> holder = new LinkedList<BoardCell>();
			if(i != 0) holder.add(board[i-1][j]);
			if(j != 0) holder.add(board[i][j-1]);
			if(i != 3) holder.add(board[i+1][j]);
			if(j != 3) holder.add(board[i][j+1]);
			adjMatrix.put(board[i][j], holder);
		}
	}
}
	private void findAllTargets(BoardCell thisCell, int numStep)
{
	LinkedList<BoardCell> adjacentCells = adjMatrix.get(thisCell);
	for(BoardCell cell: adjacentCells)
	{
		if(visited.contains(cell)) continue;
		visited.add(cell);
		if(numStep == 1) targets.add(cell);
		else
		{
			findAllTargets(cell, numStep - 1);
		}
		visited.remove(cell);
	}
}

	public Set<BoardCell> getTargets() {
		return targets;
	}
	public LinkedList<BoardCell> getAdjList(int row, int col) {
		return adjMatrix.get(board[row][col]);
	}
}
