package Experiment;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class IntBoardTest {
	IntBoard board;
	@Before
	public void setUp()
	{
		board = new IntBoard(4,4);
		board.calcAdjacencies();
	}
	@Test
	public void testAdjacency0_0()
	{
		BoardCellEx cell = board.getCell(0,0);
		LinkedList<BoardCellEx> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertEquals(2, testList.size());
	}
	@Test
	public void testAdjacency3_3()
	{
		BoardCellEx cell = board.getCell(3,3);
		LinkedList<BoardCellEx> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertEquals(2, testList.size());
	}
	@Test
	public void testAdjacency1_3()
	{
		BoardCellEx cell = board.getCell(1,3);
		LinkedList<BoardCellEx> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 3)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertEquals(3, testList.size());
	}
	@Test
	public void testAdjacency3_0()
	{
		BoardCellEx cell = board.getCell(3,0);
		LinkedList<BoardCellEx> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 0)));
		assertTrue(testList.contains(board.getCell(3, 1)));
		assertEquals(2, testList.size());
	}
	@Test
	public void testAdjacency1_1()
	{
		BoardCellEx cell = board.getCell(1,1);
		LinkedList<BoardCellEx> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(0, 1)));
		assertTrue(testList.contains(board.getCell(1, 0)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertEquals(4, testList.size());
	}
	@Test
	public void testAdjacency2_2()
	{
		BoardCellEx cell = board.getCell(2,2);
		LinkedList<BoardCellEx> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(2, 1)));
		assertTrue(testList.contains(board.getCell(1, 2)));
		assertTrue(testList.contains(board.getCell(2, 3)));
		assertTrue(testList.contains(board.getCell(3, 2)));
		assertEquals(4, testList.size());
	}
	@Test
	public void testAdjacency3_2()
	{
		BoardCellEx cell = board.getCell(3,2);
		LinkedList<BoardCellEx> testList = board.getAdjList(cell);
		assertTrue(testList.contains(board.getCell(3, 1)));
		assertTrue(testList.contains(board.getCell(3, 3)));
		assertTrue(testList.contains(board.getCell(2, 2)));
		assertEquals(3, testList.size());
	}
	@Test
	public void testTargets0_0_3()
	{
		BoardCellEx cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set<BoardCellEx> targets = board.getTargets();
		assertEquals(6, targets.size());
		assertTrue(targets.contains(board.getCell(3, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(0, 1)));
		assertTrue(targets.contains(board.getCell(1, 2)));
		assertTrue(targets.contains(board.getCell(0, 3)));
		assertTrue(targets.contains(board.getCell(1, 0)));
	}
	@Test
	public void testTargets2_0_1()
	{
		BoardCellEx cell = board.getCell(2, 0);
		board.calcTargets(cell, 1);
		Set<BoardCellEx> targets = board.getTargets();
		assertEquals(3, targets.size());
		assertTrue(targets.contains(board.getCell(1, 0)));
		assertTrue(targets.contains(board.getCell(2, 1)));
		assertTrue(targets.contains(board.getCell(3, 0)));
	}
	@Test
	public void testTargets3_1_2()
	{
		BoardCellEx cell = board.getCell(3, 1);
		board.calcTargets(cell, 2);
		Set<BoardCellEx> targets = board.getTargets();
		assertEquals(4, targets.size());
		assertTrue(targets.contains(board.getCell(1, 1)));
		assertTrue(targets.contains(board.getCell(2, 0)));
		assertTrue(targets.contains(board.getCell(2, 2)));
		assertTrue(targets.contains(board.getCell(3, 3)));
	}
	

}
