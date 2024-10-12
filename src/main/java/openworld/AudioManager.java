package src.main.java.openworld;

import java.io.File;

import javax.sound.sampled.*;

public class AudioManager {
    private static volatile Clip currentClip;

    public static synchronized void playAudio(String audioFilePath) {
        try {
            // Stop the currently playing audio if any
            if (currentClip != null && currentClip.isRunning()) {
                currentClip.stop();
                currentClip.close();
            }

            // Set up the new audio clip
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip newClip = AudioSystem.getClip();
            newClip.open(audioStream);
            newClip.start();

            // Add a listener to close the clip when finished
            newClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    newClip.close();
                }
            });

            // Update the currently playing clip reference
            currentClip = newClip;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
