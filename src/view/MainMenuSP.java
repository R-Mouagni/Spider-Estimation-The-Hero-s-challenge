package view;

import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import util.MediaPlayerManager;

public class MainMenuSP extends StackPane {

	private MediaPlayer mediaPlayer;

	// Button box
	private VBox buttonVBox;
	private Button btnPlay;
	private Button btnOptions;
	private Button btnCredits;
	private Button quitButton;

	// Choice Nb players box
	private VBox playerButtonVBox;
	private ToggleGroup playerToggleGroup;
	private ToggleButton btnTwoPlayers;
	private ToggleButton btnThreePlayers;
	private ToggleButton btnFourPlayers;
	private Button btnCancel;

	// credits
	private VBox creditsVBox;
	private Label lblCredits;
	private Button btnCancelCredit;

	public MainMenuSP() {
		playBackgroundMusic();
		createButtonBox();
		this.setBackground(createBackground("mainMenu.png"));
	}

	// Create the main button box
	public void createButtonBox() {
		if (getButtonVBox().getChildren().isEmpty()) {
			getButtonVBox().getChildren().addAll(getBtnPlay(), getBtnOptions(), getBtnCredits());
			getButtonVBox().setPadding(new Insets(0, 0, 150, 0));
			getChildren().addAll(getButtonVBox(), getQuitButton());
		}

	}

	private void createBoxChoiceNbPlayers() {
		this.getChildren().add(getPlayerButtonBox());
	}
	
	private void createBoxCredits() {
		this.getChildren().add(getCreditsVBox());
	}

