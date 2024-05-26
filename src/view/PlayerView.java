package view;

import java.util.ArrayList;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.Player;

public class PlayerView extends StackPane{
	private TranslateTransition breathingAnimation;
    private ImageView imgPlayer;
    private Player player;
    
    public PlayerView(Image image,Player player) {
    	this.player=player;
        this.imgPlayer = new ImageView(image);
        this.getChildren().add(imgPlayer);
        createBreathingAnimation();
        startBreathingAnimation();
    }
    
    private void createBreathingAnimation() {
        breathingAnimation = new TranslateTransition(Duration.seconds(0.3), this);
        breathingAnimation.setByY(-3); 
        breathingAnimation.setCycleCount(TranslateTransition.INDEFINITE);
        breathingAnimation.setAutoReverse(true);
        breathingAnimation.setInterpolator(Interpolator.EASE_BOTH);
    }
    
    // Starts the breathing animation if not already running
    private void startBreathingAnimation() {
        if (breathingAnimation != null && !breathingAnimation.getStatus().equals(TranslateTransition.Status.RUNNING)) {
            breathingAnimation.play();
        }
    }
    
    //adjust Position if only 1 player on the square
    public static void adjustSizeAndPosition(PlayerView p) {
    	p.setScaleX(1);
    	p.setScaleY(1.0);
    	p.setTranslateX(0);
    	p.setTranslateY(0);
    }
    
    //Adjust Position if 2 or more player are on the same square
    public static void adjustSizeAndPosition(ArrayList<PlayerView> playersAtSamePosition) {
        int numPlayers = playersAtSamePosition.size();
        double scale = 1;
        
        if(numPlayers>2) {
        	scale=0.5;
        }

        for (int i = 0; i < numPlayers; i++) {
            PlayerView player = playersAtSamePosition.get(i);
            player.setScaleX(scale);
            player.setScaleY(scale);

            if (numPlayers == 2) {
                if (i == 0) {
                    player.setTranslateX(-25);
                } else {
                    player.setTranslateX(25);
                }
            } else {
                double posX;
                if (i == 0) {
                	//Bottom Left
                    posX = -37.5; 
                } else if (i == 1) {
                	//Bottom Right
                    posX = -12.5; 
                } else if (i == 2) {
                	//Top Left
                    posX = 12.5; 
                } else {
                	//Top Right
                    posX = 37.5; 
                }
                player.setTranslateX(posX);
            }
        }
    }
    public Player getPlayer() {
		return player;
	}

    

}
