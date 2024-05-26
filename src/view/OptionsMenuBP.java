package view;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import util.MediaPlayerManager;

public class OptionsMenuBP extends BorderPane {
	private MediaPlayerManager mediaPlayerManager;

	private Button btnBack;

	//Box Option
	private VBox buttonBox;
	private Slider volumeSlider;
	private Button btnCard;
	private Label lblVolume;

	public OptionsMenuBP() {
		mediaPlayerManager = MediaPlayerManager.getInstance();
		setTop(getBtnBack());
		setCenter(getButtonBox());
	}

	public Label getLblVolume() {
		if(lblVolume==null) {
			lblVolume=new Label("Volume");
		}
		return lblVolume;
	}

	public Button getBtnBack() {
	    if(btnBack==null) {
	        btnBack=new Button("Back");
	        btnBack.setPrefSize(100, 50);
	        btnBack.setOnAction(event -> {
	            Scene scene = this.getScene();
	            MainMenuSP mainMenu = (MainMenuSP) scene.getRoot();
	            mainMenu.hideOptions();
	        });
	    }
	    return btnBack;
	}



	public Slider getVolumeSlider() {
		if(volumeSlider == null) {
			volumeSlider = new Slider();
			volumeSlider.setMin(0); 
			volumeSlider.setMax(1); 
			volumeSlider.setValue(mediaPlayerManager.getMenuMediaPlayer().getVolume()); 
			volumeSlider.setShowTickLabels(true);
			volumeSlider.setShowTickMarks(true);
			volumeSlider.setMajorTickUnit(0.2);
			volumeSlider.setBlockIncrement(0.05);
			volumeSlider.setMaxWidth(600); 

			volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
				mediaPlayerManager.setVolume(newValue.doubleValue());
			});
		}
		return volumeSlider;
	}


	public VBox getButtonBox() {
		if(buttonBox == null) {
			buttonBox = new VBox(20);
			buttonBox.setMaxSize(400,300);
			buttonBox.setAlignment(Pos.CENTER);
			buttonBox.getStyleClass().add("options-menu");
			BorderPane.setAlignment(buttonBox, Pos.CENTER); 

			buttonBox.getChildren().addAll(getLblVolume(), getVolumeSlider(), getBtnCard());


		}
		return buttonBox;
	}



    // Getter for card management button
	public Button getBtnCard() {
		if(btnCard==null) {
			btnCard = new Button("Card Managment");
			btnCard.setOnAction(event ->{
	            Scene scene = this.getScene();
	            MainMenuSP mainMenu = (MainMenuSP) scene.getRoot();
	            mainMenu.getChildren().clear();
	            mainMenu.getChildren().add(new LoginMenuVB());
			});
		}
		return btnCard;
	}





}
