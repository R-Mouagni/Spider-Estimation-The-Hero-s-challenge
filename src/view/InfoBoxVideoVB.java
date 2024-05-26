package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class InfoBoxVideoVB extends VBox {
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private Button continueButton;

    public InfoBoxVideoVB(GameSP game, String videoPath, String message) {
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource(videoPath).toString()));
        mediaView = new MediaView(mediaPlayer);
        mediaView.setFitWidth(1280);
        mediaView.setFitHeight(720);

        mediaPlayer.setVolume(0.3);
        
        // Event handler for when video ends
        mediaPlayer.setOnEndOfMedia(() -> {
            getChildren().remove(mediaView);
            getChildren().addAll(new Label(message), getContinueButton());
        });

        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));

        getChildren().addAll(mediaView);
        mediaPlayer.play();

        setPrefSize(300, 200);
        setMinSize(200, 100);
        setMaxSize(1280, 720);
        getStyleClass().add("info-box");

        game.getChildren().add(this);
    }

    public Button getContinueButton() {
        if (continueButton == null) {
            continueButton = new Button("Return to the main menu");
            continueButton.setOnAction(event -> {
                StackPane parent = (StackPane) getParent();
                parent.getChildren().clear();
	            Scene scene = parent.getScene();
	            MainMenuSP mainMenu = (MainMenuSP) scene.getRoot();
	            mainMenu.hideOptions();
	            //Reset game
	            scene.setRoot(new MainMenuSP());
            });
        }
        return continueButton;
    }
}