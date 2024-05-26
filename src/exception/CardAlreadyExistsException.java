package exception;

public class CardAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//exception if the card is in the deck
	public CardAlreadyExistsException() {
		super("Cette carte se trouve déjà dans le deck");

	}

}
