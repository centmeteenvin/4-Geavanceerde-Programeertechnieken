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

    public void initialize() {
        isPlaying = true;
        score = 0;
        currentLevel = 1;
    }

    /**
     * Getter for isPlaying.
     * @return {@link #isPlaying}
     */
    public Boolean getPlaying() {
        return isPlaying;
    }

    /**
     * setter for isPlaying.
     * @param playing {@link #isPlaying}
     */
    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    /**
     * getter for score.
     * @return {@link #score}
     */
    public int getScore() {
        return score;
    }

    /**
     * setter for score.
     * @param score {@link #score}
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * getter for currentLevel.
     * @return {@link #currentLevel}
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * setter for currentLevel.
     * @param currentLevel {@link #currentLevel}
     */
    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
