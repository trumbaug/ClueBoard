package clueGame;

public class BadConfigFormatException extends Exception{
	private String message;
	@Override
	public String toString() {
		return message;
	}
	public BadConfigFormatException(String message) {
		super(message);
		this.message = message;
	}
	
}
