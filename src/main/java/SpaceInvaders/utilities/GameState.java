package SpaceInvaders.utilities;

import SpaceInvaders.Game;

/**
 * Holds the gameState like score and playing.
 * <p>
 * Holds information like score and level.<br>
 * is created by the AbstractFactory so that it can be passed to the objects that need to interact with it.<br>
 */
public class GameState {

    /**
     * The score of the player in the SpaceInvaders.
     */
    private int score;

    /**
     * Tells if the gaming is running.<br>
     * If false the {@link Game#gameLoop()} exits.
     */
    private Boolean isPlaying;

    /**
     * The current Level that is loaded are needs to be loaded.
     * <p>Used in {@link Game#loadLevel()}.</p>
     */
    private int currentLevel;

    public Boolean getPlaying() {
        return isPlaying;
    }

    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
