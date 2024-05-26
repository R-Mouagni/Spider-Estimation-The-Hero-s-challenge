package view;

import com.google.gson.Gson;

import exception.DeckNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.BasicCard;
import model.Deck;
import model.Question;
import util.Persistance;

//Represents a card menu for managing card options
public class CardMenuVB extends BorderPane{
    private ComboBox<BasicCard>cbCard;
	private ObservableList<BasicCard> dataViewCards;

	private VBox containerVB;
	private HBox hbButton;
	private Button btnAdd;
    private Button btnDel;
    private Deck deck;
    private BasicCard currentCard;
    
    
    private Button btnBack;
    private Button btnReturn;
    
    //Table
    private GridPane tableGR;
    //Title
    private Label lblSubject;
    //Header
    private Label difficultyHeader;
    private Label questionHeader;
    private Label answerHeader;
    private Label modifyHeader;
    
    //Challenge 1
    private Label lblDifficulty1;
    private Label lblChallenge1;
    private Label lblAnswer1;
    private Button btnModify1;
    
    //Challenge 2
    private Label lblDifficulty2;
    private Label lblChallenge2;
    private Label lblAnswer2;
    private Button btnModify2;
    
    //Challenge 3
    private Label lblDifficulty3;
    private Label lblChallenge3;
    private Label lblAnswer3;
    private Button btnModify3;
    
