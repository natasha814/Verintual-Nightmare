package src.main.java.openworld.Location;

import java.util.Scanner;
import src.main.java.openworld.Item;
import src.main.java.openworld.JumpScare;
import src.main.java.openworld.Player.Player;
import java.io.File;
import javax.sound.sampled.*;

public class ClownHouse extends Location {
    private Item item;
    private String circusPath = "src/main/resources/circus.wav";

    public ClownHouse(String name, String description, Item item) {
        super(name, description);
        this.item = item;
    }

    @Override
    public void enter(Player player) {
        JumpScare jumpScare = new JumpScare();

        // Create a new Thread instance each time enter() is called
        Thread circusThread = new Thread(() -> {
            try {
                File audioFile = new File(circusPath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();

                // Add a listener to close the clip when finished
                clip.addLineListener(event -> {
                    if (event.getType() == LineEvent.Type.STOP) {
                        clip.close();
                    }
                });

                // Wait for the audio to finish playing
                while (clip.isRunning()) {
                    Thread.sleep(100); // Check every 100 ms if the clip is still running
                }

                audioStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        circusThread.start(); // Start the new thread

        // Display the jumpscare and location description
        jumpScare.imageJump("src/main/java/openworld/images/tent.jpg", 1000);
        System.out.println("You enter the " + getName() + ": " + getDescription());
        
        if (item != null) {
            String a_or_an = checkFirstLetterVowel(item) ? "an " : "a ";
            System.out.println("You see " + a_or_an + item.name().toLowerCase() + " here.");
            
            Scanner userIn = new Scanner(System.in);
            System.out.println("Would you like to pick it up? [Y/N]");
            char info = userIn.nextLine().toLowerCase().charAt(0);
            
            if (info == 'y') {
                player.getInventory().addItem(item);
            }
        }
    }

    private boolean checkFirstLetterVowel(Item item) {
        char lower_char = Character.toLowerCase(item.name().charAt(0));
        return lower_char == 'a' || lower_char == 'e' || lower_char == 'i' || lower_char == 'o' || lower_char == 'u';
    }

    @Override
    public String toString() {
        return getName() + ": " + getDescription();
    }

    public Item getItem() {
        return item;
    }
}