package squares;

import exception.EmptyDeckException;
import view.BossVB;
import view.GameSP;
import view.PlayerView;

public class FinalSquare extends Square {
    private static final String IMAGE_FINAL_SQUARE = "/resources/img/squares/finalSquare.png";
    
    public FinalSquare() {
        super(IMAGE_FINAL_SQUARE);
    }
    
    @Override
    public void onPlayerEnter(GameSP game, PlayerView player) {
    	try {
			new BossVB(game, player, "black-square");
		} catch (EmptyDeckException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}