    //Challenge 4
    private Label lblDifficulty4;
    private Label lblChallenge4;
    private Label lblAnswer4;
    private Button btnModify4;
    
    
   

    
	public CardMenuVB() {
		try {
			this.deck=Deck.loadDeckFromFile("deck.json");
		} catch (DeckNotFoundException e) {
			e.printStackTrace();
		}
		dataViewCards = FXCollections.observableArrayList(deck.getData());        
		currentCard=deck.getCards().get(0);
		this.setCenter(getContainerVB());
		this.setTop(getBtnBack());
        
        getCbCard().valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                currentCard = newValue;
                getTableGR();
            }
        });
	}


	
    // Method to get the ComboBox for selecting a card
	public ComboBox<BasicCard> getCbCard() {
	    if (cbCard == null) {
	        cbCard = new ComboBox<>();
	        cbCard.setItems(dataViewCards);

	        cbCard.setCellFactory(param -> new ListCell<BasicCard>() {
	            @Override
	            protected void updateItem(BasicCard item, boolean empty) {
	                super.updateItem(item, empty);
	                if (empty || item == null) {
	                    setText(null);
	                } else {
	                    setText(item.getSubject());
	                }
	            }
	        });

	        cbCard.setButtonCell(new ListCell<BasicCard>() {
	            @Override
	            protected void updateItem(BasicCard item, boolean empty) {
	                super.updateItem(item, empty);
	                if (empty || item == null) {
	                    setText(null);
	                } else {
	                    setText(item.getSubject());
	                }
	            }
	        });

	        cbCard.setPromptText("Select a card");
	    }
	    return cbCard;
	}
	
    // Method to get the HBox for buttons
	public HBox getHbButton() {
		if(hbButton==null) {
			hbButton=new HBox(10);
			hbButton.getChildren().addAll(getBtnReturn(),getBtnAdd(),getBtnDel());
			hbButton.setAlignment(Pos.CENTER);
		}
		return hbButton;
	}
	public Button getBtnAdd() {
		if(btnAdd == null) {
			btnAdd = new Button("Add");
			btnAdd.setPrefWidth(100);
			btnAdd.setOnAction(event -> {
			   	QuestionBuilderBP questionInterface = new QuestionBuilderBP();
		        questionInterface.setMaxSize(600, 400);
		        questionInterface.setStyle("-fx-background-color:white");
	            Scene scene = this.getScene();
	            MainMenuSP mainMenu = (MainMenuSP) scene.getRoot();
	            mainMenu.getChildren().clear();
	            mainMenu.getChildren().add(questionInterface);
			});
			
		}
		return btnAdd;
	}

	public Button getBtnDel() {
	    if (btnDel == null) {
	        btnDel = new Button("Delete Card");
	        btnDel.setPrefWidth(250); 
	        btnDel.setOnAction(event->{
	            deck.remove(currentCard);
	            Gson g = new Gson();
	            String json = g.toJson(deck);
	            Persistance.writeFile("deck.json", json);
	            try {
	                this.deck = Deck.loadDeckFromFile("deck.json");
	            } catch (DeckNotFoundException e) {
	                e.printStackTrace();
	            }
	            dataViewCards = FXCollections.observableArrayList(deck.getData());
	            if (!dataViewCards.isEmpty()) {
	                currentCard = deck.getCards().get(0);
	            }
	            cbCard.setItems(dataViewCards); 
	            getTableGR();
	        });
	    }
	    return btnDel;
	}


	
	private Button getBtnReturn() {
		if(btnReturn==null) {
			btnReturn = new Button("Return");
			btnReturn.setOnAction(event -> {
				Scene scene = this.getScene();
				MainMenuSP mainMenu = (MainMenuSP) scene.getRoot();
				mainMenu.getChildren().clear();
				mainMenu.displayOptions();
			});
			btnReturn.setPrefSize(100, 50);			
		}
		return btnReturn;
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
	
    // Method to get the VBox container
	public VBox getContainerVB() {
		if(containerVB==null) {
			containerVB=new VBox(10);
	        containerVB.getChildren().addAll(getCbCard(),getTableGR(),getHbButton());
	        containerVB.setAlignment(Pos.CENTER);
			
		}
		return containerVB;
	}
	
    // Method to get the GridPane for displaying card details
	public GridPane getTableGR() {
		if(tableGR==null) {
			tableGR = new GridPane();
            tableGR.setAlignment(Pos.CENTER);
            tableGR.setHgap(10);
            tableGR.setVgap(10);
            tableGR.setPadding(new Insets(100));
            tableGR.setPrefSize(1000, 500);
            tableGR.setMaxWidth(Double.MAX_VALUE);
            GridPane.setColumnSpan(getLblSubject(), 4);
            //Disposition
            //row 0 (subject)
            tableGR.add(getLblSubject(),0,0);
            //row 1 (headers)
            tableGR.add(getDifficultyHeader(), 0, 1);
            tableGR.add(getQuestionHeader(), 1, 1);
            tableGR.add(getAnswerHeader(), 2, 1);
            tableGR.add(getModifyHeader(), 3, 1);
            //row2 (question1)
            tableGR.add(getLblDifficulty1(), 0, 2);
            tableGR.add(getLblChallenge1(), 1, 2);
            tableGR.add(getLblAnswer1(), 2, 2);
            tableGR.add(getBtnModify1(), 3, 2);

            // Row 3 (question2)
            tableGR.add(getLblDifficulty2(), 0, 3);
            tableGR.add(getLblChallenge2(), 1, 3);
            tableGR.add(getLblAnswer2(), 2, 3);
            tableGR.add(getBtnModify2(), 3, 3);

            // Row 4 (question3)
            tableGR.add(getLblDifficulty3(), 0, 4);
            tableGR.add(getLblChallenge3(), 1, 4);
            tableGR.add(getLblAnswer3(), 2, 4);
            tableGR.add(getBtnModify3(), 3, 4);

            // Row 5 (question4)
            tableGR.add(getLblDifficulty4(), 0, 5);
            tableGR.add(getLblChallenge4(), 1, 5);
            tableGR.add(getLblAnswer4(), 2, 5);
            tableGR.add(getBtnModify4(), 3, 5);
		} else {
	        ObservableList<Question> questions = FXCollections.observableArrayList(currentCard.getQuestions());
	        lblSubject.setText(currentCard.getSubject());
	        for (int i = 0; i < questions.size(); i++) {
	            switch (i) {
	                case 0:
	                    lblChallenge1.setText(questions.get(i).getChallenge());
	                    lblAnswer1.setText(questions.get(i).getAnswer());
	                    break;
	                case 1:
	                    lblChallenge2.setText(questions.get(i).getChallenge());
	                    lblAnswer2.setText(questions.get(i).getAnswer());
	                    break;
	                case 2:
	                    lblChallenge3.setText(questions.get(i).getChallenge());
	                    lblAnswer3.setText(questions.get(i).getAnswer());
	                    break;
	                case 3:
	                    lblChallenge4.setText(questions.get(i).getChallenge());
	                    lblAnswer4.setText(questions.get(i).getAnswer());
	                    break;
	                default:
	                    break;
	            }
	        }
	    }
		return tableGR;
	}
	
	//Title
	
	public Label getLblSubject() {
		if(lblSubject==null) {
			lblSubject=new Label(currentCard.getSubject());
            GridPane.setColumnSpan(lblSubject, 4);
            lblSubject.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: white;");
		}
		return lblSubject;
	}
	
	//Headers
	public Label getDifficultyHeader() {
	    if (difficultyHeader == null) {
	        difficultyHeader = new Label("Difficulty");
            difficultyHeader.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");

	    }
	    return difficultyHeader;
	}

	public Label getQuestionHeader() {
	    if (questionHeader == null) {
	        questionHeader = new Label("Challenge");
            questionHeader.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
	    }
	    return questionHeader;
	}

	public Label getAnswerHeader() {
	    if (answerHeader == null) {
	        answerHeader = new Label("Answer");
            answerHeader.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
	    }
	    return answerHeader;
	}

	public Label getModifyHeader() {
	    if (modifyHeader == null) {
	        modifyHeader = new Label("Modify");
            modifyHeader.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
	    }
	    return modifyHeader;
	}
	
	//question 1
	public Label getLblDifficulty1() {
	    if (lblDifficulty1 == null) {
	        lblDifficulty1 = new Label("1");
			lblDifficulty1.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
	    }
	    return lblDifficulty1;
	}
	
	public Label getLblChallenge1() {
		if(lblChallenge1==null) {
			lblChallenge1=new Label(currentCard.getQuestions().get(0).getChallenge());
			lblChallenge1.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
		}
		return lblChallenge1;
	}
	
	public Label getLblAnswer1() {
		if(lblAnswer1==null) {
			lblAnswer1=new Label(currentCard.getQuestions().get(0).getAnswer());
			lblAnswer1.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
		}
		return lblAnswer1;
	}
	
	public Button getBtnModify1() {
		if(btnModify1==null) {
			btnModify1=new Button("Modify");
            btnModify1.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
            btnModify1.setOnAction(event -> {
	        	modifyQuestionAndAnswer(0);
            });		
		}
		return btnModify1;
	}
	
	
	// Question 2
	public Label getLblDifficulty2() {
	    if (lblDifficulty2 == null) {
	        lblDifficulty2 = new Label("2");
			lblDifficulty2.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
	    }
	    return lblDifficulty2;
	}
	
	public Label getLblChallenge2() {
	    if (lblChallenge2 == null) {
	        if (currentCard.getQuestions().size() > 1) {
	            lblChallenge2 = new Label(currentCard.getQuestions().get(1).getChallenge());
	        } else {
	            lblChallenge2 = new Label("");
	        }
	        lblChallenge2.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
	    }
	    return lblChallenge2;
	}

	public Label getLblAnswer2() {
	    if (lblAnswer2 == null) {
	        if (currentCard.getQuestions().size() > 1) {
	            lblAnswer2 = new Label(currentCard.getQuestions().get(1).getAnswer());
	        } else {
	            lblAnswer2 = new Label("");
	        }
	        lblAnswer2.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
	    }
	    return lblAnswer2;
	}

	public Button getBtnModify2() {
	    if (btnModify2 == null) {
	        btnModify2 = new Button("Modify");
	        btnModify2.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
	        btnModify2.setOnAction(event -> {
	        	modifyQuestionAndAnswer(1);
	        });
	    }
	    return btnModify2;
	}

	// Question 3
	public Label getLblDifficulty3() {
	    if (lblDifficulty3 == null) {
	        lblDifficulty3 = new Label("3");
			lblDifficulty3.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
	    }
	    return lblDifficulty3;
	}
	
	public Label getLblChallenge3() {
	    if (lblChallenge3 == null) {
	        if (currentCard.getQuestions().size() > 2) {
	            lblChallenge3 = new Label(currentCard.getQuestions().get(2).getChallenge());
	        } else {
	            lblChallenge3 = new Label("");
	        }
	        lblChallenge3.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
	    }
	    return lblChallenge3;
	}

	public Label getLblAnswer3() {
	    if (lblAnswer3 == null) {
	        if (currentCard.getQuestions().size() > 2) {
	            lblAnswer3 = new Label(currentCard.getQuestions().get(2).getAnswer());
	        } else {
	            lblAnswer3 = new Label("");
	        }
	        lblAnswer3.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
	    }
	    return lblAnswer3;
	}

	public Button getBtnModify3() {
	    if (btnModify3 == null) {
	        btnModify3 = new Button("Modify");
	        btnModify3.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
	        btnModify3.setOnAction(event -> {
	        	modifyQuestionAndAnswer(2);
	        });
	    }
	    return btnModify3;
	}

	// Question 4
	public Label getLblDifficulty4() {
	    if (lblDifficulty4 == null) {
	        lblDifficulty4 = new Label("4");
			lblDifficulty4.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
	    }
	    return lblDifficulty4;
	}
	public Label getLblChallenge4() {
	    if (lblChallenge4 == null) {
	        if (currentCard.getQuestions().size() > 3) {
	            lblChallenge4 = new Label(currentCard.getQuestions().get(3).getChallenge());
	        } else {
	            lblChallenge4 = new Label("");
	        }
	        lblChallenge4.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
	    }
	    return lblChallenge4;
	}

	public Label getLblAnswer4() {
	    if (lblAnswer4 == null) {
	        if (currentCard.getQuestions().size() > 3) {
	            lblAnswer4 = new Label(currentCard.getQuestions().get(3).getAnswer());
	        } else {
	            lblAnswer4 = new Label("");
	        }
	        lblAnswer4.setStyle("-fx-font-size: 15px; -fx-text-fill: white;");
	    }
	    return lblAnswer4;
	}

	public Button getBtnModify4() {
	    if (btnModify4 == null) {
	        btnModify4 = new Button("Modify");
	        btnModify4.setStyle("-fx-font-size: 18px; -fx-text-fill: white;");
	        btnModify4.setOnAction(event -> {
	        	modifyQuestionAndAnswer(3);
	        });
	    }
	    return btnModify4;
	}

	
    // Method to handle modifying a question and answer
	public void modifyQuestionAndAnswer(int questionIndex) {
	    VBox modifyBox = new VBox(10);
	    modifyBox.setAlignment(Pos.CENTER);
	    modifyBox.setPadding(new Insets(20));
	    modifyBox.setMaxSize(600, 400);
	    modifyBox.setStyle("-fx-background-color:white");

	    Label lblNewChallenge = new Label("New Challenge:");
	    TextField txtNewChallenge = new TextField();
	    Label lblNewAnswer = new Label("New Answer:");
	    TextField txtNewAnswer = new TextField();

	    Scene scene = this.getScene();
	    Button btnSave = new Button("Save");
	    btnSave.setOnAction(saveEvent -> {
	        String newChallenge = txtNewChallenge.getText();
	        String newAnswer = txtNewAnswer.getText();
	        Question currentQuestion = currentCard.getQuestions().get(questionIndex);
	        currentQuestion.setChallenge(newChallenge);
	        currentQuestion.setAnswer(newAnswer);

	        getTableGR();

	        Gson gson = new Gson();
	        String json = gson.toJson(deck);
	        Persistance.writeFile("deck.json", json);

	        MainMenuSP mainMenu = (MainMenuSP) scene.getRoot();
	        mainMenu.getChildren().clear();
	        mainMenu.getChildren().add(new CardMenuVB());
	    });

	    Button btnCancel = new Button("Cancel");
	    btnCancel.setOnAction(cancelEvent -> {
	        MainMenuSP mainMenu = (MainMenuSP) scene.getRoot();
	        mainMenu.getChildren().clear();
	        mainMenu.getChildren().add(new CardMenuVB());
	    });

	    modifyBox.getChildren().addAll(lblNewChallenge, txtNewChallenge, lblNewAnswer, txtNewAnswer, btnSave, btnCancel);

	    MainMenuSP mainMenu = (MainMenuSP) scene.getRoot();
	    mainMenu.getChildren().clear();
	    mainMenu.getChildren().add(modifyBox);
	}


	
	

}
