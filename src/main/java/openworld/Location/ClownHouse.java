package src.main.java.openworld.Location;

import java.util.Scanner;

import src.main.java.openworld.AudioManager;
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

        // Play the audio using AudioManager
        Thread circusThread = new Thread(() -> {
            AudioManager.playAudio(circusPath);
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