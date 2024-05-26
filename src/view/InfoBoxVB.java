package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class InfoBoxVB extends VBox {
    private Label label;
    private Button continueButton;
    private String message;

    public InfoBoxVB(GameSP game, String message) {
    	this.message = message;
    	
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10));
    	
    	getChildren().addAll(getLabel(), getContinueButton());
    	
        setPrefSize(300, 200);
        setMinSize(200, 100);
        setMaxSize(600, 500);
        getStyleClass().add("info-box");

        game.getChildren().add(this);
    }

    public Label getLabel() {
        if (label == null) {
            label = new Label();
            label.setTextAlignment(TextAlignment.CENTER);
            label.setWrapText(true);
            label.getStyleClass().add("info-label");
            label.setText(message);
        }
        return label;
    }

    public Button getContinueButton() {
        if (continueButton == null) {
            continueButton = new Button("Continue");
            continueButton.setOnAction(event -> {
                StackPane parent = (StackPane) getParent();
                parent.getChildren().remove(this);
            });
        }
        return continueButton;
    }
}
