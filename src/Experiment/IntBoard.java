package Experiment;

import java.util.HashSet;
import java.util.Set;
import java.util.*;
public class IntBoard {
	private Map<BoardCellEx, LinkedList<BoardCellEx>> adjList;
	private Set<BoardCellEx> targets; 
	private BoardCellEx[][] grid;
	private Set<BoardCellEx> visited;
	public IntBoard(int row, int col) {
		super();
		adjList = new HashMap<BoardCellEx, LinkedList<BoardCellEx>>();
		grid = new BoardCellEx[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				grid[i][j] = new BoardCellEx(i,j,"w");
			}
		}
	}
	public BoardCellEx getCell(int row, int col)
	{
		return grid[row][col];
	}
	public void calcAdjacencies() {


		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				LinkedList<BoardCellEx> holder = new LinkedList<BoardCellEx>();
				if(i != 0) holder.add(grid[i-1][j]);
				if(j != 0) holder.add(grid[i][j-1]);
				if(i != 3) holder.add(grid[i+1][j]);
				if(j != 3) holder.add(grid[i][j+1]);
				adjList.put(grid[i][j], holder);
			}
		}
	}
	private void findAllTargets(BoardCellEx thisCell, int numStep)
	{
		LinkedList<BoardCellEx> adjacentCells = adjList.get(thisCell);
		for(BoardCellEx cell: adjacentCells)
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
	public void calcTargets(BoardCellEx startCell, int pathLength) {
		visited = new HashSet<BoardCellEx>();
		targets = new HashSet<BoardCellEx>();
		visited.add(startCell);
		findAllTargets(startCell, pathLength);
	}
	public Set<BoardCellEx> getTargets() {
		return targets;
	}
	public LinkedList<BoardCellEx> getAdjList(BoardCellEx cell) {
		return adjList.get(cell);
	}
}
