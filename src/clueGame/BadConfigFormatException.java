package clueGame;

public class BadConfigFormatException extends Exception{

	@Override
	public String toString() {
		return "BadConfigFormatException []";
	}
	public BadConfigFormatException(String message) {
		super(message);
	}
	
}
