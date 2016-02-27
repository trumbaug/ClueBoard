package clueGame;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private DoorDirection doorDir;
	
	
	public BoardCell(int row, int column, char initial, DoorDirection d) {
		super();
		this.row = row;
		this.column = column;
		this.initial = initial;
		this.doorDir = d;
	}

	public char getInitial() {
		return initial;
	}

	public DoorDirection getDoorDir() {
		return doorDir;
	}

	public boolean isWalkway() {
		return true;
	}
	
	public boolean isRoom() {
		return true;
	}
	
	public boolean isDoorWay() {
		if (doorDir != DoorDirection.NONE)
			return true;
		return false;
	}
}
