package CluePlayers;

import java.awt.Color;
import java.util.HashSet;
import java.util.Random;
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
		int targetsSize = targets.size();
		int intCounter = 0;
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(targetsSize);
		BoardCell selection = new BoardCell();
		Set<BoardCell> currentTargets = new HashSet<BoardCell>();
		currentTargets = targets;
		
		if(lastVisited.isRoom() || lastVisited.isDoorway()){
			currentTargets.remove(lastVisited);
		}
		
		for (BoardCell c : currentTargets){
			//System.out.println("c is: " + c);
			if(c.isDoorway()){
					selection = c;
					lastVisited = c;
					//System.out.println(lastVisited);
					break;
			}
			else if(intCounter == randomInt){
				selection = c;
				lastVisited = c;
				//System.out.println(lastVisited);
				break;
			}
			intCounter++;
		}
		//System.out.println("selection is: " + selection);
		return selection;
	}
	
	public void makeAccusation(){
		
	}
	
	public void makeSuggestion(Board board, BoardCell location){
		
	}

}
