package exception;

public class QuestionAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//exception if the question is in the card
	public QuestionAlreadyExistsException() {
		super("Cette question se trouve déjà dans la carte");
	}

}
