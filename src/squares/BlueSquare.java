package squares;

import java.util.ArrayList;

import exception.EmptyDeckException;
import exception.ThemeNotFoundException;
import model.BasicCard;
import model.Deck;
import model.Theme;
import view.GameSP;
import view.PlayerView;
import view.QuestionDisplayVB;

public class BlueSquare extends Square {
	private static final String IMAGE_BLUE_SQUARE = "/resources/img/squares/blueSquare.png";

	public BlueSquare() {
		super(IMAGE_BLUE_SQUARE);
		setTxtColor("Blue");
	}

	@Override
	public void onPlayerEnter(GameSP game, PlayerView player) throws EmptyDeckException, ThemeNotFoundException {
		try {
			//deck creation
			Deck deck = game.getDeck();
			if (deck == null || deck.getCards().isEmpty()) {
				throw new EmptyDeckException();
			}
			//take Informatic cards
			ArrayList<BasicCard> informaticsCards = deck.filterCardsByTheme(deck.getCards(), Theme.INFORMATICS);

			//use a random Informatic cards
			BasicCard randomInformaticsCard = deck.drawRandomCard(informaticsCards);
			if (randomInformaticsCard == null) {
				throw new EmptyDeckException();
			}
			//layout
			new QuestionDisplayVB(game, randomInformaticsCard, player, "blue-card");

		} catch (EmptyDeckException e) {
			//e.printStackTrace();
		}
	}	
}

