package CluePlayers;

import java.awt.Color;
import java.util.HashSet;
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
		BoardCell selection = new BoardCell();
		Set<BoardCell> currentTargets = new HashSet<BoardCell>();
		//Set<BoardCell> currentVisited = new HashSet<BoardCell>();
		currentTargets = targets;
		System.out.println(currentTargets);
		//currentVisited = board.getVisited();
		for (BoardCell c : currentTargets){
			if(c.isDoorway()){
					selection = c;
					break;
			}
		}
		return selection;
	}
	
	public void makeAccusation(){
		
	}
	
	public void makeSuggestion(Board board, BoardCell location){
		
	}

}
