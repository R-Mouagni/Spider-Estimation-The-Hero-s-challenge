package squares;

import java.util.ArrayList;

import exception.EmptyDeckException;
import model.BasicCard;
import model.Deck;
import view.GameSP;
import view.InfoBoxVB;
import view.PlayerView;
import view.SuperVillainQuestionDisplayVB;

public class SuperVilainSquare extends Square {
    private static final String IMAGE_SUPER_VILAIN_SQUARE = "/resources/img/squares/blackSquare.png";
    
    public SuperVilainSquare() {
        super(IMAGE_SUPER_VILAIN_SQUARE);
    }
    
	@Override
	public void onPlayerEnter(GameSP game, PlayerView player) {
		try {
			new InfoBoxVB(game, "The Sandman attacks you! \n" + player.getPlayer().toStringDuel() +", defend yourself by answering \nhis question correctly or you'll move back.");
			//deck creation
			Deck deck = game.getDeck();
			if (deck == null || deck.getCards().isEmpty()) {
				throw new EmptyDeckException();
			}

			ArrayList<BasicCard> cards = deck.getCards();
			
			BasicCard randomCard = deck.drawRandomCard(cards);
			if (randomCard == null) {
				throw new EmptyDeckException();
			}
			//layout
			new SuperVillainQuestionDisplayVB(game, randomCard, player, "black-card");

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


