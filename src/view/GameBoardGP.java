package view;

import exception.EmptyDeckException;
import exception.ThemeNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import squares.BlueSquare;
import squares.DuelSquare;
import squares.FinalSquare;
import squares.GreenSquare;
import squares.OrangeSquare;
import squares.PurpleSquare;
import squares.SpiderGadgetSquare;
import squares.SuperVilainSquare;

public class GameBoardGP extends GridPane{

	private static final double GRID_SQUARE_WIDTH = 110;
	private static final double GRID_SQUARE_HEIGHT = 110;
	private static final int NUM_COLS=8;
	private static final int NUM_ROWS=6;

	//Game board course
	private final int[][] parcours = {
			{6, 0}, {5, 0}, {4, 0}, {3, 0}, {2, 0}, {1, 0}, 
			{0, 0}, {0, 1}, {0, 2},
			{1, 2}, {2, 2}, {3, 2}, {4, 2}, {5, 2}, {6, 2},
			{6, 3}, {6, 4},
			{5, 4}, {4, 4}, {3, 4}, {2, 4}, {1, 4}, {0, 4},
			{0, 5}, {0, 6}, 
			{1, 6}, {2, 6}, {3, 6}, {4, 6}, {5, 6}, {6, 6},
			{6, 7}, {6, 8},
			{5, 8}, {4, 8}, {3, 8}, {2, 8}, {1, 8}, {0, 8}
	};

	public GameBoardGP() {		
		this.creationColumns(NUM_COLS, GRID_SQUARE_WIDTH);
		this.creationRows(NUM_ROWS, GRID_SQUARE_HEIGHT);
		this.dispositionSquares();

		this.setAlignment(Pos.CENTER);
		this.setPadding(new Insets(0, 0, 0, 100));
		this.setVgap(10);
		this.setHgap(10);

		BorderPane.setMargin(this, new Insets(0, 0, 0, 0)); 

	}

	//Moves the player on the game board based on the number of steps
	public void movePlayer(PlayerView player,int steps) {
		int newParcoursIndex = player.getPlayer().getParcoursIndex() + steps;
		if(newParcoursIndex<0) {
			newParcoursIndex=0;
		}
		System.out.println(newParcoursIndex);
		if (newParcoursIndex < parcours.length) {
			System.out.println("Je passe dedans");
			int newRow = parcours[newParcoursIndex][0];
			int newCol = parcours[newParcoursIndex][1];
			player.getPlayer().moveTo(newRow, newCol);
			setConstraints(player, newCol, newRow);
			player.getPlayer().setParcoursIndex(newParcoursIndex);
		}
	}
	
	public void movePlayerCheat(PlayerView player,int parcourIndex) {
		int newParcoursIndex = parcourIndex;
		
		if (newParcoursIndex < parcours.length) {
			int newRow = parcours[newParcoursIndex][0];
			int newCol = parcours[newParcoursIndex][1];
			player.getPlayer().moveTo(4, 4);
			setConstraints(player, newCol, newRow);
			player.getPlayer().setParcoursIndex(newParcoursIndex);
		}
	}
	

	public void positionsExchange(PlayerView player,PlayerView player2) {
		setConstraints(player, player.getPlayer().getCol(), player.getPlayer().getRow());
		setConstraints(player2, player2.getPlayer().getCol(), player2.getPlayer().getRow());
	}
	// Processes the action associated with the square
	public void processSquareAction(GameSP game, PlayerView player,int col,int row) {
		Node node = getNodeAtPosition(col, row, player);

		if (node instanceof ImageView) {
			ImageView imageView = (ImageView) node;	            
			Image image = imageView.getImage();

			if (image.getUrl().contains("blue")) {
				BlueSquare blueSquare = new BlueSquare();
				try {
					blueSquare.onPlayerEnter(game, player);
				} catch (EmptyDeckException | ThemeNotFoundException e) {
					e.printStackTrace();
				}
			}else if (image.getUrl().contains("green")) {
				GreenSquare greensquare = new GreenSquare();
				try {
					greensquare.onPlayerEnter(game, player);
				} catch (EmptyDeckException | ThemeNotFoundException e) {
					e.printStackTrace();
				}
			}else if (image.getUrl().contains("orange")) {
				OrangeSquare orangesquare = new OrangeSquare();
				try {
					orangesquare.onPlayerEnter(game, player);
				} catch (EmptyDeckException | ThemeNotFoundException e) {
					e.printStackTrace();
				}
			}else if (image.getUrl().contains("purple")) {
				PurpleSquare purpleSquare = new PurpleSquare();
				try {
					purpleSquare.onPlayerEnter(game, player);
				} catch (EmptyDeckException | ThemeNotFoundException e) {
					e.printStackTrace();
				}
			}else if (image.getUrl().contains("red")) {
				DuelSquare duelSquare = new DuelSquare();
				duelSquare.onPlayerEnter(game, player);
				
			}else if (image.getUrl().contains("black")) {
				SuperVilainSquare supervilainSquare = new SuperVilainSquare();
				supervilainSquare.onPlayerEnter(game, player);
				
			}else if (image.getUrl().contains("yellow")) {
				SpiderGadgetSquare spiderGadgetSquare = new SpiderGadgetSquare();
				spiderGadgetSquare.onPlayerEnter(game, player);
				
			}else if (image.getUrl().contains("final")) {
				FinalSquare finalSquare = new FinalSquare();
				finalSquare.onPlayerEnter(game, player);
			}
		}			
	}

