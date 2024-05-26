package view;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Background {
    private ImageView background1;
    private ImageView background2;
    private int backgroundOffset;
    private static final double ANIMATION_DURATION = 100; 
    private static final String IMAGE_BACKGROUND = "/resources/img/bg/background.png";

    public Background() {
        this.backgroundOffset = 0;
        background1 = createBackground(IMAGE_BACKGROUND);
        background2 = createBackground(IMAGE_BACKGROUND);
        startBackgroundAnimations();
    }

    private ImageView createBackground(String imagePath) {
		Image image = (new Image(getClass().getResource(imagePath).toString()));
        ImageView background = new ImageView(image);
        background.setFitWidth(1920);
        background.setFitHeight(1080);
        return background;
    }

    private void startBackgroundAnimations() {
        TranslateTransition backgroundAnimation1 = createBackgroundAnimation(background1, true);
        TranslateTransition backgroundAnimation2 = createBackgroundAnimation(background2, false);
        backgroundAnimation1.play();
        backgroundAnimation2.play();
    }

    // Creates a background animation for translating the background image horizontally.
    private TranslateTransition createBackgroundAnimation(ImageView background, boolean moveLeft) {
        TranslateTransition animation = new TranslateTransition(Duration.seconds(ANIMATION_DURATION), background);
        animation.setFromX(moveLeft ? 0 : 1920);
        animation.setToX(moveLeft ? -1920 : 0);
        animation.setCycleCount(TranslateTransition.INDEFINITE);
        animation.setAutoReverse(false);
        animation.setInterpolator(Interpolator.LINEAR);
        animation.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
            double frac = newValue.toSeconds() / ANIMATION_DURATION;
            int backgroundOffset = (int) (frac * 6350) % 635;
            setBackgroundOffset(backgroundOffset);
        });
        return animation;
    }

    public ImageView getBackground1() {
        return background1;
    }

    public ImageView getBackground2() {
        return background2;
    }

    public int getBackgroundOffset() {
        return backgroundOffset;
    }

    public void setBackgroundOffset(int backgroundOffset) {
        this.backgroundOffset = backgroundOffset;
    }
    
}
