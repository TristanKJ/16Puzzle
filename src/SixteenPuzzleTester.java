import static org.junit.Assert.*;
import org.junit.Test;

/**
 *	All tests were written by me and have been shared freely.
 *
 * @author Tristan Knope-Jenkins
 *
 */
public class SixteenPuzzleTester {
	
	
	@Test
	public void broegTest1()
	{
		Main main = new Main("4, 1, 2, 3, 5, 6, 10, 7, 8, 0, 9, 11, 12, 13, 14, 15");
		String result = main.solve();
		assertEquals("RULLU", result.trim());
	}
	
	@Test
	public void broegTest2()
	{
		Main main = new Main("1, 2, 6, 3, 4, 0, 10, 7, 8, 5, 9, 11, 12, 13, 14, 15");
		String result = main.solve();
		assertEquals("DRUULL", result.trim());
	}

	@Test
	public void broegTest3()
	{
		Main main = new Main("4, 1, 2, 3, 8, 0, 5, 7, 9, 10, 6, 11, 12, 13, 14, 15");
		String result = main.solve();
		assertEquals("RDLLUU", result.trim());
	}
	
	@Test
	public void broegTest4()
	{
		Main main = new Main("1, 2, 6, 3, 4, 5, 0, 7, 12, 8, 10, 11, 13, 14, 9, 15");
		String result = main.solve();
		assertEquals("DDLLURRUULL", result.trim());
	}
	
	@Test
	public void moveTestTests()
	{
		State temp = new State("1, 2, 3, 4, 0, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15");
		assertTrue(temp.testDown());
		temp.moveDown();
		assertFalse(temp.testUp());
		temp.moveRight();
		assertFalse(temp.testLeft());
		temp.moveLeft();
		assertFalse(temp.testRight());
		temp.moveUp();
		assertFalse(temp.testDown());
	}
	
	@Test
	public void queueTests()
	{
		DLLQueue<String> q = new DLLQueue<String>();
		
		assertTrue(q.add("First"));
		q.add("Secound");
		q.add("Third");
		assertEquals(q.size(), 3);
		assertEquals("First", q.remove());
		assertEquals(q.size(), 2);
		q.add("4th");
		assertEquals("Secound", q.remove());
		assertEquals(q.size(), 2);
		assertEquals("Third", q.remove());
		assertEquals("4th", q.remove());
		assertTrue(q.isEmpty());
	}

	@Test
	public void moveDownTest1() {
		State temp = new State("1, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15");
		
		assertTrue(temp.testDown());
		assertEquals(temp.getEmptySpace(), 1);
		temp.moveDown();
		assertTrue(temp.getMovesMade().trim().equals("D"));
		assertEquals(temp.getEmptySpace(),5);
		assertTrue(temp.toString().equals("1,5,2,3,4,0,6,7,8,9,10,11,12,13,14,15,"));
	}
	
	@Test
	public void moveDownTest2()
	{
		State temp = new State("1, 2, 3, 4,"
							+  "5, 6, 7, 8,"
							 + "9, 10, 11, 12,"
							 + "0, 13, 14, 15");
		assertFalse(temp.testDown());
	}
	
	@Test
	public void moveUpTest1()
	{
		State temp = new State("1, 5, 2, 3, 4, 0, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15");
		
		assertTrue(temp.testUp());
		assertEquals(temp.getEmptySpace(), 5);
		temp.moveUp();
		assertTrue(temp.getMovesMade().trim().equals("U"));
		assertEquals(temp.getEmptySpace(),1);
		assertTrue(temp.toString().equals("1,0,2,3,4,5,6,7,8,9,10,11,12,13,14,15,"));
	}
	
	@Test
	public void moveUpTest2()
	{
		State temp = new State("1, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15");
		
		assertFalse(temp.testUp());
		temp = new State("0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15");
		assertFalse(temp.testUp());
		temp = new State("1, 2, 3, 0, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15");
		assertFalse(temp.testUp());
		temp.moveDown();
		assertFalse(temp.testUp());
	}
	
	@Test
	public void moveRightTest1()
	{
		State temp = new State("1, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15");
		assertTrue(temp.testRight());
		temp.moveRight();
		assertTrue(temp.getMovesMade().trim().equals("R"));
		assertEquals(temp.getEmptySpace(),2);
		assertTrue(temp.toString().equals("1,2,0,3,4,5,6,7,8,9,10,11,12,13,14,15,"));
	}
	
	@Test
	public void moveRightTest2()
	{
		State temp = new State("1, 2, 3, 0, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15");
		assertFalse(temp.testRight());
		temp = new State("1, 2, 3, 4, 5, 6, 7, 0, 8, 9, 10, 11, 12, 13, 14, 15");
		assertFalse(temp.testRight());
		temp = new State("1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 0, 12, 13, 14, 15");
		assertFalse(temp.testRight());
		temp = new State("1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0");
		assertFalse(temp.testRight());
		
		temp.moveLeft();
		assertFalse(temp.testRight());
	}
	
	@Test
	public void moveLeftTest1()
	{
		State temp = new State("1, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15");
		assertTrue(temp.testLeft());
		temp.moveLeft();
		assertTrue(temp.getMovesMade().trim().equals("L"));
		assertEquals(temp.getEmptySpace(),0);
		assertTrue(temp.toString().equals("0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,"));
	}
	
	@Test
	public void isGoalStateTest()
	{
		State temp = new State("0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,");
		assertTrue(temp.isGoalState());
		
		temp = new State("1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0");
		assertFalse(temp.isGoalState());
	}
	
	@Test
	public void findingGoalStateTest()
	{
		State temp = new State("1, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15");
		assertFalse(temp.isGoalState());
		assertTrue(temp.testLeft());
		temp.moveLeft();
		assertTrue(temp.isGoalState());
	}
	
	@Test
	public void cloneTest()
	{
		State temp = new State("1, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15");
		State temp2 = temp.cloneState();
		
		assertTrue(temp.equals(temp2));
	}

}
