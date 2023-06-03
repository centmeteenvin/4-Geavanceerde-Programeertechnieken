package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.dataOriented;

import java.util.Objects;

/**
 * The Components that {@link SoundEngine} plays.
 * A list of them is kept in {@link be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory#soundComponents}.
 */
public class SoundComponent {
    /**
     * The audioFile that we need to play.
     * Must be a .wav file.
     */
    private final String fileName;

    /**
     * The gain of the clip.
     * In Db.
     */
    private final int gain;

    /**
     * The complete constructor for a SoundComponent
     * @param fileName {@link #fileName}.
     * @param gain {@link #gain}.
     */
    public SoundComponent(String fileName, int gain) {
        this.fileName = fileName;
        this.gain = gain;
    }

    /**
     * The shortened constructor. Sets {@link #gain} to 0.
     * @param fileName {@link #fileName}
     */
    public SoundComponent(String fileName) {
        this(fileName, 0);
    }

    /**
     * The fileName we are working with.
     * @return {@link #fileName}.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * The volume Gain for this component.
     * @return {@link #gain}.
     */
    public int getGain() {
        return gain;
    }

    /**
     * Filter equality by {@link #fileName}.
     * playing the same sound twice in a frame is pointless.
     * @param o the other object.
     * @return true if equal.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SoundComponent that = (SoundComponent) o;

        if (gain != that.gain) return false;
        return Objects.equals(fileName, that.fileName);
    }


    /**
     * Filter equality by {@link #fileName}.
     * playing the same sound twice in a frame is pointless.
     * @return a unique integer.
     */
    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + gain;
        return result;
    }
    /**
     * Convert object to readable string.
     */
    @Override
    public String toString() {
        return "SoundComponent{" +
                "fileName='" + fileName + '\'' +
                ", gain=" + gain +
                '}';
    }
}