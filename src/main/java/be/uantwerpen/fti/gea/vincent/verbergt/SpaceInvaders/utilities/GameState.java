package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.Game;

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
     * The current Level that should be loaded.
     * <p>Used in {@link Game#loadLevel()}.</p>
     */
    private int currentLevel;

    /**
     * The level that is currently loaded.
     * Not the level that should be loaded: {@link #currentLevel}
     */
    private int currentLoadedLevel = -1;


    /**
     * The Health of the player
     */
    private int health = 0;

    /**
     * Initialisation of the gamestate.
     * <p>
     * Is called during {@link Game#initialize()}.
     * </p>
     */
    public void initialize() {
//        isPlaying = true;
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

    /**
     * Getter for the currently loaded level.
     * @return {@link #currentLoadedLevel}
     */
    public int getCurrentLoadedLevel() {
        return currentLoadedLevel;
    }

    /**
     * Setter for currently loaded level
     * @param currentLoadedLevel {@link #currentLoadedLevel}
     */
    public void setCurrentLoadedLevel(int currentLoadedLevel) {
        this.currentLoadedLevel = currentLoadedLevel;
    }

    /**
     * Easy way to add points.
     * Replaces {@code setScore(getScore() + points)}
     * @param points The amount of points that is added.
     * @return The new score.
     */
    public int addScore(int points) {
        score = score + points;
        return this.score;
    }

    /**
     * get the health of the player.
     * @return {@link #health}.
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Set the health of the player.
     * @param health {@link #health}.
     */
    public void setHealth(int health) {
        this.health = health;
    }
}
