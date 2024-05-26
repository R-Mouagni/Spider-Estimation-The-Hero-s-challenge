package test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.CardAlreadyExistsException;
import model.BasicCard;
import model.Deck;
import model.Theme;

class DeckTest {

	private Deck deck;
    private BasicCard b1;
    private BasicCard b2;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
        b1 = new BasicCard("Paul", Theme.EDUCATION, "animaux");
        b2 = new BasicCard("Jean", Theme.ENTERTAINMENT, "physique");

    }
    
    @AfterEach
	void tearDown() throws Exception {
		deck=null;
		b1=null;
		b2=null;
	}

	@Test
    public void testAdd() throws CardAlreadyExistsException, IllegalArgumentException, IllegalAccessException {
	    // Adding a card to the deck
		deck.add(b1);
		
		// Verifying the size of the deck after adding a card
		try {
			Field f = Deck.class.getDeclaredField("cards");
	        f.setAccessible(true);
	        ArrayList<BasicCard> cardsList = (ArrayList<BasicCard>) f.get(deck);
	        assertEquals(1, cardsList.size(), "The deck size should be equal to 1 after adding a card.");

		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

        // Verifying that adding an other card doesn't throws exception
        assertDoesNotThrow(()->deck.add(b2));  
        
        // Verifying that adding a duplicate card throws CardAlreadyExistsException
		assertThrows(CardAlreadyExistsException.class, () ->deck.add(b1));
    }
}
