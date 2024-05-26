package strategy;

import java.util.ArrayList;

import exception.EmptyDeckException;
import model.BasicCard;
import model.Deck;
import view.GameSP;
import view.InfoBoxVB;
import view.PlayerView;
import view.SpiderGadgetPlusTwoQuestionDisplayVB;

public class SpiderPlusTwo implements StrategySpiderGadget{
	
	public void activateSpiderGadget(GameSP game, PlayerView player) {
		try {
			new InfoBoxVB(game, player.getPlayer().toStringDuel() + ", You've got the Spider-PlusTwo gadget! \nPlay this card as usual, and You'll have two extra squares");
			// Deck creation
			// Check if the deck is empty or null
			Deck deck = game.getDeck();
			if (deck == null || deck.getCards().isEmpty()) {
				throw new EmptyDeckException();
			}

			ArrayList<BasicCard> cards = deck.getCards();
			
			// Draw a random card from the deck
			BasicCard randomCard = deck.drawRandomCard(cards);
			if (randomCard == null) {
				throw new EmptyDeckException();
			}
			// Display the card
			new SpiderGadgetPlusTwoQuestionDisplayVB(game, randomCard, player, "yellow-card");
		} catch (EmptyDeckException e) {
			//e.printStackTrace();
		}
	}
}
