package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.lang.*;

import CluePlayers.Card;
import CluePlayers.Card.CardType;
import CluePlayers.Player;
import CluePlayers.ComputerPlayer;
import CluePlayers.HumanPlayer;
import CluePlayers.Solution;
import Experiment.BoardCellEx;

public class Board {
	private int numRows;
	private int numColumns;
	private int numDoors = 0;
	public final static int BOARD_SIZE = 50;
	private BoardCell[][] board;
	private static Map<Character,String> rooms;
	private Map<BoardCell, LinkedList<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;
	private String boardConfigFile;
	private String roomConfigFile;
	private String weaponsConfigFile;
	private String playersConfigFile;
	private Set<BoardCell> visited;
	private Solution theAnswer;
	private static LinkedList<Card> deck;
	private LinkedList<Card> suspectCards;
	private LinkedList<Card> weaponCards;
	private LinkedList<Card> roomCards;
	private LinkedList<Player> allPlayers;
	private LinkedList<ComputerPlayer> computerPlayers;
	private HumanPlayer humanPlayer;
	


	public Board()  {
		super();
		board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
		boardConfigFile = "ClueLayout.csv";
		roomConfigFile = "ClueLegend.txt";
		weaponsConfigFile = "weapons.txt";
		playersConfigFile = "people.csv";
		rooms = new HashMap<Character,String>();
		adjMatrix = new HashMap<BoardCell, LinkedList<BoardCell>>();
		deck = new LinkedList<Card>();
	}
	///*
	public Board(String boardConfigFile, String roomConfigFile) {
		super();
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
		weaponsConfigFile = "weapons.txt";
		playersConfigFile = "people.csv";
		board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
		this.boardConfigFile = boardConfigFile;	
		rooms = new HashMap<Character,String>();
		adjMatrix = new HashMap<BoardCell, LinkedList<BoardCell>>();
		deck = new LinkedList<Card>();
	}
//*/
	public Board(String boardConfigFile, String roomConfigFile, String playerConfigFile, String weaponsConfigFile) {
		super();
		board = new BoardCell[BOARD_SIZE][BOARD_SIZE];
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
		this.playersConfigFile = playerConfigFile;
		this.weaponsConfigFile = weaponsConfigFile;
		rooms = new HashMap<Character,String>();
		adjMatrix = new HashMap<BoardCell, LinkedList<BoardCell>>();
		deck = new LinkedList<Card>();
	}

	public void initialize() {
		try{
			loadRoomConfig();
			loadBoardConfig();
			loadConfigFiles();
			calcAdjacencies();
			createDeck();
			dealCards();
		}
		catch (BadConfigFormatException e)
		{
			try {
				PrintWriter fout = new PrintWriter("LogFileInitialize.txt");
				fout.println(e);
				fout.close();
			} catch (FileNotFoundException e1) {
				System.out.println("Cannot create file for writing exception");
			}
		}
		catch (FileNotFoundException e)
		{
			e.getMessage();
		}
	}

	public void loadRoomConfig()  throws FileNotFoundException, BadConfigFormatException{
		FileReader reader = null;
		roomCards = new LinkedList<Card>();

		try{
			reader = new FileReader(roomConfigFile);
			Scanner in = new Scanner(reader);
			String dummy;
			String[] ar;

			while (in.hasNext())
			{
				dummy = in.nextLine();
				ar = dummy.split(", ");
				if (!(ar[2].equals("Card") || ar[2].equals("Other")))
				{
					throw new BadConfigFormatException("Bad legend file.");
				}
				rooms.put(ar[0].charAt(0), ar[1]);
				Card room = new Card(ar[1], CardType.ROOM);
				roomCards.add(room);
			}}
		catch (FileNotFoundException e)
		{
			throw e;
		} 
		catch (BadConfigFormatException e)
		{
			try {
				PrintWriter fout = new PrintWriter("LogFileLoadRoom.txt");
				fout.println(e);
				fout.close();
			} catch (FileNotFoundException e1) {
				System.out.println("Cannot create file for writing exception");
			}
			throw e;
		}
	}

