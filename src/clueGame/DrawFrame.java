package clueGame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class DrawFrame extends JFrame {
Board board;
int length;
int width;

public DrawFrame()
{
	setTitle("Board Game");

	board = new Board();
	board.initialize();
	length = board.getNumColumns();
	width = board.getNumRows();
	setSize((length +2)*40 ,(width + 2)*40);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	add(board, BorderLayout.CENTER);
	
	JMenuBar menubar = new JMenuBar();
	setJMenuBar(menubar);
	menubar.add(createMenuItem());
	
}

private JMenu createMenuItem(){
	JMenu menu = new JMenu("File");
	menu.add(createFileNotesItem());
	menu.add(createFileExitItem());
	return menu;
}

private JMenuItem createFileExitItem(){
	JMenuItem item = new JMenuItem("Exit");
	class MenuItemListener implements ActionListener {
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	item.addActionListener(new MenuItemListener());
	return item;
}

private JMenuItem createFileNotesItem(){
	JMenuItem item = new JMenuItem("Detective Notes");
	class MenuItemListener implements ActionListener {
		DetectiveNotes dialog;
		public void actionPerformed(ActionEvent e)
		{
			dialog = new DetectiveNotes();
			dialog.setVisible(true);		}
	}
	item.addActionListener(new MenuItemListener());
	return item;
}


public static void main(String[] args){
	DrawFrame frame = new DrawFrame();
	frame.setVisible(true);
	}

}


