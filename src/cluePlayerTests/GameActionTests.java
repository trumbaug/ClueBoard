package cluePlayerTests;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import CluePlayers.Card;
import CluePlayers.ComputerPlayer;
import CluePlayers.Player;
import CluePlayers.Solution;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class GameActionTests {
	private static Board board;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		board = new Board();
		board.initialize();
	}

	@Test
	public void accusationTest() {
		//Check that checkAccusation returns true if the correct solution is passed
		Solution test = new Solution();
		test.person = "Miss Scarlett";
		test.room = "Library";
		test.weapon = "candlestick";
		board.setTheAnswer(test);
		assertTrue(board.checkAccusation(test));
		//Check that checkAccusation returns false if the incorrect weapon is passed
		Solution test1 = new Solution();
		test1.person = "Miss Scarlett";
		test1.room = "Library";
		test1.weapon = "lead pipe";
		assertFalse(board.checkAccusation(test1));
		//Check that checkAccusation returns false if the incorrect room is passed
		Solution test2 = new Solution();
		test2.person = "Miss Scarlett";
		test2.room = "Conservatory";
		test2.weapon = "candlestick";
		assertFalse(board.checkAccusation(test2));
		//Check that checkAccusation returns false if the incorrect person is passed
		Solution test3 = new Solution();
		test3.person = "Colonel Mustard";
		test3.room = "Library";
		test3.weapon = "candlestick";
		assertFalse(board.checkAccusation(test3));
		//Check that checkAccusation returns false if the incorrect person, weapon and room are passed
		Solution test4 = new Solution();
		test4.person = "Colonel Mustard";
		test4.room = "Conservatory";
		test4.weapon = "lead pipe";
		assertFalse(board.checkAccusation(test4));
	}
	
	//Testing selecting a location with random picking
	@Test 
	public void randomTargetLocationTest(){
		ComputerPlayer player = new ComputerPlayer();
		// Pick a location with no rooms in target, just three targets
		board.calcTargets(7, 0, 2);
		boolean loc_5_0 = false;
		boolean loc_7_2 = false;
		boolean loc_9_0 = false;
		boolean loc_6_1 = false;
		boolean loc_8_1 = false;
		
		// Run the test 100 times to assure that each location is chose when randomly selecting
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());

			if (selected == board.getCellAt(5, 0))
				loc_5_0 = true;
			else if (selected == board.getCellAt(7, 2))
				loc_7_2 = true;
			else if (selected == board.getCellAt(9, 0))
				loc_9_0 = true;
			else if (selected == board.getCellAt(6, 1))
				loc_6_1 = true;
			else if (selected == board.getCellAt(8, 1))
				loc_8_1 = true;
			else
				fail("Invalid target selected");
		}
		// Ensure each target was selected at least once
		assertTrue(loc_5_0);
		assertTrue(loc_7_2);
		assertTrue(loc_9_0);
		assertTrue(loc_6_1);
		assertTrue(loc_8_1);
	}
	
	//Testing selecting a location if room is NOT last one visited 
	@Test
	public void notVisitedRoomTargetLocationTest(){
		ComputerPlayer player = new ComputerPlayer();
		
		// Pick room as selected location
		board.calcTargets(2, 3, 2);
		boolean loc_2_2 = false;
		
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(board.getTargets());
			System.out.println(selected);
			if (selected == board.getCellAt(2, 2)){
				loc_2_2 = true;
			break;
			}
			
			// Ensure room is selected if not already visited 100 times
		}
		assertTrue(loc_2_2);

	}
	
	//Testing selecting a location if room is last one visited
	@Test 
	public void VisitedRoomTargetLocationTest(){
		ComputerPlayer player = new ComputerPlayer();
		Set<BoardCell> visited = new HashSet<BoardCell>();
		BoardCell testRoom = new BoardCell(2, 2, 'K', DoorDirection.RIGHT);
		// Pick random location if room already visited
		board.calcTargets(2, 3, 2);
	
		boolean loc_2_2 = false;
		boolean loc_4_3 = false;

		// Run the test 100 times
		for (int i=0; i<100; i++) {
			player.setLastVisited(testRoom);

			BoardCell selected = player.pickLocation(board.getTargets());

			if (selected == board.getCellAt(2, 2)){
				loc_2_2 = true;
			}
			if (selected == board.getCellAt(4, 3)){
				loc_4_3 = true;
			}
		}
		// Ensure room is selected if not already visited
		assertFalse(loc_2_2);
		assertTrue(loc_4_3);

	}
	
	//Test all requirements for disprove suggestion 
	@Test
	public void disproveSuggestionTest(){
		Player humanplayer = new Player();
		Player computerplayer1 = new Player();
		Player computerplayer2 = new Player();
		Player computerplayer3 = new Player();

		
		Card mustardCard = new Card();
		Card peacockCard = new Card();
		
		Card knifeCard = new Card();
		Card leadpipeCard = new Card();
		
		Card kitchenCard = new Card();
		Card studioCard = new Card();

		mustardCard = new Card("Colonel Mustard", Card.CardType.PERSON);
		peacockCard = new Card("Mrs. Peacock", Card.CardType.PERSON);

		knifeCard = new Card("knife", Card.CardType.WEAPON);
		leadpipeCard = new Card("lead pipe", Card.CardType.WEAPON);
		
		kitchenCard = new Card("Kitchen", Card.CardType.ROOM);
		studioCard = new Card("Studio", Card.CardType.ROOM);
		
		
		humanplayer.getMyCards().add(knifeCard);
		humanplayer.getMyCards().add(studioCard);
		
		//If no one has a card, return null 
		
		//Test for one player, once correct match
		
		//Should return null
		Solution theSuggestion = new Solution("Mrs. White", "Cellar", "dagger");
		board.getAllPlayers().clear();
		board.getAllPlayers().add(computerplayer1);
		
		computerplayer1.getMyCards().add(mustardCard);
		computerplayer1.getMyCards().add(leadpipeCard);
		
		computerplayer1.getMyCards().add(peacockCard);
		computerplayer1.getMyCards().add(studioCard);
		
		computerplayer1.getMyCards().add(kitchenCard);
		computerplayer1.getMyCards().add(knifeCard);
		
		assertNull(board.disproveSuggestion(theSuggestion, computerplayer1));	
		
		// Check that the correct person is returned
		Solution theSuggestion2 = new Solution("Mrs. Peacock", "Cellar", "dagger");
		board.getAllPlayers().clear();
		board.getAllPlayers().add(computerplayer1);
		
		assertEquals(peacockCard, board.disproveSuggestion(theSuggestion2, computerplayer2));	
		
		// Check that the correct weapon is returned
		Solution theSuggestion3 = new Solution("Mrs. White", "Cellar", "lead pipe");
		board.getAllPlayers().clear();
		board.getAllPlayers().add(computerplayer1);
		
		
		assertEquals(leadpipeCard, board.disproveSuggestion(theSuggestion3, computerplayer2));	
		
		// Check that the correct room is returned
		Solution theSuggestion4 = new Solution("Mrs. White", "Kitchen", "dagger");
		board.getAllPlayers().clear();
		board.getAllPlayers().add(computerplayer1);

		assertEquals(kitchenCard, board.disproveSuggestion(theSuggestion4, computerplayer2));	
	
		//Test for one player, multiple matches
		board.getAllPlayers().clear();
		board.getAllPlayers().add(computerplayer2);
		
		boolean personCheck = false;
		boolean roomCheck = false;
		boolean weaponCheck = false;
		
		computerplayer2.getMyCards().add(peacockCard);
		computerplayer2.getMyCards().add(kitchenCard);
		computerplayer2.getMyCards().add(leadpipeCard);

		
		for (int i=0; i<100; i++) {
			Solution theSuggestionAll = new Solution("Mrs. Peacock", "Kitchen", "lead pipe");
		
			if (peacockCard == board.disproveSuggestion(theSuggestionAll, computerplayer1))
				personCheck = true;
			else if (kitchenCard == board.disproveSuggestion(theSuggestionAll, computerplayer1))
				roomCheck = true;
			else if (leadpipeCard == board.disproveSuggestion(theSuggestionAll, computerplayer1))
				weaponCheck = true;
			
		}
		
		// Ensure each target was selected at least once
		assertTrue(personCheck);
		assertTrue(roomCheck);
		assertTrue(weaponCheck);

		//Test that all players queried
		computerplayer3.getMyCards().add(mustardCard);
		
		//Add all three players to getAllPlayers to test that all players are questioned
		board.getAllPlayers().clear();
		board.getAllPlayers().add(computerplayer2); // Has peacock, kitchen and leadpipe
		board.getAllPlayers().add(computerplayer3); // Has Mustard
		board.getAllPlayers().add(humanplayer); // knife, studio
		
		//Suggestion that no player can disprove
		Solution theSuggestionAll2 = new Solution("Mrs. White", "Bedroom", "dagger");
		assertNull(board.disproveSuggestion(theSuggestionAll2, computerplayer2));	
		
		//Suggestion that only human can disprove
		Solution theSuggestionAll3 = new Solution("Mrs. White", "Bedroom", "knife");
		assertEquals(knifeCard, board.disproveSuggestion(theSuggestionAll3, computerplayer2));
		
		//Make a suggestion that returns null because the person who made the suggestion holds that card
		Solution theSuggestionAll4 = new Solution("Mrs. Peacock", "Bedroom", "dagger");
		assertNull(board.disproveSuggestion(theSuggestionAll4, computerplayer2));
		
		//computerplayer 2 makes a suggestion that requires the last person to disprove which should be the human player
		Solution theSuggestionAll5 = new Solution("Mrs. White", "Bedroom", "knife");
		assertEquals(knifeCard, board.disproveSuggestion(theSuggestionAll5, computerplayer2));
		
	}
	
	//Test that the computer player makes a valid suggestion based on teh seen cards
	@Test
	public void computerPlayerSuggestionTest(){
		Card mustardCard = new Card();
		Card peacockCard = new Card();
		
		Card knifeCard = new Card();
		Card leadpipeCard = new Card();
		
		Card kitchenCard = new Card();
		Card garageCard = new Card();
		
		mustardCard = new Card("Colonel Mustard", Card.CardType.PERSON);
		peacockCard = new Card("Mrs. Peacock", Card.CardType.PERSON);

		knifeCard = new Card("knife", Card.CardType.WEAPON);
		leadpipeCard = new Card("lead pipe", Card.CardType.WEAPON);
		
		kitchenCard = new Card("Kitchen", Card.CardType.ROOM);
		garageCard = new Card("Garage", Card.CardType.ROOM);
		
		//Add mustarcCard, knifeCard and kitchenCard to the seen list
		board.addSeenCard(mustardCard);
		board.addSeenCard(knifeCard);
		board.addSeenCard(kitchenCard);
		
		//Place the computer player in the garage and then check that his suggestion doesn't contain any of the seend cards. 
		//Run this 100 times since the suggestion is calculated randomly from the deck and the seen cards will not always
		//be pulled from the deck
		for(int i = 0; i < 100; i++){
		ComputerPlayer player = new ComputerPlayer(0, 0, "Mrs. White", Color.WHITE);
		assertFalse(player.makeSuggestion(board, garageCard).contains(mustardCard));
		assertFalse(player.makeSuggestion(board, garageCard).contains(knifeCard));
		assertFalse(player.makeSuggestion(board, garageCard).contains(kitchenCard));
		//Make sure that the correct number of cards is suggested
		assertEquals(player.makeSuggestion(board, garageCard).size(), 3);
		}
		

	}

}
