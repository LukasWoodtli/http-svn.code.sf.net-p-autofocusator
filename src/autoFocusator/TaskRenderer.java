/**
 * 
 */
package autoFocusator;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import javax.swing.table.TableCellRenderer;

/** Shows the task in the table and formates it dependent on its state.
 * @author woodtluk
 *
 */
public class TaskRenderer extends JEditorPane implements TableCellRenderer {

	/// Used for internal operations.
	Task task;
	
	private static final long serialVersionUID = 1L;

	/** Creates a new TaskRenderer.
	 * 
	 */
	public TaskRenderer() {
		task = null;
		setContentType("text/html");
		setOpaque(true); 
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		State state = ((TaskList)table.getModel()).get(row).getState();
		String strng = (String)value;
		setBackground(Color.white);
				
		switch(state){
			case crossed: strng = "<strike>" + strng + "</strike>";
				break;
			case dismissed: setBackground(Color.pink);
				break;
			default: setBackground(Color.white);
		}
		setText(strng);
		
		if(isSelected)
			setBackground(Color.lightGray);
	
		return this;
		
	}
	
}
