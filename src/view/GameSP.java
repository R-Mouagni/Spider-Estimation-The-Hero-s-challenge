package view;

import java.util.ArrayList;

import exception.DeckNotFoundException;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import model.Deck;
import model.Player;
import util.MediaPlayerManager;

public class GameSP extends StackPane {

	private static GameBoardGP gameBoard;
	private ArrayList<PlayerView>players;
	private int turn;
	private int nbPlayers;
	private int currentPlayerIndex;
	private HBox container;
	private Background background;
    private MediaPlayerManager mediaPlayerManager;
    private Deck deck;

    //Initializes a new game with the number of players asked by the user
	public GameSP(int nbPlayers) {
        mediaPlayerManager = MediaPlayerManager.getInstance();
		players=new ArrayList<>();
		
		
		 background = new Background();
	     this.getChildren().addAll(background.getBackground1(), background.getBackground2());

	    // Initializing the container for the game board
		container=new HBox();
		gameBoard=new GameBoardGP(); 
        container.getChildren().add(gameBoard);
		
        // Initializing players based on the chosen number of players
		if(nbPlayers>=2 && nbPlayers<=4) {
			this.nbPlayers = nbPlayers;
			initializePlayers(nbPlayers);
			System.out.println(players);
		}

		placePlayersAtStartingPosition(players);
		try {
			this.deck=Deck.loadDeckFromFile("deck.json");
		} catch (DeckNotFoundException e) {
			e.printStackTrace();
		}
		this.getChildren().add(container);
		
        // Initializing the turn and playing background music
		this.turn=1;
		this.currentPlayerIndex=0;
		playBackgroundMusic();
        
		container.setAlignment(Pos.CENTER);
        container.setSpacing(30);
        
        // Displaying an information box for the players
        new InfoBoxVB(this, "Hi Spidies, to defeat Venom, you first have to go through the roof of all these buildings. \n"
                + "To progress, answer each question he asks you correctly. Depending on your level of knowledge, \n"
                + "you'll progress more quickly. But don't be fooled into thinking it's that easy, there are obstacles in the way. \n\n"
                + "Good luck!\n\n");
	}

	
	public void startGame() {
		currentPlayerIndex=-1;
		nextTurn();
	}

	//Proceeds to the next turn, updates the current player
	public void nextTurn() {
		if(currentPlayerIndex<=nbPlayers-2) {
			currentPlayerIndex++;
		}else {
			currentPlayerIndex=0;
		}
		PlayerView currentPlayer = players.get(currentPlayerIndex);
		
		refreshPositions();
        
		System.out.println("tour n°"+turn+" du "+currentPlayer+" currentIndex :"+currentPlayerIndex);
		gameBoard.processSquareAction(this, currentPlayer, currentPlayer.getPlayer().getCol(), currentPlayer.getPlayer().getRow());
		turn++;
	}
	
    // Method to refresh the positions of players on the game board
	public void refreshPositions() {
		for(PlayerView p:players) {
			ArrayList<PlayerView> allPlayersAtSamePosition = getPlayersAtSamePosition(p);
			if(allPlayersAtSamePosition.size()==1) {
				PlayerView.adjustSizeAndPosition(p);
			}else {
				for (int j=0;j<=nbPlayers;j++) {
					PlayerView.adjustSizeAndPosition(allPlayersAtSamePosition);
				}
				allPlayersAtSamePosition=null;
			}
			
		}
		
	}

    public ArrayList<PlayerView> getPlayersAtSamePosition(PlayerView player) {
        ArrayList<PlayerView> playersAtSamePosition = new ArrayList<>();
        int row = player.getPlayer().getRow();
        int col = player.getPlayer().getCol();
        for (PlayerView otherPlayer : players) {
            if (otherPlayer.getPlayer().getRow() == row && otherPlayer.getPlayer().getCol() == col) {
                playersAtSamePosition.add(otherPlayer);
            }
        }
        return playersAtSamePosition;
    }

    public ArrayList<PlayerView> getPlayers() {
        return players;
    }

	public void initializePlayers(int numPlayers) {
		players.clear();
		if (numPlayers >=2 && numPlayers <=4) {
			ArrayList<Image> playerImages = initializePlayerImages();
			for (int i = 0; i < numPlayers; i++) {
				Player player = new Player(0, 6);
				PlayerView playerView = new PlayerView(playerImages.get(i),player);
				players.add(playerView);
			}
		}

	}
	
	public ArrayList<Image> initializePlayerImages() {
		ArrayList<Image> playerImages = new ArrayList<>();
		playerImages.add(new Image(getClass().getResource("/resources/img/players/redSpiderMan.png").toString()));
		playerImages.add(new Image(getClass().getResource("/resources/img/players/blackSpiderMan.png").toString()));
		playerImages.add(new Image(getClass().getResource("/resources/img/players/yellowSpiderMan.png").toString()));
		playerImages.add(new Image(getClass().getResource("/resources/img/players/blueSpiderMan.png").toString()));
		return playerImages;
	}

	public void placePlayersAtStartingPosition(ArrayList<PlayerView> players) {
		for(PlayerView p:players) {
			gameBoard.add(p, 0, 6);			
		}
	}
	
    // Method to play the background music of the game
	public void playBackgroundMusic() {
        mediaPlayerManager.getGameMediaPlayer().play();
    }
	
    // Method to stop the background music of the game
	public void stopBackgroundMusic() {
		mediaPlayerManager.getGameMediaPlayer().stop();
	}
	

	
	public String toString() {
		StringBuilder str= new StringBuilder();
		for(PlayerView p:players) {
			str.append(p.getPlayer().toString()).append("\n");
		}
		return str.toString();
	}

	public GameBoardGP getGameBoard() {
		return gameBoard;
	}

	public void setCurrentPlayerIndex(int currentPlayerIndex) {
		this.currentPlayerIndex = currentPlayerIndex;
	}
	
	public HBox getContener() {
		return container;
	}
	
	public Deck getDeck() {
		return deck;
	}
}