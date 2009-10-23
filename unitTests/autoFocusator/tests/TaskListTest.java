/**
 * 
 */
package autoFocusator.tests;

import static org.junit.Assert.*;
import org.junit.*;
import autoFocusator.*;

/**
 * @author woodtluk
 *
 */
public class TaskListTest {

	
	public TaskListTest() {		
		list = new TaskList();
		setUp();
	}
	
	TaskList list;
	Task task0, task1;
	
	@Before
	public void setUp(){
		list.clear();
		task0 = new Task("Task 0");	
		task1 = new Task("Task 1");
		list.add(task0);
		list.add(task1);
	}
	
	@After
	public void tearDown(){
		list.clear();
	}
	
	/**	
	 * Test method for {@link autoFocusator.TaskList#get(int)}.
	 */
	@Test
	public void testGet() {
		
		list.clear();
		assertTrue(list.add(task0));
		assertTrue(list.add(task1));
		
		assertTrue(list.get(0).equals(task0));
		assertFalse(list.get(0).equals(task1));

		assertTrue(list.get(1).equals(task1));
		assertFalse(list.get(1).equals(task0));
	}

	/**
	 * Test method for {@link autoFocusator.TaskList#getFirst()}.
	 */
	@Test
	public void testGetFirst() {
		assertTrue(list.getFirst().equals(task0));
		assertFalse(list.getFirst().equals(task1));
		
	}

	/**
	 * Test method for {@link autoFocusator.TaskList#getLast()}.
	 */
	@Test
	public void testGetLast() {
		assertTrue(list.getLast().equals(task1));
		assertFalse(list.getLast().equals(task0));
	}

	/**
	 * Test method for {@link autoFocusator.TaskList#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		list.clear();
		assertTrue(list.isEmpty());
		
		list.add(task0);
		assertFalse(list.isEmpty());
	}

	/**
	 * Test method for {@link autoFocusator.TaskList#remove(int)}.
	 */
	@Test
	public void testRemove() {
		assertTrue(list.remove(0).equals(task0));
		assertTrue(list.getFirst().equals(task1));
	}

	/**
	 * Test method for {@link autoFocusator.TaskList#removeLast()}.
	 */
	@Test
	public void testRemoveLast() {
		
		assertTrue(list.removeLast().equals(task1));
		assertEquals(list.getRowCount(), 1);
		assertTrue(list.getLast().equals(task0));
	}

	/**
	 * Test method for {@link autoFocusator.TaskList#set(int, autoFocusator.Task)}.
	 */
	@Test
	public void testSet() {
		Task newTask = new Task("newTask");
		list.set(1, newTask);
		assertTrue(list.get(1).equals(newTask));
		assertFalse(list.get(1).equals(task1));
		
	}

	/**
	 * Test method for {@link autoFocusator.TaskList#toString()}.
	 */

	/**
	 * Test method for {@link autoFocusator.TaskList#getColumnCount()}.
	 */
	@Test
	public void testGetColumnCount() {
		assertEquals(list.getColumnCount(),1);
	}

	/**
	 * Test method for {@link autoFocusator.TaskList#getRowCount()}.
	 */
	@Test
	public void testGetRowCount() {
		assertEquals(list.getRowCount(),2);
		Task task2 = new Task("task2");
		list.add(task2);
		assertEquals(list.getRowCount(),3);
		
	}

	/**
	 * Test method for {@link autoFocusator.TaskList#getValueAt(int, int)}.
	 */
	@Test
	public void testGetValueAt() {
		assertEquals(list.getValueAt(1,0),task1.getName());
	}

}
