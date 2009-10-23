/** @file
 * This source file defines the table widget in witch
 * the task are shown.
 * 
 *  @author woodtluk
 */
package autoFocusator;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/** This class shows a table with the tasks in it.
 *  It is the main class with the GUI and event handlers.
 *  It needs some refactoring to be donne. Basically it's
 *  only a playground right now.
 * 
 * @todo Refactor the GUI creation methods.
 * @todo The event handlers could be put in a seperate class.
 * @todo Comments/documentation has to be written.
 * @todo Tests (JUnit) have to be completed. 
 */


public class AutoFocusator extends JPanel
					implements ActionListener, MouseListener, TableModelListener{


	private static final long serialVersionUID = 1921717180183856852L;

	TaskList taskList;
	JTable table;
	JPopupMenu contextMenu;
	JToolBar toolBar;
	JFileChooser fileChooser;
	
	public AutoFocusator(){
		super(new BorderLayout());
		

		taskList = new TaskList();
		Task task0 = new Task("Use right click to change the states of the tasks.");
		taskList.add(task0);
		Task task1 = new Task("Just play araound with this small app.");
		taskList.add(task1);
		Task task2 = new Task("Check http://sourceforge.net/projects/autofocusator/");
		taskList.add(task2);
		
		//task1.setState(State.crossed);
		//task0.setState(State.dismissed);
		
		table = new JTable(taskList);
		table.setPreferredScrollableViewportSize(new Dimension(500, 700));
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setPreferredWidth(300);		
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);		
		
		table.getColumnModel().getColumn(0).setCellRenderer(new TaskRenderer());
		//TableCellRenderer renderer = table.getColumnModel().getColumn(0).getCellRenderer();
		//double height = ((TaskRenderer)renderer).getRendererHeight();
		
		// @todo: The height should be set dependent on the content
		table.setRowHeight(28);
		
		table.getModel().addTableModelListener(this);
		
		
		toolBar = new JToolBar("Autofocusator - Toolbar");
		
		JButton buttonAddTask = new JButton("Add a Task", new ImageIcon("res/list-add.png"));
		buttonAddTask.setActionCommand("addTask");
		buttonAddTask.addActionListener(this);
		toolBar.add(buttonAddTask);
		
		JButton buttonDeleteTask = new JButton("Delete a Task", new ImageIcon("res/list-remove.png"));
		buttonDeleteTask.setActionCommand("deleteTask");
		buttonDeleteTask.addActionListener(this);
		toolBar.add(buttonDeleteTask);
		
		JButton buttonSave = new JButton("Save", new ImageIcon("res/document-save-as.png"));
		buttonSave.setActionCommand("save");
		buttonSave.addActionListener(this);
		toolBar.add(buttonSave);
		
		JButton buttonOpen = new JButton("open", new ImageIcon("res/document-open.png"));
		buttonOpen.setActionCommand("open");
		buttonOpen.addActionListener(this);
		toolBar.add(buttonOpen);
		
		add(toolBar, BorderLayout.NORTH);
 		
		contextMenu = new JPopupMenu();
		JMenuItem contextMenuItem;
		contextMenuItem = new JMenuItem("delete Task");
		contextMenuItem.addActionListener(this);
		contextMenuItem.setActionCommand("deleteTask");
		contextMenu.add(contextMenuItem);
		
		contextMenuItem = new JMenuItem("add Task");
		contextMenuItem.addActionListener(this);
		contextMenuItem.setActionCommand("addTask");
		contextMenu.add(contextMenuItem);
		
		contextMenu.addSeparator();
		
		contextMenuItem = new JMenuItem("cross");
		contextMenuItem.addActionListener(this);
		contextMenuItem.setActionCommand("cross");
		contextMenu.add(contextMenuItem);
		
		contextMenuItem = new JMenuItem("dismiss");
		contextMenuItem.addActionListener(this);
		contextMenuItem.setActionCommand("dismiss");
		contextMenu.add(contextMenuItem);
		
		contextMenuItem = new JMenuItem("worked on");
		contextMenuItem.addActionListener(this);
		contextMenuItem.setActionCommand("workedOn");
		contextMenu.add(contextMenuItem);
		
		fileChooser = new JFileChooser();
		FileFilter filter = new FileNameExtensionFilter("XML File", "xml");
		fileChooser.addChoosableFileFilter(filter);

		
		table.addMouseListener(this);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);
	}
		
		private static void createAndShowGUI() {
			JFrame frame = new JFrame("Autofocusator");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			AutoFocusator newContentPane = new AutoFocusator();
			newContentPane.setOpaque(true);
			frame.setContentPane(newContentPane);
			
			frame.pack();
			frame.setVisible(true);
		}

	public static void main(String[] arg){
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("addTask")){
			taskList.add(new Task("new Task"));
			/// @todo A new created task should be editable right away.
			table.editCellAt(taskList.getRowCount()-1,0);
				
			
		}
		if(e.getActionCommand().equals("deleteTask") && (table.getSelectedRow() != -1)){
			taskList.remove(table.getSelectedRow());
		}
		if(e.getActionCommand().equals("cross") && (table.getSelectedRow() != -1)){
			taskList.get(table.getSelectedRow()).setState(State.crossed);
		}
		if(e.getActionCommand().equals("dismiss") && (table.getSelectedRow() != -1)){
			taskList.get(table.getSelectedRow()).setState(State.dismissed);
		}
		if(e.getActionCommand().equals("workedOn") && (table.getSelectedRow() != -1)){
			Task task = taskList.get(table.getSelectedRow());
			task.setState(State.crossed);
			taskList.add(new Task(task.getName()));

		}		
			
		
		// File handling
		File file = null;
		Writer writer = null;
		JAXBContext context;
		
		
		if(e.getActionCommand().equals("save")){
			Marshaller m = null;
			int returnVal = fileChooser.showSaveDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				file = fileChooser.getSelectedFile();
				if(!file.getName().endsWith(".xml")){
				/// @TODO Append automaticly .xml
				}
			
				try {
					context = JAXBContext.newInstance(TaskList.class);
					m = context.createMarshaller();
					m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					writer = new FileWriter(file);
					m.marshal(taskList, writer);
	
				} catch (JAXBException exeption) {
					exeption.printStackTrace();
				} catch (IOException exeption) {
					exeption.printStackTrace();
				}
				finally {
					try {writer.close();} catch(Exception exeption) {} 
					
				}
			}
		}		
		if(e.getActionCommand().equals("open")){
					Unmarshaller um = null;
					int returnVal = fileChooser.showOpenDialog(this);
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						file = fileChooser.getSelectedFile();
						//if(!file.getName().endsWith(".xml")){
						/// @TODO Append automaticly .xml
						//}
					
						try {
							context = JAXBContext.newInstance(TaskList.class);
							um = context.createUnmarshaller();							
							taskList = (TaskList)um.unmarshal(file);
							table.setModel(taskList);
							table.getColumnModel().getColumn(0).setCellRenderer(new TaskRenderer());
							
			
						} catch (JAXBException exeption) {
							exeption.printStackTrace();
						
						}
						finally {
							try {writer.close();} catch(Exception exeption) {} 
							
						}
					}
				}
		taskList.fireTableDataChanged();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.isPopupTrigger()) {
			contextMenu.show(e.getComponent(), e.getX(), e.getY());
		}
		
	}

	@Override
	public void tableChanged(TableModelEvent e) {
	
		
	}
	

	
}
