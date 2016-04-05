package clueGame;

import java.awt.Color;
import java.awt.Graphics;

import cluePlayers.Card;

public class BoardCell {
	private int row;
	private int column;
private int pieceDim = 40;
	private char initial;
	private char name;
	boolean isPerson;

	private DoorDirection doorDir;
	boolean isName = false;
	
	public void setName(boolean isName) {
		this.isName = isName;
	}

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
		if(direction == 'N'){
			isName = true;
		}
		if(direction == 'P'){
			isPerson = true;
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
	
	public void drawFill(Graphics g){
		Board board = new Board();
		board.initialize();
		String stringName;
		if(initial=='W') {g.setColor(Color.YELLOW);}
		else{g.setColor(Color.GRAY);}
		g.fillRect(column*pieceDim, row*pieceDim, pieceDim, pieceDim);
		
		g.setColor(Color.BLACK);
		if(isName == true){
		g.drawString(board.rooms.get(initial), column*pieceDim, row*pieceDim);
		}
		
		if(isPerson == true){
			g.setColor(Color.GREEN);
			g.drawOval(column*pieceDim, row*pieceDim, pieceDim, pieceDim);
		}
		
	}
	public void drawGrid(Graphics g){
		g.setColor(Color.GRAY);
		g.drawRect(column*pieceDim, row*pieceDim, pieceDim, pieceDim);
		

	}
	
	public void drawDoors(Graphics g) {
		g.setColor(Color.BLUE);

		if(this.isDoorway()==true){
			switch (doorDir)
			{
				case LEFT: g.fillRect((column)*pieceDim, (row)*pieceDim, 5, 40);
							break;
				case UP: g.fillRect(column*pieceDim, row*pieceDim, pieceDim, 5);
							break;
				case RIGHT: g.fillRect((column + 1)*pieceDim, row*pieceDim, 5, 40);
							break;
				case DOWN: g.fillRect(column*pieceDim, (row + 1)*pieceDim, pieceDim, 5);
							break;
				default: doorDir = DoorDirection.NONE;
			}
				
		}		
	}
	
	public void drawPeople(Graphics g, Color color) {
		g.setColor(color);
		g.fillOval(column*40, row*40, 40, 40);
		
		g.setColor(Color.BLACK);
		g.drawOval(column*40, row*40, 40, 40);
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
	
	public char getName() {
		return name;
	}

	public boolean getisName() {
		return isName;
	}
	public void setName(char name) {
		this.name = name;
	}
	
	public void setInitial(char initial) {
		this.initial = initial;
	}
	@Override
	public String toString() {
		return "BoardCell [row=" + row + ", column=" + column + ", initial=" + initial + ", doorDir=" + doorDir + "]";
	}

	


	
}
