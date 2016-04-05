package clueGame;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import cluePlayers.Card;
import cluePlayers.Player;


public class DetectiveNotes extends JDialog{
	private JComboBox<String> people;
	private JComboBox<String> rooms; 
	private JComboBox<String> weapons; 

public DetectiveNotes(){
	setTitle("Detective Notes");
	setSize(700, 400);



	setLayout(new GridLayout(3,2));
	this.createPersonButtons();

	add(this.createPersonButtons());
	people = this.createPlayerCombo();
	//add(guessPerson);
	add(people);
	
	add(this.createRoomButtons());
	rooms = this.createRoomCombo();
	//add(guessRoom);
	add(rooms);
	
	add(this.createWeaponButtons());
	weapons = this.createWeaponCombo();
	//add(guessWeapon);
	add(weapons);
	



}
public JComboBox<String> createPlayerCombo() {
	Board board = new Board();
	board.initialize();
	JComboBox<String> combo = new JComboBox<String>();
	LinkedList<Player> thePlayers = new LinkedList<Player>();

	thePlayers = board.getAllPlayers();
	combo.setBorder(new TitledBorder (new EtchedBorder(), "Person Guess"));
	//Add all player's names to the combo box
	for(int i = 0; i < thePlayers.size(); i++){
	combo.addItem(thePlayers.get(i).getName());
	}
	
	return combo;
}

public JComboBox<String> createRoomCombo() {
	Board board = new Board();
	board.initialize();
	
	LinkedList<Card> theRooms = new LinkedList<Card>();
	theRooms = board.getRoomCards();

	JComboBox<String> combo = new JComboBox<String>();
	combo.setBorder(new TitledBorder (new EtchedBorder(), "Room Guess"));

	for(int i = 0; i < theRooms.size(); i++){
		combo.addItem(theRooms.get(i).getCardName());
		}
	
	return combo;
}

public JComboBox<String> createWeaponCombo() {
	Board board = new Board();
	board.initialize();
	
	LinkedList<Card> theWeapons = new LinkedList<Card>();
	theWeapons = board.getWeaponCards();

	JComboBox<String> combo = new JComboBox<String>();
	combo.setBorder(new TitledBorder (new EtchedBorder(), "Weapon Guess"));

	for(int i = 0; i < theWeapons.size(); i++){
		combo.addItem(theWeapons.get(i).getCardName());
		}
	
	return combo;
}

public JPanel createPersonButtons(){
	JPanel personButtonPanel = new JPanel();
	Board board = new Board();
	board.initialize();
	
	JRadioButton personButton;
	
	LinkedList<Player> thePlayers = new LinkedList<Player>();
	thePlayers = board.getAllPlayers();
	personButtonPanel.setBorder(new TitledBorder (new EtchedBorder(), "Person"));
	for(int i = 0; i < thePlayers.size(); i++){
		personButton = new JRadioButton(thePlayers.get(i).getName());
		personButtonPanel.add(personButton);
	}
	return personButtonPanel;
}

public JPanel createWeaponButtons(){
	JPanel weaponButtonPanel = new JPanel();
	Board board = new Board();
	board.initialize();
	
	JRadioButton WeaponButton;
	LinkedList<Card> theWeapons = new LinkedList<Card>();
	theWeapons = board.getWeaponCards();
	weaponButtonPanel.setBorder(new TitledBorder (new EtchedBorder(), "Weapons"));
	for(int i = 0; i < theWeapons.size(); i++){
		WeaponButton = new JRadioButton(theWeapons.get(i).getCardName());
		weaponButtonPanel.add(WeaponButton);
	}
	return weaponButtonPanel;
}

public JPanel createRoomButtons(){
	JPanel roomButtonPanel = new JPanel();
	Board board = new Board();
	board.initialize();
	
	JRadioButton RoomButton;
	
	LinkedList<Card> theRooms = new LinkedList<Card>();
	theRooms = board.getRoomCards();
	roomButtonPanel.setBorder(new TitledBorder (new EtchedBorder(), "Rooms"));

	for(int i = 0; i < theRooms.size(); i++){
		RoomButton = new JRadioButton(theRooms.get(i).getCardName());
		roomButtonPanel.add(RoomButton);
	}
	return roomButtonPanel;
}

}

