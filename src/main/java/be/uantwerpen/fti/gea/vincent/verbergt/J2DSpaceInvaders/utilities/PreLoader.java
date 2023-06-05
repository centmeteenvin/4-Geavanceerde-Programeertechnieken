package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory;
import javafx.util.Pair;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Preloads the assets in memory.
 * Otherwise, reading them from the assetFiles takes to long.
 */
public class PreLoader {

    /**
     * Map of loaded imageSprites.
     */
    private final HashMap<String, BufferedImage> loadedImages = new HashMap<>();

    /**
     * Map of loaded animation frames.
     */
    private final HashMap<String, ArrayList<BufferedImage>> loadedAnimations = new HashMap<>();
    /**
     * Byte Array of loaded sounds.
     * Is converted to audioInputStream in {@link #fetchSound(String)}.
     */
    private final HashMap<String, Pair<AudioFormat, byte[]>> loadedSounds = new HashMap<>();

    /**
     * Creates the preloader Object and Loads the assets in memory.
     *
     * @param factory Needed for properties.
     */
    public PreLoader(J2DFactory factory) {
        try {
            Props props = factory.properties;
            for (String imageName : props.getImageAssets()) {
                loadedImages.put(imageName, ImageIO.read(new File(imageName)));
            }

            for (String animationName : props.getAnimationAssets()) {
                loadedAnimations.put(animationName, getFrames(new File(animationName)));
            }

            for (String soundName : props.getSoundAssets()) {
                AudioInputStream stream = AudioSystem.getAudioInputStream(new File(soundName));
                Pair<AudioFormat, byte[]> entry = new Pair<>(stream.getFormat(), stream.readAllBytes());
                loadedSounds.put(soundName, entry);
            }
        } catch (IOException e) {
            System.out.println("Error loading assets");
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Audio extension is not allowed");
            throw new RuntimeException(e);
        }
    }

    /**
     * Return a copy of the value in {@link #loadedImages}.
     *
     * @param assetName the name of the asset.
     * @return the copy of the asset.
     */
    public BufferedImage fetchImage(String assetName) {
        return ImageService.copy(loadedImages.get(assetName));
    }

    /**
     * Return a copy of the value in {@link #loadedAnimations}.
     *
     * @param assetName the name of the asset.
     * @return the copy of the asset.
     */
    public ArrayList<BufferedImage> fetchAnimation(String assetName) {
        return loadedAnimations.get(assetName).stream().map(ImageService::copy).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Copy the byte data into a new AudioInputStream.
     * @param assetName the nome of the asset.
     * @return a new AudioInputStream.
     */
    public AudioInputStream fetchSound(String assetName) {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(loadedSounds.get(assetName).getValue());
        return new AudioInputStream(byteStream, loadedSounds.get(assetName).getKey(), loadedSounds.get(assetName).getValue().length);
    }

    /**
     * Fetch the frames from a gif and store them as bufferedImages in an array.
     *
     * @param gif the file of the gif
     * @return ArrayList with sorted bufferedImages.
     * @throws IOException when A file cannot be read, could be wrong format.
     */
    private ArrayList<BufferedImage> getFrames(File gif) throws IOException {
        ArrayList<BufferedImage> frames = new ArrayList<>();
        ImageReader imageReader = ImageIO.getImageReadersByFormatName("gif").next();
        ImageInputStream imageStream = ImageIO.createImageInputStream(gif);
        imageReader.setInput(imageStream);

        int totalFrames = imageReader.getNumImages(true);
        for (int i = 0; i < totalFrames; i++) {
            frames.add(imageReader.read(i));
        }
        return frames;
    }


}