	// Create a background image for the main menu
	private Background createBackground(String file) {
		Image bgImage;
		try {
			bgImage = new Image(getClass().getResource("/resources/img/menu/" + file).openStream());

			BackgroundSize backgroundSize = new BackgroundSize(100, 100, false, false, true, true);

			BackgroundImage backgroundImage = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

			return new Background(backgroundImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Play background music when the main menu is initialized
	private void playBackgroundMusic() {
		MediaPlayer mediaPlayer = MediaPlayerManager.getInstance().getMenuMediaPlayer();
		mediaPlayer.play();
	}

	// Stop background music when needed
	public void stopBackgroundMusic() {
		MediaPlayer mediaPlayer = MediaPlayerManager.getInstance().getMenuMediaPlayer();
		mediaPlayer.stop();
	}

	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public void displayOptions() {
		this.getChildren().clear();
		this.setBackground(createBackground("underMenu.png"));
		this.getChildren().add(new OptionsMenuBP());
	}

	// Hide options menu and display main menu buttons
	public void hideOptions() {
		this.getChildren().clear();
		this.setBackground(createBackground("mainMenu.png"));
		this.getChildren().addAll(getButtonVBox(), getQuitButton());
	}

	private static ToggleButton createToggleButton(String text, ToggleGroup group) {
		ToggleButton button = new ToggleButton(text);
		button.setToggleGroup(group);
		return button;
	}

	// Start the game with the specified number of players
	private void startPlay(int numberOfPlayers) {
		stopBackgroundMusic();
		GameSP game = new GameSP(numberOfPlayers);
		this.getChildren().clear();
		this.setBackground(null);
		this.getChildren().add(game);
		game.startGame();
	}

	// Start BoxMenu
	public VBox getButtonVBox() {
		if (buttonVBox == null) {
			buttonVBox = new VBox(20);
			buttonVBox.setAlignment(Pos.BOTTOM_CENTER);
			StackPane.setAlignment(buttonVBox, Pos.CENTER);
		}
		return buttonVBox;
	}

	private static Button createMainButton(String text) {
		Button button = new Button(text);
		button.getStyleClass().add("custom-main-buttons");

		if (text.equals("Quit")) {
			button.setStyle("-fx-pref-width: 200px; -fx-pref-height: 50px; -fx-font-size: 24px;");
		}
		if (text.equals("i")) {
			button.setStyle("-fx-pref-width: 100px; -fx-pref-height: 50px; -fx-font-size: 24px;");
		}
		return button;
	}

	public Button getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = createMainButton("Play");
			btnPlay.setOnAction(event -> createBoxChoiceNbPlayers());
		}
		return btnPlay;
	}

	public Button getBtnOptions() {
		if (btnOptions == null) {
			btnOptions = createMainButton("Options");
			btnOptions.setOnAction(event -> displayOptions());

		}
		return btnOptions;
	}

	public Button getBtnCredits() {
		if (btnCredits == null) {
			btnCredits = createMainButton("Credits");
			btnCredits.setOnAction(event-> createBoxCredits());
		}
		return btnCredits;
	}

	public Button getQuitButton() {
		if (quitButton == null) {
			quitButton = createMainButton("Quit");

			// Event
			quitButton.setOnAction(event -> System.exit(0));
			// CSS
			StackPane.setAlignment(quitButton, Pos.BOTTOM_RIGHT);
			StackPane.setMargin(quitButton, new Insets(0, 20, 30, 0));
		}
		return quitButton;
	}
	// End boxMenu

	// Start Box choice nb Players
	public VBox getPlayerButtonBox() {
		if (playerButtonVBox == null) {
			playerButtonVBox = new VBox(10);
			playerButtonVBox.getChildren().addAll(getBtnTwoPlayers(), getBtnThreePlayers(), getBtnFourPlayers(),
					getBtnCancel());
			// CSS
			playerButtonVBox.setAlignment(Pos.CENTER);
			playerButtonVBox.setMaxWidth(200);
			playerButtonVBox.setMaxHeight(300);
			StackPane.setAlignment(playerButtonVBox, Pos.CENTER);
			StackPane.setMargin(playerButtonVBox, new Insets(-100, 0, 0, 0));
			playerButtonVBox.getStyleClass().add("playerButtonBox");
		}
		return playerButtonVBox;
	}

	// Get the ToggleGroup for player selection
	public ToggleGroup getPlayerToggleGroup() {
		if (playerToggleGroup == null) {
			playerToggleGroup = new ToggleGroup();
		}
		return playerToggleGroup;
	}

	public ToggleButton getBtnTwoPlayers() {
		if (btnTwoPlayers == null) {
			btnTwoPlayers = createToggleButton("2 Players", getPlayerToggleGroup());
			btnTwoPlayers.setOnAction(event -> startPlay(2));

		}
		return btnTwoPlayers;
	}

	public ToggleButton getBtnThreePlayers() {
		if (btnThreePlayers == null) {
			btnThreePlayers = createToggleButton("3 Players", getPlayerToggleGroup());
			btnThreePlayers.setOnAction(event -> startPlay(3));
		}
		return btnThreePlayers;
	}

	public ToggleButton getBtnFourPlayers() {
		if (btnFourPlayers == null) {
			btnFourPlayers = createToggleButton("4 Players", getPlayerToggleGroup());
			btnFourPlayers.setOnAction(event -> startPlay(4));

		}
		return btnFourPlayers;
	}

	public Button getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new Button("Cancel");
			btnCancel.setOnAction(event -> {
				getChildren().remove(playerButtonVBox);
			});
			btnCancel.setId("btnCancel");
		}
		return btnCancel;
	}
	// End Box choice nb Players

	// Start credits
	// Start box credits
	public VBox getCreditsVBox() {
		if (creditsVBox == null) {
			creditsVBox = new VBox(10);
			creditsVBox.getChildren().addAll(getLblCredits(),getBtnCancelCredit());
			// CSS
			creditsVBox.setAlignment(Pos.CENTER);
			creditsVBox.setMaxWidth(600);
			creditsVBox.setMaxHeight(400);
			VBox.setMargin(getLblCredits(), new Insets(40));
			StackPane.setAlignment(creditsVBox, Pos.CENTER);
			StackPane.setMargin(creditsVBox, new Insets(-100, 0, 0, 0));
			creditsVBox.getStyleClass().add("credits-box");
		}
		System.out.println(creditsVBox.getChildren());
		return creditsVBox;
	}

	public Label getLblCredits() {
		if (lblCredits == null) {
			lblCredits=new Label("Developed by Delfosse Tristan, Mouagni Rayane and Topcu Erdem.\n\nThank you for your interest in our project.");
			lblCredits.setStyle("-fx-font-size : 20px");
		}
		return lblCredits;
	}

	public Button getBtnCancelCredit() {
		if (btnCancelCredit == null) {
			btnCancelCredit = new Button("Resume");
			btnCancelCredit.setOnAction(event -> {
				this.getChildren().remove(creditsVBox);
			});
			btnCancelCredit.setId("btnCancel");
		}
		return btnCancelCredit;
	}
	// End box credits

	// End credits

}
