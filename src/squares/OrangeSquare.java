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

public class OrangeSquare extends Square {
    private static final String IMAGE_ORANGE_SQUARE = "/resources/img/squares/orangeSquare.png";
    
    public OrangeSquare() {
        super(IMAGE_ORANGE_SQUARE);
    }
    
	@Override
	public void onPlayerEnter(GameSP game, PlayerView player) throws EmptyDeckException, ThemeNotFoundException {
		try {
			//deck creation
			Deck deck = game.getDeck();
			if (deck == null || deck.getCards().isEmpty()) {
				throw new EmptyDeckException();
			}
			//take entertainments cards
			ArrayList<BasicCard> entertainmentsCards = deck.filterCardsByTheme(deck.getCards(), Theme.ENTERTAINMENT);

			//Use a random Entertainments cards
			BasicCard randomEntertainmentsCard = deck.drawRandomCard(entertainmentsCards);
			if (randomEntertainmentsCard == null) {
				throw new EmptyDeckException();
			}
			//layout
			new QuestionDisplayVB(game, randomEntertainmentsCard, player, "orange-card");

		} catch (EmptyDeckException e) {
			//e.printStackTrace();
		}
	}	

	@Override
	public String getTxtColor() {
		// TODO Auto-generated method stub
		return null;
	}




}

