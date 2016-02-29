package Experiment;

public class BoardCellEx {
	private int col;
	private int row;
	String type;
	public BoardCellEx(int col, int row, String type) {
		super();
		this.col = col;
		this.row = row;
		this.type = type;
	}
	public int getCol() {
		return col;
	}
	public int getRow() {
		return row;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "BoardCell [col=" + col + ", row=" + row + ", type=" + type + "]";
	}
	
}


