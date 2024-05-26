package view;

import java.util.List;

import application.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.BasicCard;
import model.Question;

public class SpiderGadgetPlusTwoQuestionDisplayVB extends VBox{
	//Labels
	private Label lblTitle;
	private Label lblChallenge;
	private Label lblQuestion;
	private Label lblAnswer;
	private Label lblVerification;
	
	//Buttons
	private Button btnSubmitDifficultyChoice;
	private Button btnSubmitAnswer;
	private Button btnDraw;
	
	//TextFields
	private TextField txtAnswer;
	
	//ChoiceBox
	private ChoiceBox<String> cbDifficultyChoice;
	
	//Models
	private GameSP game;
	private BasicCard card;
	private PlayerView player;
	
	
    // Displays a question for the specified card and player + CSS for the display
	public SpiderGadgetPlusTwoQuestionDisplayVB(GameSP game, BasicCard card, PlayerView player, String squareClass) {
		this.game = game;
		this.card = card;
		this.player = player;
		
		this.setSpacing(10);
        this.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

        getLblTitle().setText("Question for theme " + card.getTheme());
        this.getChildren().add(getLblTitle());
        getLblTitle().setTextFill(Color.BLACK);
        getLblTitle().setWrapText(true);
        VBox.setMargin(getLblTitle(), new Insets(0, 0, 0, 40));
        
        getLblChallenge().setText("How much are you putting on the subject " + card.getSubject() + " , Spidey ?");

        getLblChallenge().setWrapText(true); 
        
        this.getChildren().addAll(getLblChallenge(), getCbDifficultyChoice(), getBtnSubmitDifficultyChoice());
        this.setAlignment(Pos.TOP_LEFT);

        this.getStyleClass().add("layoutQuestion");
        this.getStyleClass().add(squareClass);

        this.setMaxSize(385,500);
        this.setPadding(new Insets(0, 0, 0, 10));
        
        game.getContener().getChildren().add(this);

	}
	
	
    public Question findQuestionByDifficulty(int selectedDifficultyIndex) {
        // Get the list of questions from the card
        List<Question> questions = card.getQuestions();
        // Check if the index is valid
        if (selectedDifficultyIndex >= 0 && selectedDifficultyIndex < questions.size()) {
            // Get the question at the index corresponding to the chosen difficulty level
            return questions.get(selectedDifficultyIndex);
        }
        return null;
    }

	public Label getLblTitle() {
		if(lblTitle==null) {
			lblTitle = new Label();
		}
		return lblTitle;
	}

	public Label getLblChallenge() {
		if(lblChallenge==null) {
			lblChallenge = new Label();
		}
		return lblChallenge;
	}

	public Label getLblQuestion() {
		if(lblQuestion==null) {
			lblQuestion = new Label();
		}
		return lblQuestion;
	}

	public Label getLblAnswer() {
		if(lblAnswer==null) {
			lblAnswer = new Label();
		}
		return lblAnswer;
	}

	public Label getLblVerification() {
		if(lblVerification==null) {
			lblVerification = new Label();
		}
		return lblVerification;
	}
	
	
	public Button getBtnSubmitDifficultyChoice() {
	    if(btnSubmitDifficultyChoice==null) {
	        btnSubmitDifficultyChoice = new Button("Submit Difficulty Choice");
	        btnSubmitDifficultyChoice.setOnAction(e -> {
	            // Retrieve the index of the selected difficulty level
	            int selectedDifficultyIndex = getCbDifficultyChoice().getSelectionModel().getSelectedIndex();
	            // Find the question corresponding to the selected difficulty level
	            Question question=findQuestionByDifficulty(selectedDifficultyIndex);
	            getLblQuestion().setText(question.getChallenge());
	            getLblAnswer().setText(question.getAnswer());
	            getLblQuestion().setWrapText(true); 
	            
	            this.getChildren().addAll(getLblQuestion());
	            
	            this.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.F1) { 
                    	getTxtAnswer().setText(getLblAnswer().getText());
                    }
                });
	            // Disable the choice box and the submit button for difficulty choice after submission
	            getCbDifficultyChoice().setDisable(true);
	            btnSubmitDifficultyChoice.setDisable(true);

	            this.getChildren().add(getTxtAnswer());
	            this.getChildren().add(getBtnSubmitAnswer());
	            getBtnSubmitAnswer().setOnAction(h->{
	                // Check if the player's answer is correct
	                if(question.checkAnswer(getTxtAnswer().getText())) {
	                    // If correct, move the player accordingly and display a success message
	                    game.getGameBoard().movePlayer(player, selectedDifficultyIndex+3);
	                    System.out.println("He answered correctly. Here are his new coordinates: "+player.getPlayer().getCoordinates());
	                    System.out.println();
	                    getLblVerification().setText("Correct !");
	                    getLblVerification().setTextFill(Color.GREEN);
	                    getBtnSubmitAnswer().setDisable(true);
	                    // Display an info box with a success message
	                    new InfoBoxVB(game, "Well done! You advance " + (selectedDifficultyIndex+3) + " squares!");
	                } else {
	                    // If incorrect, move the player one square and display an error message
	                    System.out.println("He answered incorrectly. He must stay at his current coordinates: "+player.getPlayer().getCoordinates());
	                    System.out.println();
	                    getLblVerification().setText("Incorrect! The correct answer is: " + question.getAnswer());
	                    getLblVerification().setTextFill(Color.RED);
	                    getBtnSubmitAnswer().setDisable(true);
	                    // Display an info box with an error message
	                    new InfoBoxVB(game, "Too bad, you missed a great opportunity... \nYour efforts have moved you up a square.");
	                    game.getGameBoard().movePlayer(player, 1);
	                }
	                // Provide a button to proceed to the next turn
	                getBtnDraw().setOnAction(event -> {
	                    game.nextTurn();
	                    game.getContener().getChildren().remove(1);
	                });
	                // Display the verification label and the button for the next turn
	                this.getChildren().addAll(getLblVerification(), getBtnDraw());
	            });
	        });
	    }
	    return btnSubmitDifficultyChoice;
	}
	
	public Button getBtnSubmitAnswer() {
		if(btnSubmitAnswer==null) {
			btnSubmitAnswer = new Button("Submit Answer");
		}
		return btnSubmitAnswer;
	}
	
	public Button getBtnDraw() {
		if(btnDraw==null) {
			btnDraw=new Button("Next turn, Draw Card");
		}
		return btnDraw;
	}
	
	public ChoiceBox<String> getCbDifficultyChoice() {
		if(cbDifficultyChoice==null) {
			cbDifficultyChoice = new ChoiceBox<String>();
	        cbDifficultyChoice.getItems().addAll("1", "2", "3", "4");
		}
		return cbDifficultyChoice;
	}
	
	public TextField getTxtAnswer() {
		if(txtAnswer==null) {
			txtAnswer = new TextField();
		}
		return txtAnswer;
	}
}
