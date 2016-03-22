package CluePlayers;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.LinkedList;

public class Player {
	int column;
	int row;
	String playerName;
	Color color;
	LinkedList<Card> myCards = new LinkedList<Card>();//Picked a random data structure
	LinkedList<Card> seenCards; //can change if find more suitable one
	
	public Player() {
		super();
	}

	public Player(int column, int row, String playerName, Color color) {
		super();
		this.column = column;
		this.row = row;
		this.playerName = playerName;
		this.color = color;
	}
	
	public Card disproveSuggestion(Solution suggestion){
		Card card = new Card();
		return card;
	}
	
	public void addCardsToHand(Card theCard){
		//System.out.println(theCard);
		
		myCards.add(theCard);
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public String getName() {
		return playerName;
	}

	public void setName(String playerName) {
		this.playerName = playerName;
	}

	public Color getColor() {
		return color;
	}

	public LinkedList<Card> getMyCards() {
		return myCards;
	}

	public void setColor(String color) {
		this.color = convertColor(color);
	}

	public Color convertColor(String strColor) {
		Color color; 
		try {     
			// We can use reflection to convert the string to a color
			Field field = Class.forName("java.awt.Color").getField(strColor.trim());     
			color = (Color)field.get(null); } 
		catch (Exception e) {  
			color = null; // Not defined } 
		}
		return color;
	}

	public void setMyCards(LinkedList<Card> myCards) {
		this.myCards = myCards;
	}

	@Override
	public String toString() {
		return "Player [column=" + column + ", row=" + row + ", playerName=" + playerName + ", color=" + color
				+ ", myCards=" + myCards + ", seenCards=" + seenCards + "]";
	}
	
}

