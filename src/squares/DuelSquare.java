package squares;

import view.DuelBP;
import view.GameSP;
import view.PlayerView;

public class DuelSquare extends Square {
    private static final String IMAGE_DUEL_SQUARE = "/resources/img/squares/redSquare.png";

    public DuelSquare() {
        super(IMAGE_DUEL_SQUARE);
    }

    @Override
    public void onPlayerEnter(GameSP game, PlayerView player) {
        DuelBP duel = new DuelBP(game, player);
        duel.startDuel();
    }
}
