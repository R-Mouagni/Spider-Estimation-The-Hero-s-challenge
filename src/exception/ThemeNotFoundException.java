package exception;

public class ThemeNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    //exception if no cards were found in the deck
    public ThemeNotFoundException() {
        super("No cards of the specified theme were found in the deck!");
    }
}
