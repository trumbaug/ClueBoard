package clueGame;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private DoorDirection doorDir;
	
	public BoardCell() {
		super();
	}
	
	public BoardCell(int row, int column, char initial, DoorDirection d) {
		super();
		this.row = row;
		this.column = column;
		this.initial = initial;
		this.doorDir = d;
	}
	public BoardCell(int row, int column, char initial, char direction) {
		super();
		this.row = row;
		this.column = column;
		this.initial = initial;
		switch (direction)
		{
			case 'L': doorDir = DoorDirection.LEFT;
						break;
			case 'U': doorDir = DoorDirection.UP;
						break;
			case 'R': doorDir = DoorDirection.RIGHT;
						break;
			case 'D': doorDir = DoorDirection.DOWN;
						break;
			default: doorDir = DoorDirection.NONE;
		}
	}
	
	public char getInitial() {
		return initial;
	}

	public DoorDirection getDoorDirection() {
		return doorDir;
	}

	public boolean isWalkway() {
		return getInitial() == 'W';
	}
	
	public boolean isRoom() {
		if (getInitial() != 'W' && getInitial() != 'X') {
			return true;
		}
		return false;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isDoorway() {
		if (doorDir != DoorDirection.NONE)
			return true;
		return false;
	}
	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", column=" + column + ", initial=" + initial + ", doorDir=" + doorDir + "]";
	}
	
}
