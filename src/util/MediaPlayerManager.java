package util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaPlayerManager {
    private static MediaPlayerManager instance;
    private MediaPlayer menuMediaPlayer;
    private MediaPlayer gameMediaPlayer;

    private MediaPlayerManager() {
        // Initialize menu media player
        String menuMusicFile = getClass().getClassLoader().getResource("resources/medias/menuSong.mp3").toString();
        Media menuSound = new Media(menuMusicFile);
        menuMediaPlayer = new MediaPlayer(menuSound);
        menuMediaPlayer.setVolume(0);
        menuMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        // Initialize game media player
        String gameMusicFile = getClass().getClassLoader().getResource("resources/medias/gameSong1.mp3").toString();
        Media gameSound = new Media(gameMusicFile);
        gameMediaPlayer = new MediaPlayer(gameSound);
        gameMediaPlayer.setVolume(0);
        gameMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    }
    
    public static MediaPlayerManager getInstance() {
        if (instance == null) {
            instance = new MediaPlayerManager();
        }
        return instance;
    }

    public MediaPlayer getMenuMediaPlayer() {
        return menuMediaPlayer;
    }

    public MediaPlayer getGameMediaPlayer() {
        return gameMediaPlayer;
    }

    // Method to set volume for both menu and game media players
    public void setVolume(double volume) {
        menuMediaPlayer.setVolume(volume);
        gameMediaPlayer.setVolume(volume);
    }
}
