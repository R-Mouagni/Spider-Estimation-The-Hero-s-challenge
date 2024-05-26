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

public class PurpleSquare extends Square {
    private static final String IMAGE_PURPLE_SQUARE = "/resources/img/squares/purpleSquare.png";
    
    public PurpleSquare() {
        super(IMAGE_PURPLE_SQUARE);
    }
    
    //Action when a player is on the square
	@Override
	public void onPlayerEnter(GameSP game, PlayerView player) throws EmptyDeckException, ThemeNotFoundException {
		try {
			
			//deck creation
			Deck deck = game.getDeck();
			if (deck == null || deck.getCards().isEmpty()) {
				throw new EmptyDeckException();
			}
			//take Improbable card
			ArrayList<BasicCard> impropablesCards = deck.filterCardsByTheme(deck.getCards(), Theme.IMPROBABLE);

			//use a random Improbable card
			BasicCard randomImprobablesCard = deck.drawRandomCard(impropablesCards);
			if (randomImprobablesCard == null) {
				throw new EmptyDeckException();
			}
			//layout
	        new QuestionDisplayVB(game, randomImprobablesCard, player, "purple-card");

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

