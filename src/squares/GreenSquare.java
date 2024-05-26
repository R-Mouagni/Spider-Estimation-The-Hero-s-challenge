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

public class GreenSquare extends Square {
    private static final String IMAGE_GREEN_SQUARE = "/resources/img/squares/greenSquare.png";
    
    public GreenSquare() {
        super(IMAGE_GREEN_SQUARE);
    }
    
	@Override
	public void onPlayerEnter(GameSP game, PlayerView player) throws EmptyDeckException, ThemeNotFoundException {
		try {
			//deck creation
			Deck deck = game.getDeck();
			if (deck == null || deck.getCards().isEmpty()) {
				throw new EmptyDeckException();
			}
			//take education cards
			ArrayList<BasicCard> educationsCards = deck.filterCardsByTheme(deck.getCards(), Theme.EDUCATION);

			//Use a random Education card
			BasicCard randomEducationsCard = deck.drawRandomCard(educationsCards);
			if (randomEducationsCard == null) {
				throw new EmptyDeckException();
			}
			//layout
			new QuestionDisplayVB(game, randomEducationsCard, player, "green-card");

		} catch (EmptyDeckException e) {
			e.printStackTrace();
		}
	}	

	@Override
	public String getTxtColor() {
		// TODO Auto-generated method stub
		return null;
	}




}

