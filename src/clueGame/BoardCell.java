package clueGame;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	
	public boolean isWalkway() {
		return true;
	}
	
	public boolean isRoom() {
		return true;
	}
	
	public boolean isDoorWay() {
		return true;
	}
}
