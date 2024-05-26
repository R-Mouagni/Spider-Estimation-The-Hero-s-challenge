package exception;

public class DeckNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	//Exception if deck not found
	public DeckNotFoundException() {
		super("Deck not found !");
	}

}