	public void loadBoardConfig() throws BadConfigFormatException, FileNotFoundException{
		FileReader reader = null;
		try {
			reader = new FileReader(boardConfigFile);
			Scanner in = new Scanner(reader);
			String dummy;
			String[] ar;
			numRows = 0;
			Set<Character> checker = rooms.keySet();
			try{
				dummy = in.nextLine();
				ar = dummy.split(",");
				numColumns = ar.length;
				for (int i = 0; i < numColumns; i++)
				{
					if(!checker.contains(ar[i].charAt(0)))
					{	
						throw new BadConfigFormatException("Bad room format");
					}
					if (ar[i].length() == 1)
					{
						board[numRows][i] = new BoardCell(0,i,ar[i].charAt(0),DoorDirection.NONE);
					}
					else
					{
						board[numRows][i] = new BoardCell(0,i,ar[i].charAt(0),ar[i].charAt(1));
					}
				}
			}
			catch (Exception e)
			{	
				throw e;}
			while (in.hasNext())
			{
				numRows++;
				dummy = in.nextLine();
				ar = dummy.split(",");
				if (ar.length != numColumns)
				{
					throw new BadConfigFormatException("Bad Columns file");
				}
				for (int i = 0; i < numColumns; i++)
				{
					if(!checker.contains(ar[i].charAt(0)))
					{
						throw new BadConfigFormatException("Bad room format");
					}
					if (ar[i].length() == 1)
					{
						board[numRows][i] = new BoardCell(numRows,i,ar[i].charAt(0),DoorDirection.NONE);
					}
					else
					{
						board[numRows][i] = new BoardCell(numRows,i,ar[i].charAt(0),ar[i].charAt(1));
					}
				}	
			}
			numRows++;

		}
		catch (FileNotFoundException e) {
			throw e;}
		catch (BadConfigFormatException e){
			try {
				PrintWriter fout = new PrintWriter("LogFileLoadBoard.txt");
				fout.println(e);
				fout.close();
			} catch (FileNotFoundException e1) {
				System.out.println("Cannot create file for writing exception");
			}
			throw e;}
	}

	public void loadConfigFiles() throws FileNotFoundException {
		String dummy;
		String ar[];
		allPlayers = new LinkedList<Player>();
		computerPlayers = new LinkedList<ComputerPlayer>();
		humanPlayer = new HumanPlayer();
		suspectCards = new LinkedList<Card>();
		weaponCards = new LinkedList<Card>();
		
		FileReader readerPlayer = null;
		readerPlayer = new FileReader(playersConfigFile);
		Scanner inPlayer = new Scanner(readerPlayer);
		FileReader readerWeapon = null;
		readerWeapon = new FileReader(weaponsConfigFile);
		Scanner inWeapon = new Scanner(readerWeapon);
		try{
			while(inPlayer.hasNext()){
				dummy = inPlayer.nextLine();
				ar = dummy.split(",");
				Player thePlayer = new Player();
				thePlayer.setName(ar[0]);
				thePlayer.setColor(ar[1]);			
				thePlayer.setColumn(Integer.parseInt(ar[3]));
				thePlayer.setRow(Integer.parseInt(ar[2]));
				allPlayers.add(thePlayer);
				Card suspect = new Card(ar[0], CardType.PERSON);
				suspectCards.add(suspect);
				
			}
		}catch (NumberFormatException e){
			e.getMessage();
		}
		for( Player c: allPlayers ){
			if(c.getName().equals("Miss Scarlett")){
				humanPlayer = new HumanPlayer(c.getColumn(), c.getRow(), c.getName(), c.getColor());
				humanPlayer.setName(c.getName());
				
			}
			else{
				ComputerPlayer cp = new ComputerPlayer(c.getColumn(), c.getRow(), c.getName(), c.getColor());
				computerPlayers.add(cp);
			}
		}

		while(inWeapon.hasNext()){
			String cardName;
			cardName = inWeapon.nextLine();
			Card weapon = new Card(cardName, CardType.WEAPON);
			weaponCards.add(weapon);
		}

	}

	public void calcTargets(int row, int col , int pathLength) {
		visited = new HashSet<BoardCell>();
		targets = new HashSet<BoardCell>();
		visited.add(board[row][col]);
		findAllTargets(board[row][col], pathLength);
	}

