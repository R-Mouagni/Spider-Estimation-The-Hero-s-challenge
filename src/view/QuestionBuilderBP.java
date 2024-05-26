package view;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import exception.CardAlreadyExistsException;
import exception.QuestionAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.BasicCard;
import model.Deck;
import model.Question;
import model.Theme;
import util.Persistance;

public class QuestionBuilderBP extends BorderPane {
	
	//Hbox1
	private Label lblTheme;
	private Rectangle rctlColorTheme;
	private ComboBox<Theme> cmbTheme;
	private Label lblAuthor;
	private TextField txtAuthor;
	
	//Hbox2
	private Label lblSubject;
	private TextField txtSubject;
	
	//Hbox3
	private Label lblChallenges;
	private Label lblAnswers;
	
	//Hbox4 (Challenge 1)
	private Label lblQuestion1;
	private TextField txtChallenge1;
	private TextField txtAnswer1;
	
	//Hbox5 (Challenge 2)
	private Label lblQuestion2;
	private TextField txtChallenge2;		
	private TextField txtAnswer2;
		
	
	//Hbox6 (Challenge 3)
	private Label lblQuestion3;
	private TextField txtChallenge3;
	private TextField txtAnswer3;
		
	//Hbox7 (Challenge 4)
	private Label lblQuestion4;
	private TextField txtChallenge4;
	private TextField txtAnswer4;	
	
	//Hbox8 (Delete validate)
	private Button btnValidate;
	private Button btnResume;

	
	
