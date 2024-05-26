package squares;

import java.util.Random;

import strategy.SpiderInfiltration;
import strategy.SpiderPlusTwo;
import strategy.StrategySpiderGadget;
import view.GameSP;
import view.PlayerView;

public class SpiderGadgetSquare extends Square {
    private static final String IMAGE_SPIDER_GADGET_SQUARE = "/resources/img/squares/yellowSquare.png";
    private StrategySpiderGadget strategy;
    private static int previousRandom = -1;
    
    public SpiderGadgetSquare() {
        super(IMAGE_SPIDER_GADGET_SQUARE);
        // Choose a random spider gadget strategy
        this.strategy = chooseRandomSpiderGadget();
    }
    
	@Override
	public void onPlayerEnter(GameSP game, PlayerView player) {
		// Execute the selected spider gadget strategy
		strategy.activateSpiderGadget(game, player);
		System.out.println("Strategy for " + player.getPlayer().toStringDuel() + " : " + strategy.toString());
	}
	
    // Method to choose a random spider gadget strategy
    public StrategySpiderGadget chooseRandomSpiderGadget() {
        Random rand = new Random();
        int alea;
        // Generate a random index until it differs from previous
        do {
            alea = rand.nextInt(2);
        } while (alea == previousRandom);

        previousRandom = alea;    
        
        switch (alea) {
            case 0:
            	// Return SpiderPlusTwo strategy
                return new SpiderPlusTwo();
            case 1:
            	// Return SpiderInfiltration strategy
                return new SpiderInfiltration();
            default:
                return null;
        }
    }
}

