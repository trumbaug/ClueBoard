package Experiment;

import java.util.HashSet;
import java.util.Set;
import java.util.*;
public class IntBoard {
	private Map<BoardCell, LinkedList<BoardCell>> adjList;
	private Set<BoardCell> targets; 
	private BoardCell[][] grid;
	private Set<BoardCell> visited;
	public IntBoard(int row, int col) {
		super();
		adjList = new HashMap<BoardCell, LinkedList<BoardCell>>();
		grid = new BoardCell[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				grid[i][j] = new BoardCell(i,j,"w");
			}
		}
	}
	public BoardCell getCell(int row, int col)
	{
		return grid[row][col];
	}
	public void calcAdjacencies() {


		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				LinkedList<BoardCell> holder = new LinkedList<BoardCell>();
				if(i != 0) holder.add(grid[i-1][j]);
				if(j != 0) holder.add(grid[i][j-1]);
				if(i != 3) holder.add(grid[i+1][j]);
				if(j != 3) holder.add(grid[i][j+1]);
				adjList.put(grid[i][j], holder);
			}
		}
	}
	private void findAllTargets(BoardCell thisCell, int numStep)
	{
		LinkedList<BoardCell> adjacentCells = adjList.get(thisCell);
		for(BoardCell cell: adjacentCells)
		{
			if(visited.contains(cell)) continue;
			visited.add(cell);
			if(numStep == 1) targets.add(cell);
			else
			{
				findAllTargets(cell, numStep - 1);
			}
			visited.remove(cell);
		}
	}
	public void calcTargets(BoardCell startCell, int pathLength) {
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
	}
	public Set<BoardCell> getTargets() {
		return targets;
	}
	public LinkedList<BoardCell> getAdjList(BoardCell cell) {
		return adjList.get(cell);
	}
}
