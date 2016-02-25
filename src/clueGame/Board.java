package clueGame;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Board {
	private int numRows;
	private int numColumns;
	public final static int BOARD_SIZE = 50;
	private BoardCell[][] board;
	private Map<Character,String> rooms;
	private Map<BoardCell, LinkedList<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;
	
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
