package game;

/**
 * Holds the gameState like score and playing.
 * is created by the AbstractFactory so that it can be passed to the objects that need to interact with it.
 */
public class GameState {
    private int score;
    private Boolean isPlaying;

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
}