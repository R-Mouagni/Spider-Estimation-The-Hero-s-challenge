package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LoginMenuVB extends BorderPane {
	private VBox loginBox;
	//HBox Login
	private HBox hbLogin;
	private Label lblLogin;
	private TextField txtLogin;
	//HBox Password
	private HBox hbPwd;
	private Label lblPassword;
	private PasswordField txtPsw;
	
	private HBox hbButtons;
	private Button btnOkLogin;
	private Button btnBackLogin;

	private Label labelVerif;
	private Button btnBack;

	
	public LoginMenuVB() {
		this.setTop(getBtnBack());
		this.setCenter(getLoginBox());
	}

	
	public VBox getLoginBox() {
		if(loginBox == null) {
			loginBox = new VBox();
			loginBox.getStyleClass().add("login-box");
			loginBox.getChildren().addAll(getHbLogin(), getHbPwd(), getHbButtons(), getLabelVerif());
			loginBox.setAlignment(Pos.CENTER);
			loginBox.setMaxWidth(300);
			loginBox.setMaxHeight(200);
		}
		return loginBox;
	}

	public HBox getHbLogin() {
		if(hbLogin == null) {
			hbLogin = new HBox();
			hbLogin.getChildren().addAll(getLblLogin(), getTxtLogin());

		}
		return hbLogin;
	}

	public Label getLblLogin() {
		if(lblLogin == null) {
			lblLogin = new Label("Login"); 
			lblLogin.getStyleClass().add("login-label");

		}
		return lblLogin;
	}

	public TextField getTxtLogin() {
		if(txtLogin == null) {
			txtLogin = new TextField(); 
			txtLogin.getStyleClass().add("login-field");

		}
		return txtLogin;
	}

	public Label getLblPassword() {
		if(lblPassword == null) {
			lblPassword = new Label("Password"); 
			lblPassword.getStyleClass().add("login-label");

		}
		return lblPassword;
	}

	public PasswordField getTxtPsw() {
		if(txtPsw == null) {
			txtPsw = new PasswordField(); 
			txtPsw.getStyleClass().add("login-field");

		}
		return txtPsw;
	}

	public Button getBtnOkLogin() {
		if(btnOkLogin == null) {
			btnOkLogin = new Button("Login");
			btnOkLogin.getStyleClass().add("login-button");
			btnOkLogin.setOnAction(event -> {
				//if admin login is OK
				if (txtLogin.getText().equals("admin") && getTxtPsw().getText().equals("helha")) {
					labelVerif.setText("Connected successfully!");
					labelVerif.setTextFill(Color.GREEN);
		            Scene scene = this.getScene();
		            MainMenuSP mainMenu = (MainMenuSP) scene.getRoot();
		            mainMenu.getChildren().clear();
		            mainMenu.getChildren().add(new CardMenuVB());
					
		           //if admin login not OK
				} else {
					labelVerif.setText("Connection impossible.");
					labelVerif.setTextFill(Color.RED);
				}
			});

		}
		return btnOkLogin;
	}

	public Button getBtnBackLogin() {
		if(btnBackLogin == null) {
			btnBackLogin = new Button("Return"); 
			btnBackLogin.setOnAction(event -> {
	            Scene scene = this.getScene();
	            MainMenuSP mainMenu = (MainMenuSP) scene.getRoot();
	            mainMenu.getChildren().clear();
	            mainMenu.displayOptions();
			});
		}
		return btnBackLogin;
	}

	public Label getLabelVerif() {
		if(labelVerif == null) {
			labelVerif = new Label();
		}
		return labelVerif;
	}

	public HBox getHbPwd() {
		if(hbPwd == null) {
			hbPwd = new HBox();
			hbPwd.getChildren().addAll(getLblPassword(), getTxtPsw());

		}
		return hbPwd;
	}
	
	public HBox getHbButtons() {
		if(hbButtons==null) {
			hbButtons=new HBox(10);
			hbButtons.getChildren().addAll(getBtnBackLogin(),getBtnOkLogin());
		}
		return hbButtons;
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
}