	public QuestionBuilderBP() {
		
		//Top
		HBox hb1=new HBox();
		hb1.getChildren().addAll(getLblTheme(),getRctlColorTheme(),getCmbTheme(),getLblAuthor(),getTxtAuthor());
		hb1.setSpacing(15);
        
		cmbTheme.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue) {
                    case INFORMATICS:
                        rctlColorTheme.setFill(Color.BLUE);
                        break;
                    case EDUCATION:
                        rctlColorTheme.setFill(Color.GREEN);
                        break;
                    case ENTERTAINMENT:
                    	rctlColorTheme.setFill(Color.ORANGE);
                    	break;
                    case IMPROBABLE:
                    	rctlColorTheme.setFill(Color.PURPLE);
                    	break;
                    default:
                        rctlColorTheme.setFill(Color.BLACK); 
                        break;
                }
            }
        });
		HBox hb2=new HBox();
		hb2.getChildren().addAll(getLblSubject(),getTxtSubject());
		hb2.setSpacing(15);

		VBox vboxTop=new VBox();
		vboxTop.getChildren().addAll(hb1,hb2);
		
		this.setTop(vboxTop);
		
		//Center left
		HBox hb3 = new HBox();
		hb3.getChildren().addAll(getLblQuestion1(), getTxtChallenge1());
		hb3.setSpacing(15);

		HBox hb4 = new HBox();
		hb4.getChildren().addAll(getLblQuestion2(), getTxtChallenge2());
		hb4.setSpacing(15);

		HBox hb5 = new HBox();
		hb5.getChildren().addAll(getLblQuestion3(), getTxtChallenge3());
		hb5.setSpacing(15);

		HBox hb6 = new HBox();
		hb6.getChildren().addAll(getLblQuestion4(), getTxtChallenge4());
		hb6.setSpacing(15);
		
		VBox vboxCenterLeft=new VBox();
		vboxCenterLeft.getChildren().addAll(getLblChallenges(),hb3,hb4,hb5,hb6);

		
		//Centre right
		HBox hb7= new HBox(getTxtAnswer1());
		hb7.setSpacing(15);
		
		HBox hb8= new HBox(getTxtAnswer2());
		hb8.setSpacing(15);
		
		HBox hb9= new HBox(getTxtAnswer3());
		hb9.setSpacing(15);
		
		HBox hb10= new HBox(getTxtAnswer4());
		hb10.setSpacing(15);
		
		VBox vboxCenterRight = new VBox();
		vboxCenterRight.getChildren().addAll(getLblAnswers(),hb7,hb8,hb9,hb10);
		
		//Hbox center
		HBox hbCenter = new HBox(vboxCenterLeft,vboxCenterRight);
		this.setCenter(hbCenter);
		
		
		//Bottom
		HBox hb11 = new HBox();
		hb11.getChildren().addAll(getBtnResume(),getBtnValidate());
		hb11.setAlignment(Pos.BASELINE_RIGHT);
		this.setBottom(hb11);
		
		//Texte aligned to the right
		HBox.setHgrow(getTxtAuthor(), Priority.ALWAYS);
		HBox.setHgrow(getTxtSubject(), Priority.ALWAYS);
		HBox.setHgrow(getTxtAnswer1(), Priority.ALWAYS);
		HBox.setHgrow(getTxtAnswer2(), Priority.ALWAYS);
		HBox.setHgrow(getTxtAnswer3(), Priority.ALWAYS);
		HBox.setHgrow(getTxtAnswer4(), Priority.ALWAYS);
		HBox.setHgrow(getTxtChallenge1(), Priority.ALWAYS);
		HBox.setHgrow(getTxtChallenge2(), Priority.ALWAYS);
		HBox.setHgrow(getTxtChallenge3(), Priority.ALWAYS);
		HBox.setHgrow(getTxtChallenge4(), Priority.ALWAYS);

		//Padding
		hb1.setPadding(new Insets(10, 5, 10, 5));
		hb2.setPadding(new Insets(10, 5, 10, 5));
		hb3.setPadding(new Insets(10, 5, 10, 5));
		hb4.setPadding(new Insets(10, 5, 10, 5));
		hb5.setPadding(new Insets(10, 5, 10, 5));
		hb6.setPadding(new Insets(10, 5, 10, 5));
		hb7.setPadding(new Insets(10, 5, 10, 5));
		hb8.setPadding(new Insets(10, 5, 10, 5));
		hb9.setPadding(new Insets(10, 5, 10, 5));
		hb10.setPadding(new Insets(10, 5, 10, 5));
		hb11.setPadding(new Insets(10, 5, 10, 5));
		
		
		//Margin
		setMargin(vboxTop, new Insets(10, 5, 10, 5));
		setMargin(hbCenter, new Insets(10,5,10,5));
	}
	
	public Button getBtnValidate() {
		if(btnValidate==null) {
			btnValidate=new Button("Validate");
			btnValidate.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
	            // Define the action to be performed when the button is clicked
				public void handle(ActionEvent event) {
	                // Check if all fields are filled and a theme is selected
				    if (!getTxtAuthor().getText().isEmpty() &&
				            !getTxtSubject().getText().isEmpty() &&
				            !getTxtChallenge1().getText().isEmpty() &&
				            !getTxtChallenge2().getText().isEmpty() &&
				            !getTxtChallenge3().getText().isEmpty() &&
				            !getTxtChallenge4().getText().isEmpty() &&
				            !getTxtAnswer1().getText().isEmpty() &&
				            !getTxtAnswer2().getText().isEmpty() &&
				            !getTxtAnswer3().getText().isEmpty() &&
				            !getTxtAnswer4().getText().isEmpty()&&
				            getCmbTheme().getValue()!=null) {
	                    // Check if the deck file exists
				    	if(Persistance.isExist("deck.json")) {
				    		Gson g=new Gson();
				    		String read = Persistance.readFile("deck.json");
				    		Deck deck = g.fromJson(read, Deck.class);
				    		
	                        // Create questions and a basic card
				    		Question q1=new Question(getTxtAuthor().getText() ,cmbTheme.getValue(),getTxtSubject() .getText(),getTxtChallenge1().getText(),getTxtAnswer1().getText());
				    		Question q2=new Question(getTxtAuthor().getText() ,cmbTheme.getValue(),getTxtSubject() .getText(),getTxtChallenge2().getText(),getTxtAnswer2().getText());
				    		Question q3=new Question(getTxtAuthor().getText() ,cmbTheme.getValue(),getTxtSubject() .getText(),getTxtChallenge3().getText(),getTxtAnswer3().getText());
				    		Question q4=new Question(getTxtAuthor().getText() ,cmbTheme.getValue(),getTxtSubject() .getText(),getTxtChallenge4().getText(),getTxtAnswer4().getText());

				    		BasicCard b=new BasicCard(getTxtAuthor().getText() ,cmbTheme.getValue(), getTxtSubject().getText());
				    		try {
								b.add(q1);
								b.add(q2);
					    		b.add(q3);
					    		b.add(q4);
							} catch (QuestionAlreadyExistsException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    		
				    		
				    		try {
								deck.add(b);
							} catch (CardAlreadyExistsException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    		System.out.println(deck);
				    		String json=g.toJson(deck);
				    		Persistance.writeFile("deck.json", json);
				    		
	                        // Clear all text fields after successful validation
		                    getTxtAuthor().setText("");
		                    getTxtSubject().setText("");
		                    getTxtChallenge1().setText("");
		                    getTxtChallenge2().setText("");
		                    getTxtChallenge3().setText("");
		                    getTxtChallenge4().setText("");
		                    getTxtAnswer1().setText("");
		                    getTxtAnswer2().setText("");
		                    getTxtAnswer3().setText("");
		                    getTxtAnswer4().setText("");
				    	}
				    	//if deck file not exist
				    	else {
				    		Question q1=new Question(getTxtAuthor().getText() ,cmbTheme.getValue(),getTxtSubject() .getText(),getTxtChallenge1().getText(),getTxtAnswer1().getText());
				    		Question q2=new Question(getTxtAuthor().getText() ,cmbTheme.getValue(),getTxtSubject() .getText(),getTxtChallenge2().getText(),getTxtAnswer2().getText());
				    		Question q3=new Question(getTxtAuthor().getText() ,cmbTheme.getValue(),getTxtSubject() .getText(),getTxtChallenge3().getText(),getTxtAnswer3().getText());
				    		Question q4=new Question(getTxtAuthor().getText() ,cmbTheme.getValue(),getTxtSubject() .getText(),getTxtChallenge4().getText(),getTxtAnswer4().getText());

				    		BasicCard b=new BasicCard(getTxtAuthor().getText() ,cmbTheme.getValue(), getTxtSubject().getText());
				    		try {
								b.add(q1);
								b.add(q2);
					    		b.add(q3);
					    		b.add(q4);
							} catch (QuestionAlreadyExistsException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    		
				    		
				    		Deck deck= new Deck();
				    		try {
								deck.add(b);
							} catch (CardAlreadyExistsException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				    		System.out.println(deck);
				    		
				    		Gson g= new Gson();
				    		JsonElement json=g.toJsonTree(deck);
				    		Persistance.writeFile("deck.json", json.toString());
				    	}
				    	
				    }
				    else 
			            showErrorDialog("Error", "You must fill in all the fields.");		
				}
			});
		}
		
		return btnValidate;
	}
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
	


	public Label getLblTheme() {
	    if (lblTheme == null) {
	        lblTheme = new Label("Theme");
	    }
	    return lblTheme;
	}

	public Rectangle getRctlColorTheme() {
	    if (rctlColorTheme == null) {
	        rctlColorTheme = new Rectangle(25, 25);
	    }
	    return rctlColorTheme;
	}

	public ComboBox<Theme> getCmbTheme() {
	    if (cmbTheme == null) {
	        cmbTheme = new ComboBox<>();
	        cmbTheme.getItems().addAll(Theme.class.getEnumConstants());
	    }
	    return cmbTheme;
	}

	public Label getLblAuthor() {
	    if (lblAuthor == null) {
	        lblAuthor = new Label("Author");
	    }
	    return lblAuthor;
	}

	public TextField getTxtAuthor() {
	    if (txtAuthor == null) {
	        txtAuthor = new TextField();
	    }
	    return txtAuthor;
	}

	public Label getLblSubject() {
	    if (lblSubject == null) {
	        lblSubject = new Label("Subject");
	    }
	    return lblSubject;
	}

	public TextField getTxtSubject() {
	    if (txtSubject == null) {
	        txtSubject = new TextField();
	    }
	    return txtSubject;
	}

	public Label getLblChallenges() {
	    if (lblChallenges == null) {
	        lblChallenges = new Label("Challenges :");
	    }
	    return lblChallenges;
	}

	public Label getLblAnswers() {
	    if (lblAnswers == null) {
	        lblAnswers = new Label("Answers :");
	    }
	    return lblAnswers;
	}

	public Label getLblQuestion1() {
	    if (lblQuestion1 == null) {
	        lblQuestion1 = new Label("1 :");
	    }
	    return lblQuestion1;
	}

	public TextField getTxtChallenge1() {
	    if (txtChallenge1 == null) {
	        txtChallenge1 = new TextField();
	    }
	    return txtChallenge1;
	}

	public TextField getTxtAnswer1() {
	    if (txtAnswer1 == null) {
	        txtAnswer1 = new TextField();
	    }
	    return txtAnswer1;
	}

	public Label getLblQuestion2() {
	    if (lblQuestion2 == null) {
	        lblQuestion2 = new Label("2 :");
	    }
	    return lblQuestion2;
	}

	public TextField getTxtChallenge2() {
	    if (txtChallenge2 == null) {
	        txtChallenge2 = new TextField();
	    }
	    return txtChallenge2;
	}

	public TextField getTxtAnswer2() {
	    if (txtAnswer2 == null) {
	        txtAnswer2 = new TextField();
	    }
	    return txtAnswer2;
	}

	public Label getLblQuestion3() {
	    if (lblQuestion3 == null) {
	        lblQuestion3 = new Label("3 :");
	    }
	    return lblQuestion3;
	}

	public TextField getTxtChallenge3() {
	    if (txtChallenge3 == null) {
	        txtChallenge3 = new TextField();
	    }
	    return txtChallenge3;
	}

	public TextField getTxtAnswer3() {
	    if (txtAnswer3 == null) {
	        txtAnswer3 = new TextField();
	    }
	    return txtAnswer3;
	}

	public Label getLblQuestion4() {
	    if (lblQuestion4 == null) {
	        lblQuestion4 = new Label("4 :");
	    }
	    return lblQuestion4;
	}

	public TextField getTxtChallenge4() {
	    if (txtChallenge4 == null) {
	        txtChallenge4 = new TextField();
	    }
	    return txtChallenge4;
	}

	public TextField getTxtAnswer4() {
	    if (txtAnswer4 == null) {
	        txtAnswer4 = new TextField();
	    }
	    return txtAnswer4;
	}

	public Button getBtnResume() {
		if(btnResume == null) {
			btnResume = new Button("Return"); 
			btnResume.setOnAction(event -> {
	            Scene scene = this.getScene();
	            MainMenuSP mainMenu = (MainMenuSP) scene.getRoot();
	            mainMenu.getChildren().clear();
	            mainMenu.getChildren().add(new CardMenuVB());
            });
        }
		return btnResume;
	}
	
	

}
