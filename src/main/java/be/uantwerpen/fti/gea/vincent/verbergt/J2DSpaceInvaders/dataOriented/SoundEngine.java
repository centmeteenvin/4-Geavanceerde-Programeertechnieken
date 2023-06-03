package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.dataOriented;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is the controller for {@link SoundComponent}.
 */
public class SoundEngine {

    /**
     * Play the given sounds.
     * The sounds sizes should be small because they are loaded into memory.
     * Is Called from {@link be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory J2DFactory}.
     * @param sounds A list of {@link SoundComponent}.
     */
    public void playSounds(ArrayList<SoundComponent> sounds) {
        if (sounds.size() == 0) {
            return;
        }
//        System.out.println("Playing sound: " + sounds.toString());
        ArrayList<SoundComponent> uniqueSounds = sounds.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        HashMap<SoundComponent, AudioInputStream> loadedSounds = new HashMap<>();
        for (SoundComponent soundComponent: uniqueSounds) {
            try {
                loadedSounds.put(soundComponent, AudioSystem.getAudioInputStream(new File(soundComponent.getFileName())));
            } catch (UnsupportedAudioFileException | IOException e) {
                System.out.println("Error loading sound from file");
                throw new RuntimeException(e);
            }
        }
//        System.out.println("Unique Sounds are: " + uniqueSounds);
//        System.out.println("Loaded Sounds are: " + loadedSounds);
        for (Map.Entry<SoundComponent, AudioInputStream> entry : loadedSounds.entrySet()) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(entry.getValue());
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                clip.start();
            } catch (LineUnavailableException | IOException e) {
                System.out.println("Audio Line Not available");
                throw new RuntimeException(e);
            }
        }
    }

}
