package exception;

public class EmptyDeckException extends Exception {
    private static final long serialVersionUID = 1L;

    //Exception if Deck is empty
	public EmptyDeckException() {
        super("The deck is empty!");
    }
}

