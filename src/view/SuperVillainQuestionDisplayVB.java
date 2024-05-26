package view;

import java.util.ArrayList;
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

public class SuperVillainQuestionDisplayVB extends VBox{
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
	public SuperVillainQuestionDisplayVB(GameSP game, BasicCard card, PlayerView player, String squareClass) {
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
	            int selectedDifficultyIndex = getCbDifficultyChoice().getSelectionModel().getSelectedIndex();
	            Question question=findQuestionByDifficulty(selectedDifficultyIndex);
	            getLblQuestion().setText(question.getChallenge());
	            getLblAnswer().setText(question.getAnswer());
	            getLblQuestion().setWrapText(true); 
	            
	            this.getChildren().add(getLblQuestion());
	            this.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.F1) { 
                    	getTxtAnswer().setText(getLblAnswer().getText());
                    }
                });
	            getCbDifficultyChoice().setDisable(true);
	            btnSubmitDifficultyChoice.setDisable(true);

	            this.getChildren().add(getTxtAnswer());
	            this.getChildren().add(getBtnSubmitAnswer());
	            getBtnSubmitAnswer().setOnAction(h->{
	                if(question.checkAnswer(getTxtAnswer().getText())) {
	                    game.getGameBoard().movePlayer(player, selectedDifficultyIndex+1);
	                    ArrayList<PlayerView>players=game.getPlayers();
	                    for(PlayerView p:players) {
	                    	if(p!=player) {
	                    		game.getGameBoard().movePlayer(p, selectedDifficultyIndex*-1-1);
	                    	}
	                    }
	                    System.out.println("Il a bien répondu voici ces nouvelles coordonnées "+player.getPlayer().getCoordinates());
	                    System.out.println();
	                    getLblVerification().setText("Correct !");
	                    new InfoBoxVB(game, "Good job ! You have won this battle and are now " + (selectedDifficultyIndex+1) + " ahead !");                    getLblVerification().setTextFill(Color.GREEN);
	                    getBtnSubmitAnswer().setDisable(true);
	                } else {
	                    game.getGameBoard().movePlayer(player, selectedDifficultyIndex*-1-1);
	                    System.out.println("Il a mal répondu il doit rester à ces coordonnées"+player.getPlayer().getCoordinates());
	                    System.out.println();
	                    getLblVerification().setText("Incorrect!\n The correct answer is: \n" + question.getAnswer());
	                    getLblVerification().setWrapText(true);
	                    new InfoBoxVB(game, "You have lost this fight, you move back " + (selectedDifficultyIndex+1));                    getLblVerification().setTextFill(Color.RED);
	                    getBtnSubmitAnswer().setDisable(true);
	                }
	                getBtnDraw().setOnAction(event -> {
	                    game.nextTurn();
	                    game.getContener().getChildren().remove(1);
	                });
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
