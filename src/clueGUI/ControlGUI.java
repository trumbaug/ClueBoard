package clueGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlGUI extends JPanel {
	private JTextField title;
	
	public ControlGUI () {
		JPanel panel = createTurnPanel();
		add(panel, BorderLayout.NORTH);
		panel = createButtonPanel();
		add(panel, BorderLayout.NORTH);
		panel = createDiePanel();
		add(panel, BorderLayout.SOUTH);
		panel = createGuessPanel();
		add(panel, BorderLayout.SOUTH);
		panel = createGuessResultPanel();
		add(panel, BorderLayout.SOUTH);
		
	}

	private JPanel createTurnPanel(){
		JPanel panel = new JPanel();
		JLabel titleLabel = new JLabel("Whose turn?");
		title = new JTextField(15);
		title.setEditable(false);
		panel.add(titleLabel);
		panel.add(title);
		return panel;
	}
	
	private JPanel createButtonPanel(){
		JPanel panel = new JPanel();
		JButton nextPlayer = new JButton("Next player");
		JButton accusation = new JButton("Make an accusation");
		panel.add(nextPlayer);
		panel.add(accusation);
		return panel;
	}
	
	private JPanel createDiePanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		JLabel rollLabel  = new JLabel("Roll");
		title = new JTextField(5);
		title.setEditable(false);
		panel.add(rollLabel);
		panel.add(title);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Die"));
		return panel;
	}
	
	private JPanel createGuessPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		JLabel guessLabel = new JLabel("Guess");
		title = new JTextField(35);
		title.setEditable(false);
		panel.add(guessLabel);
		panel.add(title);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess"));
		return panel;
	}
	
	private JPanel createGuessResultPanel(){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		JLabel guessResultLabel = new JLabel("Response");
		title = new JTextField(15);
		title.setEditable(false);
		panel.add(guessResultLabel);
		panel.add(title);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Guess Result"));
		return panel;
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2, 0));
		frame.setSize(new Dimension(600,  400));
		ControlGUI controlGui = new ControlGUI();
		frame.add(controlGui);
		frame.setVisible(true);
	}

}