	public void calcAdjacencies() {
		Set<Character> checker = rooms.keySet();
		checker.remove('W');
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numColumns; j++) {
				LinkedList<BoardCell> holder = new LinkedList<BoardCell>();
				if (checker.contains(board[i][j].getInitial()))
				{
					if(board[i][j].getDoorDirection() != DoorDirection.NONE)
					{
						switch (board[i][j].getDoorDirection()){
						case UP: holder.add(board[i-1][j]);
						break;
						case DOWN: holder.add(board[i+1][j]);
						break;
						case RIGHT: holder.add(board[i][j+1]);
						break;
						case LEFT: holder.add(board[i][j-1]);
						break;
						}}
					adjMatrix.put(board[i][j], holder);
					continue;
				}
				if(i != 0 && !(checker.contains(board[i-1][j].getInitial()) && 
						board[i-1][j].getDoorDirection() != DoorDirection.DOWN))
					holder.add(board[i-1][j]);
				if(j != 0 && !(checker.contains(board[i][j-1].getInitial())
						&& board[i][j-1].getDoorDirection() != DoorDirection.RIGHT))
					holder.add(board[i][j-1]);
				if(i != numRows - 1 && !(checker.contains(board[i+1][j].getInitial()) 
						&& board[i+1][j].getDoorDirection() != DoorDirection.UP))
					holder.add(board[i+1][j]);
				if(j != numColumns - 1 && !(checker.contains(board[i][j+1].getInitial())
						&& board[i][j+1].getDoorDirection() != DoorDirection.LEFT))
					holder.add(board[i][j+1]);
				adjMatrix.put(board[i][j], holder);
			}
		}
	}

	private void findAllTargets(BoardCell thisCell, int numStep)
	{
		LinkedList<BoardCell> adjacentCells = adjMatrix.get(thisCell);
		for(BoardCell cell: adjacentCells)
		{
			if(visited.contains(cell)) continue;
			visited.add(cell);
			if(numStep == 1 || cell.getDoorDirection() != DoorDirection.NONE)
				targets.add(cell);
			else
			{
				findAllTargets(cell, numStep - 1);
			}
			visited.remove(cell);
		}
	}

	public void createDeck(){
		deck = new LinkedList<Card>();
		deck.addAll(suspectCards);
		deck.addAll(weaponCards);
		deck.addAll(roomCards);
	}
	
	public void dealCards(){
		for(Player p: allPlayers){
			
		}
		
	}
	
	public int countPersonCards(LinkedList<Card> deck){
		int counter = 0;
		for(Card c: deck){
			if (c.getCardType() == Card.CardType.PERSON){
				counter++;
			}
		}
		return counter; 
	}
	
	public int countWeaponCards(LinkedList<Card> deck){
		int counter = 0;
		for(Card c: deck){
			if (c.getCardType() == Card.CardType.WEAPON){
				counter++;
			}
		}
		return counter; 
	}
	
	public int countRoomCards(LinkedList<Card> deck){
		int counter = 0;
		for(Card c: deck){
			if (c.getCardType() == Card.CardType.ROOM){
				counter++;
			}
		}
		return counter; 
	}
	
	public boolean containsCard(LinkedList<Card> deck, String name, CardType type){
		for(Card c: deck){
			if (c.getCardType() == type && c.getCardName() == name){
				return true;
			}
		}
		return false;
		
	}
	
	public void selectAnswer(){
		theAnswer = new Solution();
	}

	public Card handleSuggestion(Solution suggestion, String accusingPlayer, BoardCell clicked){
		Card card = new Card();
		return card;
	}

	public boolean checkAccusation(Solution accusation){
		return false;
	}

	public BoardCell getCellAt(int row, int column) {
		return board[row][column];
	}

	public Set<BoardCell> getTargets() {
		return targets;
	}

	public LinkedList<BoardCell> getAdjList(int row, int col) {
		return adjMatrix.get(board[row][col]);
	}

	public int getNumDoors() {
		return numDoors;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public static Map<Character, String> getRooms() {
		return rooms;
	}

	public static LinkedList<Card> getDeck(){
		return deck;
	}

	public HumanPlayer getHumanPlayer() {
		return humanPlayer;
	}

	public void setHumanPlayer(HumanPlayer humanPlayer) {
		this.humanPlayer = humanPlayer;
	}

	public Solution getTheAnswer() {
		return theAnswer;
	}

	public void setTheAnswer(Solution theAnswer) {
		this.theAnswer = theAnswer;
	}

	public LinkedList<Card> getSuspectCards() {
		return suspectCards;
	}

	public void setSuspectCards(LinkedList<Card> suspectCards) {
		this.suspectCards = suspectCards;
	}

	public LinkedList<Card> getWeaponCards() {
		return weaponCards;
	}

	public void setWeaponCards(LinkedList<Card> weaponCards) {
		this.weaponCards = weaponCards;
	}

	public LinkedList<Card> getRoomCards() {
		return roomCards;
	}

	public void setRoomCards(LinkedList<Card> roomCards) {
		this.roomCards = roomCards;
	}

	public LinkedList<ComputerPlayer> getComputerPlayers() {
		return computerPlayers;
	}

	public void setComputerPlayers(LinkedList<ComputerPlayer> computerPlayers) {
		this.computerPlayers = computerPlayers;
	}



}

