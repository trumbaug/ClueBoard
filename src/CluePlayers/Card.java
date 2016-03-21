package CluePlayers;

public class Card {
	public enum CardType {PERSON, WEAPON, ROOM};
	private String cardName;
	private CardType cardType;
	
	public Card() {
		super();
	}
	
	public Card(String cardName, CardType cardType) {
		super();
		this.cardName = cardName;
		this.cardType = cardType;
	}

	public boolean equals(){
		return false;
	}
	
	public CardType getCardType(){
		return cardType;
	}
}
