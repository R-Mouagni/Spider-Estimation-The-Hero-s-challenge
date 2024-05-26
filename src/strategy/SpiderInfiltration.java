package strategy;

import view.GameSP;
import view.InfoBoxVB;
import view.PlayerView;

public class SpiderInfiltration implements StrategySpiderGadget{
	public void activateSpiderGadget(GameSP game, PlayerView player) {
		new InfoBoxVB(game, player.getPlayer().toStringDuel() + ", You've got the Spider-Infiltration gadget! You've infiltrated the system.\nIt'll automatically give you the right answer to a level 4 question.");
        // Move the player to 4 squares forward
        game.getGameBoard().movePlayer(player, 4);
        // Proceed to the next turn in the game
        game.nextTurn();
	}
}
