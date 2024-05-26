package squares;

import exception.EmptyDeckException;
import exception.ThemeNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import view.GameSP;
import view.PlayerView;

public abstract class Square {
    private static final double SQUARE_WIDTH = 110;
    private static final double SQUARE_HEIGHT = 110;
    private ImageView squareImage;
    private String txtColor;

    public Square(String imagePath) {
    	Image image = new Image(getClass().getResource(imagePath).toString());
        squareImage = new ImageView(image);
        squareImage.setFitWidth(SQUARE_WIDTH);
        squareImage.setFitHeight(SQUARE_HEIGHT);
    }
    
    public  ImageView getSquareImage() {
    	return squareImage;
    }
    
    public abstract void onPlayerEnter(GameSP game, PlayerView player) throws EmptyDeckException, ThemeNotFoundException;

    public String getTxtColor() {
    	return txtColor;
    }
    
    public void setTxtColor(String txtColor) {
    	this.txtColor=txtColor;
    }
 
   
}
    
 
