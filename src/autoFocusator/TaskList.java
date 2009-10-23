/**
 * 
 */
package autoFocusator;


import java.util.LinkedList;

//import javax.swing.event.TableModelEvent;
//import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/** This class is aimed to hold the different tasks in the application.
 * 
 * @author woodtluk
 * @warning This class is not synchronised. If multiple threads 
 * access a linked list concurrently, and at least one of the 
 * threads modifies the list structurally, it must be synchronised 
 * externally. 
 * See documentation about LinkedList.
 * 
 * 
 */
@XmlRootElement
public class TaskList extends AbstractTableModel {
	
	/// LinkedList which holds the tasks.
	@XmlElement
	private LinkedList<Task> taskList;
	
	
	
	
	public TaskList() {
		this.taskList = new LinkedList<Task>();
	}

	/**
	 * @param index The place where the element is added.
	 * @param element The element that is added.
	 * @see java.util.LinkedList#add(int, java.lang.Object)
	 */
	public void add(int index, Task element) {
		taskList.add(index, element);
	}

	/** Adds a new task to the end of the list.
	 * @param e The task that has to be added.
	 * @return true if the List has been changed by this action.
	 * @see java.util.LinkedList#add(java.lang.Object)
	 */
	public boolean add(Task e) {
		if(e == null) return false;
		return taskList.add(e);
	}

	/** Returns a task at a given index.
	 * @param index Which task should be returned.
	 * @return The requested task.
	 * @see java.util.LinkedList#get(int)
	 */
	public Task get(int index) {
		return taskList.get(index);
	}

	/** Returns the first task in the list.
	 * @return The first task.
	 * @see java.util.LinkedList#getFirst()
	 */
	public Task getFirst() {
		return taskList.getFirst();
	}

	/** Returns the last task in the list.
	 * @return The last task.
	 * @see java.util.LinkedList#getLast()
	 */
	public Task getLast() {
		return taskList.getLast();
	}

	/** Checks if the list is empty.
	 * @return true if there are no tasks in the list.
	 * @see java.util.AbstractCollection#isEmpty()
	 */
	public boolean isEmpty() {
		return taskList.isEmpty();
	}

	/** Removes a task from the list.
	 * @param index The task that has to be removed.
	 * @return The removed task.
	 * @see java.util.LinkedList#remove(int)
	 */
	public Task remove(int index) {
		if(taskList.size()== 0)
			return null;
		return taskList.remove(index);
	}

	/**
	 * @return
	 * @see java.util.LinkedList#removeLast()
	 */
	public Task removeLast() {
		if(taskList.size()== 0)
			return null;
		return taskList.removeLast();
	}

	
	
	/**
	 * 
	 * @see java.util.LinkedList#clear()
	 */
	public void clear() {
		taskList.clear();
	}

	/**
	 * @param index
	 * @param element
	 * @return
	 * @see java.util.LinkedList#set(int, java.lang.Object)
	 */
	public Task set(int index, Task element) {
		return taskList.set(index, element);
	}


	@Override
	public int getColumnCount() {
		return 1;
	}

	@Override
	public int getRowCount() {
		return taskList.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)  {
		return taskList.get(rowIndex).getName();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) {
		
		return "Tasks:";//super.getColumnName(column);
	}

		
	
	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	


	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#fireTableCellUpdated(int, int)
	 */
	@Override
	public void fireTableCellUpdated(int row, int column) {
		
		super.fireTableCellUpdated(row, column);
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#fireTableDataChanged()
	 */
	@Override
	public void fireTableDataChanged() {
		super.fireTableDataChanged();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.AbstractTableModel#setValueAt(java.lang.Object, int, int)
	 */
	@Override
	public void setValueAt(Object obj, int row, int column) {
		String strng = (String)obj;
		taskList.get(row).setName(strng);
		//super.setValueAt(arg0, arg1, arg2);
	}




	private static final long serialVersionUID = 3078318673428767259L;

}
