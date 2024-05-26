package view;

import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.DeckDuel;
import model.Question;

public class DuelBP extends BorderPane{
	//CONSTANTS
	private static int START_TIME=30;
	private static int END_TIME=0;

	//TOP
    private Label titleLayoutDuel;
    
    //LEFT
    private Label lblTimer1;
    private Timeline timerPlayer1;
    private int timer1=START_TIME;
    
    //RIGHT
    private Label lblTimer2;
    private Timeline timerPlayer2;
    private int timer2=START_TIME;
    
    //CENTER
    private VBox layoutDuelVB;
    private Label lblTurnPlayer;
    private Label lblQuestion;
    private TextField txtAnswer;
    

    //MODEL
    private PlayerView player1;
    private PlayerView player2;
    private PlayerView currentPlayer;
    private DeckDuel deckDuel;
    private Question currentQuestion;
    
    public GameSP game;
    public DuelBP(GameSP game, PlayerView currentPlayer) {
    	this.game = game;
        player2 = getRandomPlayer(currentPlayer);
        this.player1 = currentPlayer;
        this.currentPlayer=player2;
        this.deckDuel = new DeckDuel();
        this.setCenter(getLayoutDuelVB());
        this.setTop(getTitleLayoutDuel());
        this.setLeft(getLblTimer1());
        this.setRight(getLblTimer2());
        
        //CSS
        this.setId("duel");
        this.setMaxSize(1000, 800);
        BorderPane.setAlignment(getLayoutDuelVB(), Pos.CENTER);
        BorderPane.setAlignment(getTitleLayoutDuel(), Pos.CENTER);
        this.game.getChildren().add(this);
        showNextQuestion();
        switchTurn();
    }

    public void startDuel() {
    	System.out.println("START DUEL");
        showNextQuestion();
    }

    //Displays the next question in the duel
    private void showNextQuestion() {
    	System.out.println("SHOW NEXT");
        currentQuestion = deckDuel.getRandomQuestion();
        //changes the question after draw it 
        getLblQuestion().setText(currentQuestion.getChallenge()+"\n\n(Press ENTER key for validate your answer or pass the question.)");
        getTxtAnswer().setText("");
        getLblTurnPlayer().setText(currentPlayer.getPlayer().toStringDuel());
    }

    //Switches the turn between players and stop/start timer
    private void switchTurn() {
    	//check which player is current to continue the duel
        if(currentPlayer.equals(player1)) {
            getTimerPlayer1().stop();
            getTimerPlayer2().play();
            System.out.println("Changement de timer");
            currentPlayer = player2;
        } else {
            getTimerPlayer2().stop();
            getTimerPlayer1().play();
            currentPlayer=player1;
        }
    }
    
    public Timeline getTimerPlayer1() {
        if (timerPlayer1 == null) {
            timerPlayer1 = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    getLblTimer1().setText(player1.getPlayer().toStringDuel() + "\n\nTime left :\n" + timer1 + " s");
                    timer1--;
                    //check end of timer and define winner
                    if (timer1 == END_TIME) {
						game.getGameBoard().movePlayer(player1, -2);
						game.getGameBoard().movePlayer(player2, 2);
						game.getChildren().remove(this);
						game.nextTurn();
						 
						new InfoBoxVB(game, "THE WINNER IS "+player2.getPlayer().toStringDuel() + "!\n\n" +
						player2.getPlayer().toStringDuel() + " moves forward 2 squares, and " + player1.getPlayer().toStringDuel() + "moves back 2 squares.");
                    }
                })
            );
            timerPlayer1.setCycleCount(Timeline.INDEFINITE);
        }
        return timerPlayer1;
    }

    public Timeline getTimerPlayer2() {
        if (timerPlayer2 == null) {
            timerPlayer2 = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    getLblTimer2().setText(player2.getPlayer().toStringDuel() + "\n\nTime left :\n" + timer2 + " s");
                    timer2--;
                    //check end of timer and define winner
                    if (timer2 == END_TIME) {
						game.getGameBoard().movePlayer(player2, -2);
						game.getGameBoard().movePlayer(player1, 2);
						game.getChildren().remove(this);
						game.nextTurn();
						
						new InfoBoxVB(game, "THE WINNER IS "+player1.getPlayer().toStringDuel() + "!\n\n" +
						player1.getPlayer().toStringDuel() + " moves forward 2 squares, and " + player2.getPlayer().toStringDuel() + " moves back 2 squares.");
						
					}
                })
            );
            timerPlayer2.setCycleCount(Timeline.INDEFINITE);
        }
        return timerPlayer2;
    }

    public Label getLblTimer1() {
        if (lblTimer1 == null) {
            lblTimer1 = new Label(player1.getPlayer().toStringDuel() + "\n\nTime left :\n 30 s");
        }
        return lblTimer1;
    }

    public Label getLblTimer2() {
        if (lblTimer2 == null) {
            lblTimer2 = new Label(player2.getPlayer().toStringDuel() + "\n\nTime left :\n 30 s");
        }
        return lblTimer2;
    }
    
    
    //recovers a random player takes as argument the
    //player who launched the duel to avoid duplication
    public PlayerView getRandomPlayer(PlayerView currentPlayer) {
        List<PlayerView> players = game.getPlayers(); 
        
        Random rand = new Random();
        PlayerView randomPlayer;
        
        do {
            randomPlayer = players.get(rand.nextInt(players.size()));
        } while (randomPlayer == currentPlayer); 
        
        return randomPlayer;
    }

    public Label getLblQuestion() {
        if (lblQuestion == null) {
            lblQuestion = new Label();
        }
        return lblQuestion;
    }

    public TextField getTxtAnswer() {
        if (txtAnswer == null) {
            txtAnswer = new TextField();
            txtAnswer.setOnKeyPressed(e->  {
            	if(e.getCode().equals(KeyCode.ENTER)) {
            		if(currentQuestion.checkAnswer(getTxtAnswer().getText())) {
            			showNextQuestion();
            			switchTurn();            		
            		}else {
            			showNextQuestion();
            		}            		
            	}
            });    
        }
        return txtAnswer;
    }  
    
    public VBox getLayoutDuelVB() {
    	if(layoutDuelVB==null) {
    		layoutDuelVB=new VBox(20);
            layoutDuelVB.setId("layoutDuel");
            layoutDuelVB.getChildren().addAll(getLblTurnPlayer(),getLblQuestion(),getTxtAnswer());
    	}
		return layoutDuelVB;
	}
    
    public Label getTitleLayoutDuel() {
    	if(titleLayoutDuel==null){
            titleLayoutDuel = new Label("DUEL : " + player1.getPlayer().toStringDuel() + " VS. " + player2.getPlayer().toStringDuel());
            titleLayoutDuel.setWrapText(true);
            titleLayoutDuel.setFont(Font.font(20));
            VBox.setMargin(titleLayoutDuel, new Insets(0, 0, 0, 40));
    		
    	}
		return titleLayoutDuel;
	}
    
    public Label getLblTurnPlayer() {
    	if(lblTurnPlayer==null){
    		lblTurnPlayer=new Label();
    	}
		return lblTurnPlayer;
	}

}
