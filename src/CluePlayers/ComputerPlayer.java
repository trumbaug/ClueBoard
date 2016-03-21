package CluePlayers;

import java.awt.Color;
import java.util.Set;

import clueGame.Board;
import clueGame.BoardCell;

public class ComputerPlayer extends Player{
	
	
	public ComputerPlayer() {
		super();
	}

	public ComputerPlayer(int column, int row, String playerName, Color color) {
		super(column, row, playerName, color);
	}

	public BoardCell pickLocation(Set<BoardCell> targets){
		BoardCell cell = null;
		return cell;
	}
	
	public void makeAccusation(){
		
	}
	
	public void makeSuggestion(Board board, BoardCell location){
		
	}

}
