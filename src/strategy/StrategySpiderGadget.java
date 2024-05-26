package strategy;

import view.GameSP;
import view.PlayerView;

public interface StrategySpiderGadget {
    // Method signature for the method that change with each strategy
	public void activateSpiderGadget(GameSP game, PlayerView p);
}
