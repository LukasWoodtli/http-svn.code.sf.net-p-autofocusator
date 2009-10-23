/**
 * 
 */
package autoFocusator.tests;

import autoFocusator.Task;
import autoFocusator.State;

import org.junit.*;
import static org.junit.Assert.*;


/** Tests for the Task class.
 * Not working yet.
 * @author woodtluk
 *
 */
public class TaskTest {
	
	public TaskTest() {
		string = "new Task";
		task = new Task(string);
	}
	
	String string;
	Task task, otherTask;
	State state;
	
	protected void setUp(){
		
	}
	
	/* assert functions JUnit
	assertEquals(...);
  	assertArrayEquals(...);
  	
  	assertFalse(...);
    assertTrue(...);
    
    assertNull(...);
    assertNotNull(...);
    
    assertNotSame(...);
    assertSame(...);
    
    fail(String str);
    ...
    */
	
	@Test
	public void test_Task(){
		/// Test constructor.
		//assertTrue(task.getName().equals(string));
		assertTrue(task.getState() == State.active);
	

	}


	
	@Test
	public void test_getState(){
		state = State.active;
		
	//	task.setState(State.crossed);
	//	assertTrue(task.getState() == state);
		
		state = State.crossed;
		assertNotNull(state);
//		task.setState(state);
//		assertTrue(task.getState() == state);
		
//		task1.setState(State.dismissed);
//		assertTrue(task1.getState() == state);
	}
	
	@Test
	public void test_equals(){
		task = new Task("a Task");
		assertTrue(task.equals(task));
		
		otherTask = task;
		assertTrue(task.equals(otherTask));
		assertTrue(otherTask.equals(task));
		
		otherTask = new Task("a Task");
		assertTrue(task.equals(otherTask));
		
		otherTask.setState(State.dismissed);
		assertFalse(task.equals(otherTask));
	}
	
	
	@Test
	public void test_isSame(){
		task = new Task("a Task");
		assertTrue(task.isSame(task));
		
		otherTask = task;
		assertTrue(task.isSame(otherTask));
		assertTrue(otherTask.isSame(task));
		
		otherTask = new Task("a Task");
		assertTrue(task.isSame(otherTask));
		
		otherTask.setState(State.dismissed);
		assertTrue(task.isSame(otherTask));
	}
	

	

}
