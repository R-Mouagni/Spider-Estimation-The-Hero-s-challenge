package view;

import java.util.List;

import application.Main;
import exception.EmptyDeckException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.BasicCard;
import model.Question;

public class BossVB extends VBox{
	//Labels
	private Label lblTitle;
	private Label lblChallenge;
	private Label lblQuestion;
	private Label lblAnswer;
	private Label lblVerification;

	//Buttons
	private Button btnSubmitAnswer;
	private Button btnDraw;

	//TextFields
	private TextField txtAnswer;

	//ChoiceBox (temporary)

	//Models
	private GameSP game;
	private BasicCard randomCard;
	private PlayerView player;
	private Question question;

	private int currentDifficultyLevel = 0;


	// Displays a question for the specified card and player + CSS for the display
	public BossVB(GameSP game, PlayerView player, String squareClass) throws EmptyDeckException {
		this.game = game;
		this.player = player;
		this.randomCard = getRandomCard();

		this.setSpacing(10);
		this.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());

		getLblTitle().setText(player.getPlayer().toStringDuel() + " VS. Venom" + "\n\nQuestion for theme " + randomCard.getTheme());
		this.getChildren().add(getLblTitle());
		getLblTitle().setTextFill(Color.BLACK);
		getLblTitle().setWrapText(true);
		VBox.setMargin(getLblTitle(), new Insets(0, 0, 0, 40));

		getLblChallenge().setText("The subject is " + randomCard.getSubject() + "\n");
		getLblChallenge().setWrapText(true); 

		getLblQuestion().setWrapText(true); 
		this.getChildren().addAll(getLblChallenge(), getLblQuestion(), getTxtAnswer(), getBtnSubmitAnswer());
		this.setAlignment(Pos.CENTER);

		this.getStyleClass().add("layoutQuestion");
		this.getStyleClass().add(squareClass);

		setPrefSize(300, 200);
		setMinSize(200, 100);
		setMaxSize(600, 500);
		//getStyleClass().add("info-box");

		game.getChildren().add(this);
	}

	public BasicCard getRandomCard() throws EmptyDeckException {
		if (randomCard == null) {
			randomCard = game.getDeck().drawRandomCard(game.getDeck().getCards());
			if (randomCard == null) {
				throw new EmptyDeckException();
			}
		}
		return randomCard;
	}
	
	public Question findQuestionByDifficulty(int selectedDifficultyIndex) {
		// Get the list of questions from the card
		List<Question> questions = randomCard.getQuestions();
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

	public Button getBtnSubmitAnswer() {
	    // Create the button if not already initialized
	    if (btnSubmitAnswer == null) {
	        question = findQuestionByDifficulty(currentDifficultyLevel);
	        btnSubmitAnswer = new Button("Submit Answer");
	        getLblQuestion().setText(question.getChallenge());
            getLblAnswer().setText(question.getAnswer());
	        this.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.F1) { 
                	getTxtAnswer().setText(getLblAnswer().getText());
                }
            });
	        getLblQuestion().setWrapText(true); 

	        btnSubmitAnswer.setOnAction(e -> {
	            // Check if the player's answer is correct
	            if (question.checkAnswer(getTxtAnswer().getText())) {
	                // Increment the difficulty level and display the next question
	                currentDifficultyLevel++;
	                if (currentDifficultyLevel <= 3) {
	                    question = findQuestionByDifficulty(currentDifficultyLevel);
	                    getLblQuestion().setText(question.getChallenge());
	                    getLblAnswer().setText(question.getAnswer());
	                } else {
	                    // End the game if all questions are answered correctly
	                    StackPane parent = (StackPane) getParent();
	                    parent.getChildren().remove(this);
	                    // Display the victory message and end the game
	                    game.stopBackgroundMusic();
	                    new InfoBoxVideoVB(game, "/resources/medias/finalVideo.mp4", "Congratulations! You have beaten Venom but be careful, the danger isn't over yet! \n\nYou win this game.  " + player.getPlayer().toStringDuel() + "!");
	                }
	            } else {
	                // Display a verification message if the answer is incorrect
	                getLblVerification().setText("Incorrect! You have lost this fight. \n\nMaybe the next time...");
	                getLblVerification().setTextFill(Color.RED);
	                getBtnSubmitAnswer().setDisable(true);
	                this.getChildren().addAll(getLblVerification(), getBtnDraw());
	            }
	            // Clear the answer field after submission
	            getTxtAnswer().clear();
	        });
	    }
	    return btnSubmitAnswer;
	}



	public Button getBtnDraw() {
		if(btnDraw==null) {
			btnDraw=new Button("Next turn");
			btnDraw.setOnAction(event->{
				StackPane parent = (StackPane) getParent();
				parent.getChildren().remove(this);
				game.nextTurn();
			});
		}
		return btnDraw;
	}

	public TextField getTxtAnswer() {
		if(txtAnswer==null) {
			txtAnswer = new TextField();
		}
		return txtAnswer;
	}
}
