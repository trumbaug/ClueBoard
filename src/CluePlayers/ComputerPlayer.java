package CluePlayers;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import clueGame.Board;
import clueGame.BoardCell;
import javafx.scene.control.Cell;

public class ComputerPlayer extends Player{
	
	
	public ComputerPlayer() {
		super();
	}

	public ComputerPlayer(int column, int row, String playerName, Color color) {
		super(column, row, playerName, color);
	}

	public BoardCell pickLocation(Set<BoardCell> targets){
		int targetsSize = targets.size();
		int intCounter = 0;
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(targetsSize);
		BoardCell selection = new BoardCell();
		Set<BoardCell> currentTargets = new HashSet<BoardCell>();
		currentTargets = targets;
		for (BoardCell c : currentTargets){
			if(c.isDoorway()){
					selection = c;
					break;
			}
			else if(intCounter == randomInt){
				selection = c;
				break;
			}
			intCounter++;
		}
		return selection;
	}
	
	public void makeAccusation(){
		
	}
	
	public void makeSuggestion(Board board, BoardCell location){
		
	}

}
