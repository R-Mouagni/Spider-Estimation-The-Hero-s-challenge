package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.gson.Gson;

import exception.CardAlreadyExistsException;
import exception.DeckNotFoundException;
import util.Persistance;

/**
 * Represents a deck of basic cards
 */
public class Deck {
    public ArrayList<BasicCard> cards;

    /**
     * Constructs a new Deck object
     */
    public Deck() {
        this.cards = new ArrayList<>();
    }

    /**
     * Adds a card to the deck
     *
     * @param b The card to be added
     * @throws CardAlreadyExistsException If the card already exists in the deck
     */
    public void add(BasicCard b) throws CardAlreadyExistsException {
        if (!cards.contains(b)) {
            cards.add(b);
        } else {
            throw new CardAlreadyExistsException();
        }
    }

    /**
     * Removes a card from the deck
     *
     * @param b The card to be removed
     * @return true if the card was successfully removed, false otherwise
     */
    public boolean remove(BasicCard b) {
        return cards.remove(b);
    }

    /**
     * Retrieves a string representation of the deck
     *
     * @return A string representation of the deck
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Liste de cartes Deck :\n");
        str.append("======================\n");
        int i = 1;
        for (BasicCard b : cards) {
            str.append("\nCARTE NUMERO ").append(i).append(" :\n");
            str.append("----------------\n");
            str.append(b.toString());
            i++;
        }
        return str.toString();
    }

    /**
     * Retrieves the list of cards in the deck
     *
     * @return The list of cards in the deck
     */
    public ArrayList<BasicCard> getCards() {
        return cards;
    }

    /**
     * Sets the list of cards in the deck
     *
     * @param cards The list of cards to be set
     */
    public void setCards(ArrayList<BasicCard> cards) {
        this.cards = cards;
    }

    /**
     * Retrieves a list of cards from the deck
     *
     * @return A list of cards from the deck
     */
    public List<BasicCard> getData() {
        List<BasicCard> result = new ArrayList<>();
        for (BasicCard p : cards) {
            result.add(p);
        }
        return result;
    }

    /**
     * Loads a deck of cards from a file
     *
     * @param file The file from which to load the deck
     * @return The loaded deck of cards
     * @throws DeckNotFoundException If the specified deck file is not found
     */
    public static Deck loadDeckFromFile(String file) throws DeckNotFoundException {
        if (Persistance.isExist(file)) {
            String read = Persistance.readFile(file);
            Gson g = new Gson();
            Deck deck = g.fromJson(read, Deck.class);
            return deck;
        } else {
            throw new DeckNotFoundException();
        }
    }

    /**
     * Draws a random card from the deck
     *
     * @param cards The list of cards to draw from
     * @return A randomly selected card from the deck
     */
    public BasicCard drawRandomCard(ArrayList<BasicCard> cards) {
        return cards.get(new Random().nextInt(cards.size()));
    }

    /**
     * Filters cards by theme from the given list of cards
     *
     * @param cards The list of cards to filter
     * @param theme The theme to filter by
     * @return A list of cards with the specified theme
     */
    public ArrayList<BasicCard> filterCardsByTheme(ArrayList<BasicCard> cards, Theme theme) {
        ArrayList<BasicCard> filteredCards = new ArrayList<>();
        for (BasicCard card : cards) {
            if (card.getTheme() == theme) {
                filteredCards.add(card);
            }
        }
        return filteredCards;
    }
}
