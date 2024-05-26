package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import view.MainMenuSP;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
        	Font.loadFont(getClass().getResourceAsStream("/resources/fonts/PixelSansSerif.ttf"), 10);
            primaryStage.setTitle("Spider-Estimation : The Hero's Challenge");
            primaryStage.getIcons().add(new Image("file:src/resources/img/menu/logo.png"));
            MainMenuSP mainMenu = new MainMenuSP();
            Scene menuScene = new Scene(mainMenu, 1920, 1080);
            menuScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(menuScene);
            primaryStage.setFullScreenExitHint("");
            //primaryStage.setMaximized(true);
            primaryStage.setFullScreen(true);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