	//Places the squares on the board
	public void dispositionSquares() {
		this.add(getPurpleSquare().getSquareImage(), 0, 6);
		this.add(getBlueSquare().getSquareImage(), 2, 6);
		this.add(getDuelSquare().getSquareImage(), 3, 6);
		this.add(getGreenSquare().getSquareImage(), 4, 6);
		this.add(getBlueSquare().getSquareImage(), 6, 6);
		this.add(getGreenSquare().getSquareImage(), 7, 6);
		this.add(getOrangeSquare().getSquareImage(), 8, 6);

		this.add(getBlueSquare().getSquareImage(), 0, 5);
		this.add(getPurpleSquare().getSquareImage(), 2, 5);
		this.add(getOrangeSquare().getSquareImage(), 4, 5);
		this.add(getPurpleSquare().getSquareImage(), 6, 5);
		this.add(getGreenSquare().getSquareImage(), 8, 5);

		this.add(getGreenSquare().getSquareImage(), 0, 4);
		this.add(getSuperVilainSquare().getSquareImage(), 2, 4);
		this.add(getSpiderGadgetSquare().getSquareImage(), 4, 4);
		this.add(getSuperVilainSquare().getSquareImage(), 6, 4);
		this.add(getBlueSquare().getSquareImage(), 8, 4);

		this.add(getSuperVilainSquare().getSquareImage(), 0, 3);
		this.add(getOrangeSquare().getSquareImage(), 2, 3);
		this.add(getPurpleSquare().getSquareImage(), 4, 3);
		this.add(getGreenSquare().getSquareImage(), 6, 3);
		this.add(getSpiderGadgetSquare().getSquareImage(), 8, 3);

		this.add(getOrangeSquare().getSquareImage(), 0, 2);
		this.add(getGreenSquare().getSquareImage(), 2, 2);
		this.add(getSuperVilainSquare().getSquareImage(), 4, 2);
		this.add(getDuelSquare().getSquareImage(), 6, 2);
		this.add(getPurpleSquare().getSquareImage(), 8, 2);

		this.add(getSpiderGadgetSquare().getSquareImage(), 0, 1);
		this.add(getPurpleSquare().getSquareImage(), 2, 1);
		this.add(getGreenSquare().getSquareImage(), 4, 1);
		this.add(getOrangeSquare().getSquareImage(), 6, 1);
		this.add(getOrangeSquare().getSquareImage(), 8, 1);

		this.add(getBlueSquare().getSquareImage(), 0, 0);
		this.add(getGreenSquare().getSquareImage(), 1, 0);
		this.add(getOrangeSquare().getSquareImage(), 2, 0);
		this.add(getOrangeSquare().getSquareImage(), 4, 0);
		this.add(getSpiderGadgetSquare().getSquareImage(), 5, 0);
		this.add(getBlueSquare().getSquareImage(), 6, 0);
		this.add(getFinalSquare().getSquareImage(), 8, 0);
	}

	public void creationColumns(int numCols,double squareWidth) {
		for (int i = 0; i < numCols; i++) {
			ColumnConstraints column = new ColumnConstraints(squareWidth);
			this.getColumnConstraints().add(column);
		}    	
	}

	public void creationRows(int numRows,double squareHeight) {
		for (int i = 0; i < numRows; i++) {
			RowConstraints row = new RowConstraints(squareHeight);
			this.getRowConstraints().add(row);
		}
	}



	public BlueSquare getBlueSquare() {
		return new BlueSquare();
	}

	public PurpleSquare getPurpleSquare() {
		return new PurpleSquare();
	}

	public GreenSquare getGreenSquare() {
		return new GreenSquare();
	}

	public OrangeSquare getOrangeSquare() {
		return new OrangeSquare();
	}

	public DuelSquare getDuelSquare() {
		return new DuelSquare();
	}

	public FinalSquare getFinalSquare() {
		return new FinalSquare();
	}

	public SpiderGadgetSquare getSpiderGadgetSquare() {
		return new SpiderGadgetSquare();
	}

	public SuperVilainSquare getSuperVilainSquare() {
		return new SuperVilainSquare();
	}

	//Retrieves the node at the specified position on the game board
	public Node getNodeAtPosition(int col, int row, PlayerView p1) {
		row=getRowIndex(p1);
		col=getColumnIndex(p1);
		for (Node node : getChildren()) {
			if (getRowIndex(node) == row && getColumnIndex(node) == col) {
				return node;
			}
		}
		return null;
	}
	
	public void setPlayer(PlayerView p, int col, int row) {
		p.getPlayer().moveTo(row, col);
		
	}

}

