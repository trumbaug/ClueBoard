package CluePlayers;

import java.awt.Color;
import java.lang.reflect.Field;

public class Player {
	int column = 0;
	int row = 0;
	String name = "a";
	Color color;
	
	public Player() {
		super();
	}

	public Player(int column, int row, String name, Color color) {
		super();
		this.column = column;
		this.row = row;
		this.name = name;
		this.color = color;
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
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
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
	
}
