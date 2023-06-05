package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.dataOriented;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.PreLoader;

import javax.sound.sampled.*;
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
     * The preloader containing the cached sounds.
     */
    private final PreLoader preLoader;

    /**
     * The Constructor of the soundEngine
     * @param preLoader the Preloader with the cached sounds.
     */
    public SoundEngine(PreLoader preLoader) {
        this.preLoader = preLoader;
        try {
            AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

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
            loadedSounds.put(soundComponent, preLoader.fetchSound(soundComponent.getFileName()));
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
