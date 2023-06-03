import com.sun.javafx.tk.Toolkit;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.junit.jupiter.api.Test;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Sound {
    @Test
    public void soundTest() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        File audioFile = new File("src/main/resources/J2D/blaster.wav");
        AudioInputStream in = AudioSystem.getAudioInputStream(audioFile);
        Clip clip = AudioSystem.getClip();
        clip.open(in);
        clip.start();
//        Clip clip = AudioSystem.getClip();
//        File audio = new File("src/main/resources/J2D/blaster.wav");
//        AudioInputStream in = AudioSystem.getAudioInputStream(audio);
//        clip.open(in);
//        clip.start();
    }

    public static void main(String[] args) {
        try {

            File audioFile = new File("src/main/resources/J2D/blaster.wav");
            AudioInputStream in = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(in);
            clip.start();
            System.out.println("Finished playing sound");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
