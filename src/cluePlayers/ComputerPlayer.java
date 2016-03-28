package cluePlayers;

import java.awt.Color;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

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
				
		for (BoardCell c : currentTargets){
			//Should probably put in own function
			System.out.println(randomInt);
			System.out.println("c is: " + c);
			boolean row = false;
			boolean column = false;
			if(c.getRow() == lastVisited.getRow())
				row = true;
			if(c.getColumn() == lastVisited.getColumn())
				column = true;
			if(row && column){
				continue;
			}

			if((c.isDoorway() || c.isRoom())){
					selection = c;
					lastVisited = c;
					//System.out.println("in");
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
	
	public Vector<Card> makeSuggestion(Board board, Card room){
		
		Vector<Card> suggestion = new Vector<Card>();
		LinkedList<Card> weaponDeck = new LinkedList<Card>();
		weaponDeck = board.getWeaponCards();
		Random randomGenerator = new Random();
		
		int randomWeaponInt = randomGenerator.nextInt(weaponDeck.size());
		LinkedList<Card> personDeck = new LinkedList<Card>();
		personDeck = board.getSuspectCards();
		int randomPersonInt = randomGenerator.nextInt(personDeck.size());

		for(Card c: weaponDeck){
			if(board.getSeenCards().contains(c))
				weaponDeck.remove(c);
		}
		
		for(Card c: personDeck){
			if(board.getSeenCards().contains(c))
				personDeck.remove(c);
		}
		
		suggestion.add(room);
		suggestion.add(weaponDeck.get(randomWeaponInt));
		suggestion.add(personDeck.get(randomPersonInt));

		return suggestion;
		
	}

}
